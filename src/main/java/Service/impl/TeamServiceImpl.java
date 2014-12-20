package Service.impl;

import Service.TeamService;
import persistance.dao.ContestDAO;
import persistance.dao.PersonDAO;
import persistance.dao.TeamDAO;
import persistance.dao.impl.ContestDAOImpl;
import persistance.dao.impl.PersonDAOImpl;
import persistance.dao.impl.TeamDAOImpl;
import persistance.model.Person;
import persistance.model.State;
import persistance.model.Team;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tkachdan on 13-Dec-14.
 */
public class TeamServiceImpl implements TeamService {
    TeamDAO teamDAO = new TeamDAOImpl();
    PersonDAO personDAO = new PersonDAOImpl();
    ContestDAO contestDAO = new ContestDAOImpl();


    @Override
    public boolean addMember(Team team, Person member) {
        if (team.getTeamMembers().size() == 3)
            return false;

        team.setTeamMembers(member);
        personDAO.updatePerson(member);
        teamDAO.updateTeam(team);
        return true;
    }

    @Override
    public boolean deleteMember(Team team, Person member) {
        if (team.getTeamMembers().size() == 0)
            return false;

        if (!(team.getTeamMembers().contains(member)))
            return false;

        Set<Person> newMembers = new HashSet<Person>();
        for (Person currentMember : team.getTeamMembers())
            if (!(currentMember.equals(member)))
                newMembers.add(currentMember);

        team.setTeamMembers(newMembers);
        personDAO.updatePerson(member);
        teamDAO.updateTeam(team);
        return true;
    }

    @Override
    public boolean setCoach(Team team, Person person) {
        if (personDAO.getPersonsTeam(person).size() > 0)
            return false;

        person.setIsCoachOfTeams(team);
        personDAO.updatePerson(person);
        return true;
    }

    @Override
    public boolean setTeamState(Team team, State state) {
        if (!(state.equals(State.ACCEPTED))) {
            team.setState(state);
            teamDAO.updateTeam(team);
            return true;
        }

        if (team.getTeamMembers().size() > 0) {
            team.setState(state);
            teamDAO.updateTeam(team);
            return true;
        } else
            return false;
    }
}
