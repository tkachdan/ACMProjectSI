package Service;

import persistance.model.Contest;
import persistance.model.Person;
import persistance.model.State;
import persistance.model.Team;

import java.util.Set;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public interface ContestService {
    public void registerTeam(Team team, Set<Person> teamMembers, Contest contest);

    public void changeTeamState(Team team, State state);

    public void promoteTeam(Team team, Contest contest);

    public void changeCoach(Team team, Person person);

}
