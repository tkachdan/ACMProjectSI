package persistance.dao.impl;

/**
 * Created by tkachdan on 04-Dec-14.
 */

import org.hibernate.Session;
import persistance.dao.PersonDAO;
import persistance.model.Person;
import persistance.utlis.HibernateUtils;

import java.util.List;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public class PersonDAOImpl implements PersonDAO {
    Session session = new HibernateUtils().getSessionFactory().openSession();


    public void savePerson(Person person) {
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();


    }

    @Override
    public Person getPerson(int id) {
        session.beginTransaction();
        Person person = null;

        person = (Person) session
                .createQuery("from Person person where id = :personID")
                .setInteger("personID", id).uniqueResult();

        session.getTransaction().commit();

        return person;
    }

    @Override
    public List<Person> getAllPersons() {
        session.beginTransaction();
        List persons = session.createQuery("FROM Person ").list();

        return persons;
    }


    public void updatePerson(Person person) {
        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();


    }

    @Override
    public void deletePerson(int id) {
        session.beginTransaction();

        Person person = (Person) session.load(Person.class, id);
        session.delete(person);
        session.getTransaction().commit();

    }
}