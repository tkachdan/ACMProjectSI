package persistance.utlis;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by tkachdan on 15-Nov-14.
 * <p>
 * Utils for work with database
 */
public class Service {

    private HibernateUtils hibernateUtil;
    private SessionFactory sessionFactory;
    private Session session;

    public Service() {
        this.hibernateUtil = new HibernateUtils();
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }


    /*


    public void printAllTeams(){
        session = sessionFactory.openSession();
        List<Team> list = session.createCriteria(Team.class).addOrder(Order.asc("name")).list();
        for (Team entity : list){
            System.out.println(entity);
        }

    }
    */
}
