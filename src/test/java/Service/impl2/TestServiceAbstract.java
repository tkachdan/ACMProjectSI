package Service.impl2;

import Service.impl.ContestServiceImpl;
import Service.impl.PersonServiceImpl;
import Service.impl.TeamServiceImpl;
import persistance.dao.impl.ContestDAOImpl;
import persistance.dao.impl.PersonDAOImpl;
import persistance.dao.impl.TeamDAOImpl;

/**
 * Created by tkachdan on 13-Dec-14.
 */
public abstract class TestServiceAbstract {
    protected static ContestServiceImpl contestService = new ContestServiceImpl();
    protected static TeamServiceImpl teamService = new TeamServiceImpl();
    protected static PersonServiceImpl personService = new PersonServiceImpl();

    protected static PersonDAOImpl personDAO = new PersonDAOImpl();
    protected static TeamDAOImpl teamDAO = new TeamDAOImpl();
    protected static ContestDAOImpl contestDAO = new ContestDAOImpl();
}
