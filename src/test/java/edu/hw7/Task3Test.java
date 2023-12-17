package edu.hw7;

import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import edu.hw7.Task3.ReadWriteLockDatabase;
import edu.hw7.Task3.SynchronizedDatabase;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static java.lang.Thread.sleep;

public class Task3Test {
    @Test
    void synchronizedDatabaseTest() {
        SynchronizedDatabase personDatabase = new SynchronizedDatabase();
        Person person = new Person(10, "abc", null, "123");
        Person person1 = new Person(5, "abc", "Secret", "023");
        Thread addThread = getThread(personDatabase, person, person1);
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            Future<List<Person>> findByName = executorService.submit(() -> personDatabase.findByName("abc"));
            Future<List<Person>> finsByAddress = executorService.submit(() -> personDatabase.findByAddress("Secret"));
            Future<List<Person>> findByPhone = executorService.submit(() -> personDatabase.findByPhone("023"));
            executorService.shutdown();
            boolean isExecuted = executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
            boolean everythingIsInDataBase = findByName.get().contains(person1) && finsByAddress.get().contains(person1)
                && findByPhone.get().contains(person1);
            boolean isAbsent = !findByName.get().contains(person1) && !finsByAddress.get().contains(person1)
                && !findByPhone.get().contains(person1);
            Assertions.assertTrue(everythingIsInDataBase || isAbsent);
            personDatabase.delete(5);
            Assertions.assertFalse(findByName.get().contains(person1));
            Assertions.assertFalse(finsByAddress.get().contains(person1));
            Assertions.assertFalse(findByPhone.get().contains(person1));
            addThread.join();
            if (!isExecuted) {
                throw new Exception();
            }
        } catch (Exception ignored) {
        }
    }

    @Test
    void readAndLockDatabaseTest() {
        ReadWriteLockDatabase personDatabase = new ReadWriteLockDatabase();
        Person person = new Person(10, "abc", null, "123");
        Person person1 = new Person(5, "abc", "Secret", "023");
        Thread addThread = getThread(personDatabase, person, person1);

        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            Future<List<Person>> findByName = executorService.submit(() -> personDatabase.findByName("abc"));
            Future<List<Person>> finsByAddress = executorService.submit(() -> personDatabase.findByAddress("Secret"));
            Future<List<Person>> findByPhone = executorService.submit(() -> personDatabase.findByPhone("023"));
            executorService.shutdown();
            boolean isExecuted = executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
            boolean everythingIsInDataBase = findByName.get().contains(person1) && finsByAddress.get().contains(person1)
                && findByPhone.get().contains(person1);
            boolean isAbsent = !findByName.get().contains(person1) && !finsByAddress.get().contains(person1)
                && !findByPhone.get().contains(person1);
            Assertions.assertTrue(everythingIsInDataBase || isAbsent);
            personDatabase.delete(5);
            Assertions.assertFalse(findByName.get().contains(person1));
            Assertions.assertFalse(finsByAddress.get().contains(person1));
            Assertions.assertFalse(findByPhone.get().contains(person1));
            addThread.join();
            if (!isExecuted) {
                throw new Exception();
            }
        } catch (Exception ignored) {
        }
    }

    @NotNull private static Thread getThread(PersonDatabase personDatabase, Person person, Person person1) {
        Thread addThread = new Thread(() -> {
            try {
                sleep(500);
                personDatabase.add(person);
                sleep(500);
                personDatabase.add(person1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            sleep(500);
            addThread.start();
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return addThread;
    }
}
