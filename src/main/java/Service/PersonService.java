package Service;

import persistance.model.Contest;
import persistance.model.Person;
import persistance.model.State;
import persistance.model.Team;

/**
 * Created by tkachdan on 05-Dec-14.
 */
public interface PersonService {
    public void registerTeamToContest(Team team, Contest contest);

    public void setTeamState(Team team, State state);

    public void setTeamName(Team team, String name);

    public void setTeamRank(Team team, int rank);

    public void savePerson(Person person);

    public void insertPersonIntoTeam(Team team, Person person);

    public void updatePerson(Person person);

    public void deletePerson(Person person);

}
