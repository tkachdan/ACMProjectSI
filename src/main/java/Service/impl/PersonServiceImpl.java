package Service.impl;

import Service.PersonService;
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

/**
 * Created by tkachdan on 05-Dec-14.
 */
public class PersonServiceImpl implements PersonService {
    TeamDAO teamDAO = new TeamDAOImpl();
    PersonDAO personDAO = new PersonDAOImpl();
    ContestDAO contestDAO = new ContestDAOImpl();

    /**
     * for ContestManager person
     *
     * @param team
     * @param contest
     */
    @Override
    public void registerTeamToContest(Team team, Contest contest) {
        team.setAttendsContest(contest);
        teamDAO.updateTeam(team);
    }

    @Override
    public void setTeamState(Team team, State state) {
        team.setState(state);
        teamDAO.updateTeam(team);
    }

    @Override
    public void setTeamName(Team team, String name) {

    }

    @Override
    public void setTeamRank(Team team, int rank) {

    }

    @Override
    public void savePerson(Person person) {

    }

    @Override
    public void insertPersonIntoTeam(Team team, Person person) {

    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Person person) {

    }
}
