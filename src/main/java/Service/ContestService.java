package Service;

import persistance.model.Contest;
import persistance.model.Person;
import persistance.model.Team;

import java.util.Set;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public interface ContestService {

    public boolean saveContest(Contest contest);

    public boolean updateContest(Contest contest);

    public boolean promoteTeam(Team team, Contest fromContest, Contest toContest);

    public boolean registerTeam(Team team, Set<Person> teamMembers, Contest contest, Person coach);

    public boolean setManager(Contest contest, Person manager);

}
