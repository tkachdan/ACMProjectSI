package persistance.dao.impl;

import org.hibernate.Session;
import persistance.dao.ContestDAO;
import persistance.model.Contest;
import persistance.utlis.Service;

import java.util.List;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public class ContestDAOImpl implements ContestDAO {



    public void saveContest(Contest contest) {
        Session session = Service.getSession();
        session.beginTransaction();
        session.save(contest);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public Contest getContest(int id) {
        Session session = Service.getSession();
        session.beginTransaction();
        Contest contest = null;

        contest = (Contest) session
                .createQuery("from Contest contest where id = :contestID")
                .setLong("contestID", id).uniqueResult();

        session.getTransaction().commit();
        session.close();
        return contest;
    }

    @Override
    public List<Contest> getAllContests() {
        Session session = Service.getSession();
        session.beginTransaction();
        List contests = session.createQuery("FROM Contest ").list();
        session.clear();
        return contests;
    }


    public void updateContest(Contest contest) {
        Session session = Service.getSession();
        session.beginTransaction();


        session.update(contest);
        session.getTransaction().commit();
        session.close();
    }

    public boolean isExists(Contest contest) {
        Session session = Service.getSession();
        session.beginTransaction();
        if (getContest(contest.getId()) == null) {
            session.close();
            return false;
        } else {
            session.close();
            return true;
        }

    }

    @Override
    public void deleteContest(int id) {
        Session session = Service.getSession();
        session.beginTransaction();

        Contest contest = (Contest) session.load(Contest.class, id);
        session.delete(contest);
        session.getTransaction().commit();
        session.close();
    }
}