package edu.hw10.Task1;

import edu.hw10.Task1.Classes.MyClass;
import edu.hw10.Task1.Classes.MyRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomObjectGeneratorTest {

    @Test
    void nextObject() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        var myClass = rog.nextObject(MyClass.class);
        var myRecord = rog.nextObject(MyRecord.class);

        Assertions.assertInstanceOf(MyClass.class, myClass);
        Assertions.assertInstanceOf(MyRecord.class, myRecord);

    }

    @Test
    void testNextObject() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        var myClass = rog.nextObject(MyClass.class, "defaultFabric");
        Assertions.assertEquals("DefaultName", myClass.getName());
    }

    @Test
    void testAnnotations() {
        RandomObjectGenerator rog = new RandomObjectGenerator();

        var myClass = rog.nextObject(MyClass.class);

        Assertions.assertTrue(myClass.getAge() == 0 || myClass.getAge() == 1);
        Assertions.assertTrue(myClass.getSalary() > 0);
        assertNotNull(myClass.getName());
    }


}
