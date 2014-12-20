package Service;

import persistance.model.Person;
import persistance.model.State;
import persistance.model.Team;

/**
 * Created by tkachdan on 05-Dec-14.
 */
public interface TeamService {
    public boolean addMember(Team team, Person member);

    public boolean deleteMember(Team team, Person member);

    public boolean setCoach(Team team, Person person);

    public boolean setTeamState(Team team, State state);

}
