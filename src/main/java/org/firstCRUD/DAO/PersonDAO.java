package org.firstCRUD.DAO;

import org.firstCRUD.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT;
    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Zuma"));
        people.add(new Person(++PEOPLE_COUNT, "Rockie"));
        people.add(new Person(++PEOPLE_COUNT, "Skie"));
        people.add(new Person(++PEOPLE_COUNT, "Chase"));

    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

}
