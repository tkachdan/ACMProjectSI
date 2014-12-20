package Service.impl;

import Service.ContestService;
import persistance.dao.ContestDAO;
import persistance.dao.PersonDAO;
import persistance.dao.TeamDAO;
import persistance.dao.impl.ContestDAOImpl;
import persistance.dao.impl.PersonDAOImpl;
import persistance.dao.impl.TeamDAOImpl;
import persistance.model.Contest;
import persistance.model.Person;
import persistance.model.Team;

import java.util.Date;
import java.util.Set;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public class ContestServiceImpl implements ContestService {
    TeamDAO teamDAO = new TeamDAOImpl();
    PersonDAO personDAO = new PersonDAOImpl();
    ContestDAO contestDAO = new ContestDAOImpl();



    @Override
    public boolean saveContest(Contest contest) {
        contestDAO.saveContest(contest);
        return true;
    }

    @Override
    public boolean updateContest(Contest contest) {
        contestDAO.updateContest(contest);
        return true;
    }


    @Override
    public boolean promoteTeam(Team team, Contest fromContest, Contest toContest) {
        if (!(contestDAO.isExists(fromContest)))  //if source contest doesn't exist
            return false;

        if (!(contestDAO.isExists(toContest))) //if destination contest doesn't exist
            return false;

        if (fromContest.getNextContest() == null) { //if contest doesn't have any next contest
            fromContest.setNextContest(toContest);
            contestDAO.updateContest(fromContest);
        }

        if (!(fromContest.getNextContest().equals(toContest))) //if contests doesn't have connection
            return false;

        if (!(teamDAO.isExists(team))) //if team doesn't exist
            return false;

        if (!(team.getAttendsContest().equals(fromContest))) //if team doesn't attend source contest
            return false;

        Team newTeam = new Team(team);
        newTeam.setIsCloneOf(team);
        newTeam.setAttendsContest(toContest);
        teamDAO.saveTeam(newTeam);
        return true;
    }

    //TODO promote tým lze jen pokud datum narození contestanta
    //je menší než 1995 (ci vek mladsi nez.. napr 20) a pokud je tym accepted and ma rank
    //TODO registrace je možná jen v čase od do
    @Override
    public boolean registerTeam(Team team, Set<Person> teamMembers, Contest contest, Person coach) {
        if (!contestDAO.isExists(contest))
            contestDAO.saveContest(contest);

        if (!contest.isIsregistrationopen())
            return false;

        Date currentDate = new Date();

        if (currentDate.before(contest.getRegistrationfrom())) return false;
        if (currentDate.after(contest.getRegistrationtill())) return false;


        if (!teamDAO.isExists(team))
            teamDAO.saveTeam(team);


        for (Person person : teamMembers) {
            Person persondb = personDAO.getPersonByEmail(person.getEmail());
            if (persondb == null) { //if there is NO person with suitable email — save such person
                personDAO.savePerson(person);
                team.setTeamMembers(person);
            } else {
                team.setTeamMembers(persondb);
            }
        }

        team.setAttendsContest(contest);
        teamDAO.updateTeam(team);

        Person couchdb = personDAO.getPersonByEmail(coach.getEmail());
        if (couchdb == null) {
            personDAO.savePerson(coach);
            coach.setIsCoachOfTeams(team);
        } else {
            coach = couchdb;
            coach.setIsCoachOfTeams(team);
        }
        personDAO.updatePerson(coach);

        return true;
    }

    //TODO add checking for year
    @Override
    public boolean setManager(Contest contest, Person manager) {
        if (personDAO.isExists(manager)) {
            if (personDAO.getPersonsTeam(manager).size() == 0) { //if person doesn't have team
                manager.setIsManagerOfContest(contest);
                contestDAO.updateContest(contest);
                personDAO.updatePerson(manager);
                return true;
            } else
                return false;
        } else {
            if (!(contestDAO.isExists(contest)))
                contestDAO.saveContest(contest);

            manager.setIsManagerOfContest(contest);
            contestDAO.updateContest(contest);
            personDAO.savePerson(manager);
            return true;
        }
    }
}
