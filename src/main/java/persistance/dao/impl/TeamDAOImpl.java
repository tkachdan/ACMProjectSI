package persistance.dao.impl;

/**
 * Created by tkachdan on 04-Dec-14.
 */

import org.hibernate.Session;
import persistance.dao.TeamDAO;
import persistance.model.Team;
import persistance.utlis.HibernateUtils;

import java.util.List;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public class TeamDAOImpl implements TeamDAO {
    Session session = new HibernateUtils().getSessionFactory().openSession();


    public void saveTeam(Team team) {
        session.beginTransaction();
        session.save(team);
        session.getTransaction().commit();


    }

    @Override
    public Team getTeam(int id) {
        session.beginTransaction();
        Team team = null;

        team = (Team) session
                .createQuery("from Team team where id = :teamID")
                .setLong("teamID", id).uniqueResult();

        session.getTransaction().commit();

        return team;
    }

    @Override
    public List<Team> getAllTeams() {
        session.beginTransaction();
        List teams = session.createQuery("FROM Team ").list();

        return teams;
    }


    public void updateTeam(Team team) {
        session.beginTransaction();
        session.update(team);
        session.getTransaction().commit();


    }

    @Override
    public void deleteTeam(int id) {
        session.beginTransaction();

        Team team = (Team) session.load(Team.class, id);
        session.delete(team);
        session.getTransaction().commit();

    }
}