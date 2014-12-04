package persistance.dao.impl;

import org.hibernate.Session;
import persistance.dao.ContestDAO;
import persistance.model.Contest;
import persistance.utlis.HibernateUtils;

import java.util.List;

/**
 * Created by tkachdan on 04-Dec-14.
 */
public class ContestDAOImpl implements ContestDAO {
    Session session = new HibernateUtils().getSessionFactory().openSession();


    public void saveContest(Contest contest) {
        session.beginTransaction();
        session.save(contest);
        session.getTransaction().commit();


    }

    @Override
    public Contest getContest(int id) {
        session.beginTransaction();
        Contest contest = null;

        contest = (Contest) session
                .createQuery("from Contest contest where id = :contestID")
                .setLong("contestID", id).uniqueResult();

        session.getTransaction().commit();

        return contest;
    }

    @Override
    public List<Contest> getAllContests() {
        session.beginTransaction();
        List contests = session.createQuery("FROM Contest ").list();

        return contests;
    }


    public void updateContest(Contest contest) {
        session.beginTransaction();


        session.update(contest);
        session.getTransaction().commit();

    }

    @Override
    public void deleteContest(int id) {
        session.beginTransaction();

        Contest contest = (Contest) session.load(Contest.class, id);
        session.delete(contest);
        session.getTransaction().commit();

    }
}