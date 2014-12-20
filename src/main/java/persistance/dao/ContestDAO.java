package persistance.dao;

import persistance.model.Contest;

import java.util.List;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public interface ContestDAO {

    public void saveContest(Contest contest);

    public Contest getContest(int id);

    public List<Contest> getAllContests();

    public void updateContest(Contest contest);

    public void deleteContest(int id);

    public boolean isExists(Contest contest);


}

