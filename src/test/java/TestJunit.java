import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import persistance.dao.ContestDAO;
import persistance.dao.impl.ContestDAOImpl;
import persistance.model.Contest;
import persistance.utlis.HibernateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by tkachdan on 30-Nov-14.
 */
public class TestJunit {
    public static Session session = new HibernateUtils().getSessionFactory().openSession();

    Date date = new Date();
    java.sql.Date hugoBirth = new java.sql.Date(date.getTime());
    java.sql.Date carriageDate = new java.sql.Date(date.getTime());
    java.sql.Date dateFrom = new java.sql.Date(date.getTime());
    java.sql.Date dateTill = new java.sql.Date(date.getTime());

    @Test
    public void testContestDAO() {
        ContestDAO data = new ContestDAOImpl();
        Contest contest = new Contest("local contest", 5, carriageDate, dateFrom, dateTill, true);

        data.saveContest(contest);

        String hql = "from Contest ";

        Query query = session.createQuery(hql);
        List results = query.list();
        Contest contestRes = (Contest) results.get(results.size() - 1);
        Assert.assertEquals(contest.getId(), contestRes.getId());
        Assert.assertEquals(contest.getName(), contestRes.getName());

        Contest contest1 = new Contest("sec contest", 5, carriageDate, dateFrom, dateTill, true);

        data.saveContest(contest1);

        List<Contest> allContest = data.getAllContests();
        for (Contest c : allContest) {
            System.out.println(c);
        }


    }


}
