package persistance.dao.impl;

/**
 * Created by tkachdan on 04-Dec-14.
 */

import org.hibernate.Query;
import org.hibernate.Session;
import persistance.dao.PersonDAO;
import persistance.model.Person;
import persistance.model.Team;
import persistance.utlis.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public class PersonDAOImpl implements PersonDAO {


    public void savePerson(Person person) {
        Session session = Service.getSession(); // Service.getSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public Person getPerson(int id) {
        Session session = Service.getSession();
        session.beginTransaction();
        Person person = null;

        person = (Person) session
                .createQuery("from Person person where id = :personID")
                .setInteger("personID", id).uniqueResult();

        session.getTransaction().commit();
        session.close();
        return person;
    }

    public boolean isExists(Person person) {
        Session session = Service.getSession();
        session.beginTransaction();
        if (getPerson(person.getId()) == null) {
            session.close();
            return false;
        } else {
            session.close();
            return true;
        }
    }

    public Set<Team> getPersonsTeam(Person person) {
        Session session = Service.getSession();
        session.beginTransaction();
        //SELECT r.name FROM Team t JOIN t.teamMembers r WHERE t.id = 3
        TeamDAOImpl teamDAO = new TeamDAOImpl();
        Set<Team> teams = new HashSet<Team>();
        List<Team> allTeams = teamDAO.getAllTeams();

        for (Team team : allTeams) {
            if (team.getTeamMembers().contains(person))
                teams.add(team);
        }

        session.close();
        return teams;
    }

    public Person getPersonByEmail(String email) {
        Session session = Service.getSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Person t WHERE t.email = :emailParam");
        query.setParameter("emailParam", email);
        List<Person> persons = query.list();

        if (persons.size() == 0) {
            session.close();
            return null;
        } else {
            session.close();
            return persons.get(0);
        }
    }


    @Override
    public List<Person> getAllPersons() {
        Session session = Service.getSession();
        session.beginTransaction();
        List persons = session.createQuery("FROM Person ").list();
        session.close();
        return persons;
    }


    public void updatePerson(Person person) {
        Session session = Service.getSession();
        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void deletePerson(int id) {
        Session session = Service.getSession();
        session.beginTransaction();

        Person person = (Person) session.load(Person.class, id);
        session.delete(person);
        session.getTransaction().commit();
        session.close();
    }
}