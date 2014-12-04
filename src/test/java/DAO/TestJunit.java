package DAO;

import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import persistance.dao.ContestDAO;
import persistance.dao.PersonDAO;
import persistance.dao.TeamDAO;
import persistance.dao.impl.ContestDAOImpl;
import persistance.dao.impl.PersonDAOImpl;
import persistance.dao.impl.TeamDAOImpl;
import persistance.model.Contest;
import persistance.model.Person;
import persistance.model.State;
import persistance.model.Team;
import persistance.utlis.HibernateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by tkachdan on 30-Nov-14.
 */
public class TestJunit {
    public static Session session = new HibernateUtils().getSessionFactory().openSession();

    Date date = new Date();
    java.sql.Date hugoBirth = new java.sql.Date(date.getTime());
    java.sql.Date carriageDate = new java.sql.Date(date.getTime());
    java.sql.Date dateFrom = new java.sql.Date(date.getTime());
    java.sql.Date dateTill = new java.sql.Date(date.getTime());


    @Test
    public void saveTest() {
        ContestDAO contestDAO = new ContestDAOImpl();
        PersonDAO personDAO = new PersonDAOImpl();
        TeamDAO teamDAO = new TeamDAOImpl();

        Contest contest = new Contest("local contest", 5, null, null, null, true);
        Person person = new Person("testPerson", "test@email.com", "cvut", null);
        Team team = new Team("teamName", 5, State.ACCEPTED, contest, null, null);

        contestDAO.saveContest(contest);
        personDAO.savePerson(person);
        teamDAO.saveTeam(team);

        List teamResults = session.createQuery("from Team").list();
        List contestResults = session.createQuery("from Contest ").list();
        List personResults = session.createQuery("from Person ").list();

        Contest contestRes = (Contest) contestResults.get(contestResults.size() - 1);
        Team teamRes = (Team) teamResults.get(teamResults.size() - 1);
        Person personRes = (Person) personResults.get(personResults.size() - 1);


        Assert.assertEquals(contest.getId(), contestRes.getId());
        Assert.assertEquals(team.getId(), teamRes.getId());
        Assert.assertEquals(person.getId(), personRes.getId());


    }

    @Test
    public void updateTest() {
        ContestDAO contestDAO = new ContestDAOImpl();
        PersonDAO personDAO = new PersonDAOImpl();
        TeamDAO teamDAO = new TeamDAOImpl();

        Contest contest = new Contest("local contest", 5, null, null, null, true);
        Person person = new Person("testPerson", "test@email.com", "cvut", null);
        Team team = new Team("teamName", 5, State.ACCEPTED, contest, null, null);

        contestDAO.saveContest(contest);
        personDAO.savePerson(person);
        teamDAO.saveTeam(team);


        contest.setName("new local contest");
        person.setName("new testPerson");
        team.setName("new testTeam");

        contestDAO.updateContest(contest);
        personDAO.updatePerson(person);
        teamDAO.updateTeam(team);

        List teamResults = session.createQuery("from Team").list();
        List contestResults = session.createQuery("from Contest ").list();
        List personResults = session.createQuery("from Person ").list();

        Contest contestRes = (Contest) contestResults.get(contestResults.size() - 1);
        Team teamRes = (Team) teamResults.get(teamResults.size() - 1);
        Person personRes = (Person) personResults.get(personResults.size() - 1);


        Assert.assertEquals(contest.getName(), contestRes.getName());
        Assert.assertEquals(team.getName(), teamRes.getName());
        Assert.assertEquals(person.getName(), personRes.getName());

    }

    @Test
    public void deleteTest() {
        ContestDAO contestDAO = new ContestDAOImpl();
        PersonDAO personDAO = new PersonDAOImpl();
        TeamDAO teamDAO = new TeamDAOImpl();

        Contest contest = new Contest("local contest", 5, null, null, null, true);
        Person person = new Person("testPerson", "test@email.com", "cvut", null);
        Team team = new Team("teamName", 5, State.ACCEPTED, contest, null, null);

        contestDAO.saveContest(contest);
        personDAO.savePerson(person);
        teamDAO.saveTeam(team);

        teamDAO.deleteTeam(team.getId());
        contestDAO.deleteContest(contest.getId());
        personDAO.deletePerson(person.getId());



       /* List teamResults = session.createQuery("from Team where id = ?").setParameter(0,team.getId()).list();
        List contestResults = session.createQuery("from Contest where id = ?").setParameter(0,contest.getId()).list();
        List personResults = session.createQuery("from Person where id = ?").setParameter(0,person.getId()).list();

        if((teamResults.size() > 0) || (contestResults.size() > 0) || (personResults.size() > 0)){
            AssertionError error = new AssertionError();

        }

*/

    }

    @Test
    public void testContestDAO() {
        ContestDAO data = new ContestDAOImpl();
        Contest contest = new Contest("local contest", 5, carriageDate, dateFrom, dateTill, true);

        data.saveContest(contest);

        String hql = "from Contest ";

        Query query = session.createQuery(hql);
        List results = query.list();
        Contest contestRes = (Contest) results.get(results.size() - 1);
        Assert.assertEquals(contest.getId(), contestRes.getId());
        Assert.assertEquals(contest.getName(), contestRes.getName());

        Contest contest1 = new Contest("sec contest", 5, carriageDate, dateFrom, dateTill, true);

        data.saveContest(contest1);

        List<Contest> allContest = data.getAllContests();
        for (Contest c : allContest) {
            System.out.println(c);
        }


    }


    @Test
    public void updateContestDAO() {
        ContestDAO data = new ContestDAOImpl();
        Contest contest = new Contest("local contest", 5, carriageDate, dateFrom, dateTill, true);

        data.saveContest(contest);

        contest.setName("sssss");

        data.updateContest(contest);

        String hql = "from Contest ";

        Query query = session.createQuery(hql);
        List results = query.list();
        Contest contestRes = (Contest) results.get(results.size() - 1);
        Assert.assertEquals(contest.getId(), contestRes.getId());
        Assert.assertEquals(contest.getName(), "sssss");

        Contest contest1 = new Contest("sec contest", 5, carriageDate, dateFrom, dateTill, true);

        data.saveContest(contest1);

        List<Contest> allContest = data.getAllContests();
        for (Contest c : allContest) {
            System.out.println(c);
        }


    }


}
