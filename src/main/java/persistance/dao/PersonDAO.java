package persistance.dao;

import persistance.model.Person;
import persistance.model.Team;

import java.util.List;
import java.util.Set;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public interface PersonDAO {

    public void savePerson(Person team);

    public Person getPerson(int id);

    public List<Person> getAllPersons();

    public void updatePerson(Person person);

    public void deletePerson(int id);

    public Person getPersonByEmail(String email);

    public boolean isExists(Person person);

    public Set<Team> getPersonsTeam(Person person);

}