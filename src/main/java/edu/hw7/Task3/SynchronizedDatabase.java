package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedDatabase implements PersonDatabase {
    Map<Integer, Person> idMap = new HashMap<>();
    Map<String, List<Person>> nameMap = new HashMap<>();
    Map<String, List<Person>> adressMap = new HashMap<>();
    Map<String, List<Person>> phoneNumberMap = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        idMap.put(person.id(), person);
        putIntoListMap(person.name(), person, nameMap);
        putIntoListMap(person.address(), person, adressMap);
        putIntoListMap(person.phoneNumber(), person, phoneNumberMap);
    }

    private synchronized void putIntoListMap(String key, Person person, Map<String, List<Person>> map) {
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
    public synchronized void delete(int id) {
        Person person = idMap.get(id);
        if (person == null) {
            return;
        }
        idMap.remove(id);
        nameMap.get(person.name()).remove(person);
        adressMap.get(person.address()).remove(person);
        phoneNumberMap.get(person.phoneNumber()).remove(person);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameMap.get(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return adressMap.get(address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneNumberMap.get(phone);
    }
}
