package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDatabase implements PersonDatabase {

    Map<Integer, Person> idMap = new HashMap<>();
    Map<String, List<Person>> nameMap = new HashMap<>();
    Map<String, List<Person>> adressMap = new HashMap<>();
    Map<String, List<Person>> phoneNumberMap = new HashMap<>();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            idMap.put(person.id(), person);
            putIntoListMap(person.name(), person, nameMap);
            putIntoListMap(person.address(), person, adressMap);
            putIntoListMap(person.phoneNumber(), person, phoneNumberMap);
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    private void putIntoListMap(String key, Person person, Map<String, List<Person>> map) {
        if (map.containsKey(key)) {
            map.get(key).add(person);
        } else {
            map.put(key, new ArrayList<>() {
                {
                    add(person);
                }
            });
        }
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            Person person = idMap.get(id);
            if (person == null) {
                return;
            }
            idMap.remove(id);
            nameMap.get(person.name()).remove(person);
            adressMap.get(person.address()).remove(person);
            phoneNumberMap.get(person.phoneNumber()).remove(person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        readWriteLock.readLock().lock();
        try {
            return nameMap.get(name);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        readWriteLock.readLock().lock();
        try {
            return adressMap.get(address);
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    @Override
    public List<Person> findByPhone(String phone) {
        readWriteLock.readLock().lock();
        try {
            return phoneNumberMap.get(phone);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
