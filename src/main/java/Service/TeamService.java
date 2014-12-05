package Service;

import persistance.model.Contest;
import persistance.model.Person;
import persistance.model.Team;

/**
 * Created by tkachdan on 05-Dec-14.
 */
public interface TeamService {
    public void addTeamMember(Person person);

    public void attendContest(Team team, Contest contest);

    public void setRank(Team team, int rank);


}
