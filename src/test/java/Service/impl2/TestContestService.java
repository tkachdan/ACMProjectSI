package Service.impl2;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import persistance.model.Contest;
import persistance.model.Person;
import persistance.model.State;
import persistance.model.Team;
import persistance.utlis.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tkachdan on 13-Dec-14.
 */
public class TestContestService extends TestServiceAbstract {

    Date date = new Date();
    java.sql.Date hugoBirth = new java.sql.Date(date.getTime());
    java.sql.Date carriageDate = new java.sql.Date(date.getTime());
    java.sql.Date dateFrom = new java.sql.Date(date.getTime());
    java.sql.Date dateTill = new java.sql.Date(date.getTime());

    Contest contest = new Contest("local contest", 5, null, null, null, true);
    Person person = new Person("testPerson", "test@email.com", "cvut", null);
    Team team = new Team("teamName", 5, State.ACCEPTED, contest, null, null);

    @Test
    public void testSetNewManagerToNewContest() {
        contestService.setManager(contest, person);

        Session session = Service.getSession();

        Query query = session.createQuery("SELECT r FROM Person t JOIN t.isManagerOfContest r");
        List<Contest> contests = query.list();
        List<Person> persons = personDAO.getAllPersons();

        Assert.assertEquals("contest_name-1", contests.get(0).getName());
        Assert.assertEquals("Pavel", persons.get(0).getName());
        Assert.assertEquals(contests.get(0), persons.get(0).getIsManagerOfContest().toArray()[0]);
    }

    @Test
    public void testPromoteTeam() {

        Contest contestTo = contest;
        Contest contestFrom = contest;
        contestDAO.saveContest(contestTo);
        contestDAO.saveContest(contestFrom);

        Person person = new Person("person1", "email1@mail.com", "cvut", null);
        personDAO.savePerson(person);
        Set<Person> teamMembers = new HashSet<Person>();
        teamMembers.add(person);

        Team team1 = new Team("team1", 1, State.ACCEPTED, null, null, teamMembers);
        team1.setAttendsContest(contestFrom);
        teamDAO.saveTeam(team1);

        System.out.println(contestService.promoteTeam(team1, contestFrom, contestTo));
        List<Team> teams = teamDAO.getAllTeams();
        List<Contest> contests = contestDAO.getAllContests();

        Assert.assertEquals(2, teams.size());
        Assert.assertEquals(2, contests.size());

        System.out.println(teams.size());
        System.out.println(teams);
        System.out.println(contests);
    }

    @Test
    public void testPromoteTeamWithNoRank() {

        Contest contestTo = new Contest(10, "contest to (initial)", new Date(), true, new Date(), new Date(), null);
        Contest contestFrom = new Contest(null, "contest from (destination)", new Date(), true, new Date(), new Date(), contestTo);
        contestDAO.saveContest(contestTo);
        contestDAO.saveContest(contestFrom);

        Person person = new Person("person1", "email1@mail.com", "cvut", null);
        personDAO.savePerson(person);
        Set<Person> teamMembers = new HashSet<Person>();
        teamMembers.add(person);

        Team team1 = new Team("team1", 0, State.ACCEPTED, null, null, teamMembers);
        team1.setAttendsContest(contestFrom);
        teamDAO.saveTeam(team1);

        Assert.assertEquals(false, contestService.promoteTeam(team1, contestFrom, contestTo));
    }

    @Test
    public void testPromoteTeamWithNoState() {
        Contest contestTo = new Contest(null, "contest to (initial)", new Date(), true, new Date(), new Date(), null);
        Contest contestFrom = new Contest(null, "contest from (destination)", new Date(), true, new Date(), new Date(), contestTo);
        contestDAO.saveContest(contestTo);
        contestDAO.saveContest(contestFrom);

        Person person = new Person("person1", "email1@mail.com", "cvut", null);
        personDAO.savePerson(person);
        Set<Person> teamMembers = new HashSet<Person>();
        teamMembers.add(person);

        Team team1 = new Team("team1", 1, State.PENDING, null, null, teamMembers);
        team1.setAttendsContest(contestFrom);
        teamDAO.saveTeam(team1);

        Assert.assertEquals(false, contestService.promoteTeam(team1, contestFrom, contestTo));
    }

    @Test
    public void testRegisterTeam() {
        Date date = new Date(114, 11, 25, 12, 0, 0);//2014 - 25 December
        Date registrationFrom = new Date(114, 11, 0, 0, 0, 0); //2014 - 1 December
        Date registrationTo = new Date(115, 11, 30, 0, 0, 0); //2015 - 30 December
        Date birthday = new Date(90, 0, 0, 0, 0, 0); //1990 - 1 January
        Contest contest = new Contest(1, "contest_name", date, true, registrationFrom, registrationTo, null);

        Team team = new Team("team_TEST", 0, State.ACCEPTED);

        Set<Person> teamMembers = new HashSet<Person>();
        Person person1 = new Person("Favel", "favel@mail.com", "cvut", birthday);
        Person person2 = new Person("Pavel", "pavel@mail.com", "cvut", birthday);
        Person person3 = new Person("Latik", "latik@mail.com", "cvut", birthday);
        teamMembers.add(person1);
        teamMembers.add(person2);
        teamMembers.add(person3);

        Person coach = new Person("Main coach", "coach@mail.com", "cvut", new Date());

        contestService.registerTeam(team, teamMembers, contest, coach);
        System.out.println(contestDAO.getAllContests());
        System.out.println(teamDAO.getAllTeams());
        System.out.println(personDAO.getAllPersons());

        Assert.assertEquals(1, personDAO.getPersonsTeam(person1).size());
        Assert.assertEquals(true, personDAO.getPersonsTeam(person1).contains(team));
        Assert.assertEquals(1, personDAO.getPersonsTeam(person2).size());
        Assert.assertEquals(true, personDAO.getPersonsTeam(person2).contains(team));
        Assert.assertEquals(1, personDAO.getPersonsTeam(person3).size());
        Assert.assertEquals(true, personDAO.getPersonsTeam(person3).contains(team));
    }

    @Test
    public void testRegisterTeamWithSamePerson() {
        Date registrationFrom = new Date(114, 11, 0, 0, 0, 0); //2014 - 1 December
        Date registrationTo = new Date(115, 11, 30, 0, 0, 0); //2015 - 30 December
        Contest contest = new Contest(1, "contest_name", new Date(), true, registrationFrom, registrationTo, null);

        Person person1 = new Person("Favel", "favel@mail.com", "cvut", new Date(90, 0, 0));
        Person person2 = new Person("Pavel", "pavel@mail.com", "cvut", new Date(90, 0, 0));
        Person person3 = new Person("Latik", "latik@mail.com", "cvut", new Date(90, 0, 0));
        personDAO.savePerson(person1);
        personDAO.savePerson(person2);
        personDAO.savePerson(person3);

        Set<Person> teamMembers = new HashSet<Person>();
        Person person4 = new Person("test", "latik@mail.com", "cvut", new Date(90, 0, 0));
        Person person5 = new Person("test", "pavel@mail.com", "cvut", new Date(90, 0, 0));
        Person person6 = new Person("test", "favel@mail.com", "cvut", new Date(90, 0, 0));
        personDAO.savePerson(person4);
        personDAO.savePerson(person5);
        personDAO.savePerson(person6);

        teamMembers.add(person4);
        teamMembers.add(person5);
        teamMembers.add(person6);

        Team team = new Team("team_TEST", 0, State.ACCEPTED);
        //team.setTeamMembers(teamMembers);

        Person coach = new Person("Main coach", "coach@mail.com", "cvut", new Date());
        contestService.registerTeam(team, teamMembers, contest, coach);

        System.out.println(contestDAO.getAllContests());
        System.out.println(teamDAO.getAllTeams());
        System.out.println(personDAO.getAllPersons());

        Assert.assertEquals(1, personDAO.getPersonsTeam(person1).size());
        Assert.assertEquals(true, personDAO.getPersonsTeam(person1).contains(team));

        Assert.assertEquals(1, personDAO.getPersonsTeam(person2).size());
        Assert.assertEquals(true, personDAO.getPersonsTeam(person2).contains(team));

        Assert.assertEquals(1, personDAO.getPersonsTeam(person3).size());
        Assert.assertEquals(true, personDAO.getPersonsTeam(person3).contains(team));

        Assert.assertEquals(0, personDAO.getPersonsTeam(person4).size());
        Assert.assertEquals(false, personDAO.getPersonsTeam(person4).contains(team));

        Assert.assertEquals(0, personDAO.getPersonsTeam(person5).size());
        Assert.assertEquals(false, personDAO.getPersonsTeam(person5).contains(team));

        Assert.assertEquals(0, personDAO.getPersonsTeam(person6).size());
        Assert.assertEquals(false, personDAO.getPersonsTeam(person6).contains(team));

        Assert.assertEquals(7, personDAO.getAllPersons().size());
    }


}
