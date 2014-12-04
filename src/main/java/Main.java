import org.hibernate.Session;
import persistance.model.Contest;
import persistance.model.Person;
import persistance.model.State;
import persistance.model.Team;
import persistance.utlis.HibernateUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tkachdan on 26-Nov-14.
 */
public class Main {

    public static void main(String[] args) {


        // Dates
        Date date = new Date();
        java.sql.Date hugoBirth = new java.sql.Date(date.getTime());
        java.sql.Date carriageDate = new java.sql.Date(date.getTime());
        java.sql.Date dateFrom = new java.sql.Date(date.getTime());
        java.sql.Date dateTill = new java.sql.Date(date.getTime());

        Session session = new HibernateUtils().getSessionFactory().openSession();
        session.beginTransaction();

        Person person = new Person("asd", "asd@asd.asd", "cvut", hugoBirth);
        Team team = new Team("team");

        team.setState(State.ACCEPTED);
        Set<Person> teamMembers = new HashSet<>();
        teamMembers.add(person);
        team.setTeamMembers(teamMembers);
//String name, int capacity, Date date, Date registrationfrom, Date registrationtill, boolean isregistrationopen) {
        Contest contest = new Contest("local contest", 5, carriageDate, dateFrom, dateTill, true);

        team.setAttendsContest(contest);

        session.save(person);
        session.save(team);
        session.save(contest);

        session.getTransaction().commit();
///////////////////////////


    }
}
