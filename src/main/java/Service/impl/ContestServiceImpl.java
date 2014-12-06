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
import persistance.model.State;
import persistance.model.Team;

import java.util.Set;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public class ContestServiceImpl implements ContestService {
    TeamDAO teamDAO = new TeamDAOImpl();
    PersonDAO personDAO = new PersonDAOImpl();
    ContestDAO contestDAO = new ContestDAOImpl();


    @Override
    public void registerTeam(Team team, Set<Person> teamMembers, Contest contest) {
        for (Person p : teamMembers) {
            //Person person = ;
            if (personDAO.getPerson(p.getId()) == null) {
                personDAO.savePerson(p);
            }
        }
        team.setTeamMembers(teamMembers);
        team.setAttendsContest(contest);
        teamDAO.updateTeam(team);
    }

    @Override
    public void changeTeamState(Team team, State state) {
        team.setState(state);
        teamDAO.updateTeam(team);
    }

    @Override
    public void promoteTeam(Team team, Contest contest) {
        Team newTeam = new Team(team);
        newTeam.setIsCloneOf(team);
        newTeam.setAttendsContest(contest);
        teamDAO.saveTeam(newTeam);
    }

    @Override
    public void changeCoach(Team team, Person person) {
        Set<Team> coachedTeams = person.getCoachedTeams();
        coachedTeams.add(team);
        personDAO.updatePerson(person);


    }

    @Override
    public void changeStatusOfRegistration(Contest contest, boolean status) {
        contest.setIsregistrationopen(status);
        contestDAO.updateContest(contest);

    }

    @Override
    public void setCoach(Team team, Person person) {
        Set<Team> coachedTeams = person.getCoachedTeams();
        coachedTeams.add(team);
        personDAO.updatePerson(person);
    }
}
