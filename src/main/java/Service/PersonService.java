package Service;

import persistance.model.Contest;
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


}
