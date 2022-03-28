package org.firstCRUD.DAO;

import org.firstCRUD.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    //    private List<Person> people;
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/firstDB";
    private static final String USERBAME = "postgres";
    private static final String PASSWORD = "7411945";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERBAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//    {
//        people = new ArrayList<>();
//
//        people.add(new Person(++PEOPLE_COUNT, "Zuma",24,"zumaMamkinSyn@mail.ru"));
//        people.add(new Person(++PEOPLE_COUNT, "Rockie",20,"rockiehochunaitymamu@mail.ru"));
//        people.add(new Person(++PEOPLE_COUNT, "Skie",15,"littlegirl@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT,"Chase", 18,"lider1992@yandex.ru"));
//
//    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM People";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return people;
    }

    public Person show(int id) {
        // return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        Person person = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM people where id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setAge(resultSet.getInt("age"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return person;
    }

    public void save(Person person) {
        // person.setId(++PEOPLE_COUNT);
        //  people.add(person);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO people VALUES(1,?,?,?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();

//            Statement statement = connection.createStatement();
//            String SQL = "INSERT INTO People VALUES(" + 1 + ",'" + person.getName() +
//                    "'," + person.getAge() + ",'" + person.getEmail() + "')";
//
//            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void update(int id, Person updatePerson) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  people SET name=?, age=?,email=? WHERE id=?");

            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4, id);
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void delete(int id){
       PreparedStatement preparedStatement = null;

       try{
           preparedStatement = connection.prepareStatement("DELETE FROM people WHERE id = ?");
           preparedStatement.setInt(1,id);
           preparedStatement.executeUpdate();
       }catch (SQLException throwables){
           throwables.printStackTrace();
       }
    }
}
