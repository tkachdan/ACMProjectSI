package persistance.dao;

import persistance.model.Person;

import java.util.List;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public interface PersonDAO {

    public void savePerson(Person team);

    public Person getPerson(int id);

    public List<Person> getAllPersons();

    public void updatePerson(Person person);

    public void deletePerson(int id);


}