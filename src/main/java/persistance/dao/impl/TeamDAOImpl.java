package persistance.dao.impl;

/**
 * Created by tkachdan on 04-Dec-14.
 */

import org.hibernate.Session;
import persistance.dao.TeamDAO;
import persistance.model.Team;
import persistance.utlis.Service;

import java.util.List;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public class TeamDAOImpl implements TeamDAO {


    public void saveTeam(Team team) {
        Session session = Service.getSession();
        session.beginTransaction();
        session.save(team);
        session.getTransaction().commit();
        session.close();


    }

    public boolean isExists(Team team) {
        if (getTeam(team.getId()) == null) {
            return false;
        } else {
            return true;
        }

    }


    @Override
    public Team getTeam(int id) {
        Session session = Service.getSession();
        session.beginTransaction();
        Team team = null;

        team = (Team) session
                .createQuery("from Team team where id = :teamID")
                .setLong("teamID", id).uniqueResult();

        session.getTransaction().commit();
        session.close();
        return team;
    }

    @Override
    public List<Team> getAllTeams() {
        Session session = Service.getSession();
        session.beginTransaction();
        List teams = session.createQuery("FROM Team ").list();
        session.close();
        return teams;
    }


    public void updateTeam(Team team) {
        Session session = Service.getSession();
        session.beginTransaction();
        session.update(team);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void deleteTeam(int id) {
        Session session = Service.getSession();
        session.beginTransaction();

        Team team = (Team) session.load(Team.class, id);
        session.delete(team);
        session.getTransaction().commit();
        session.close();

    }
}