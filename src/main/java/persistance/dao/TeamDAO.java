package persistance.dao;

import persistance.model.Team;

import java.util.List;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public interface TeamDAO {

    public void saveTeam(Team team);

    public Team getTeam(int id);

    public List<Team> getAllTeams();

    public void updateTeam(Team Team);

    public void deleteTeam(int id);

    public boolean isExists(Team team);
}