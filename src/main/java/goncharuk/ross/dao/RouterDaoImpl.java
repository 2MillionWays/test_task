package goncharuk.ross.dao;

import goncharuk.ross.model.Router;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("routerDao")
public class RouterDaoImpl extends AbstractDao<Integer,Router> implements RouterDao {

    public Router findById(int id) {
        return getByKey(id);
    }

    public Router findByMac(String mac) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("apMac",mac));
        return (Router)criteria.uniqueResult();
    }

    public void save(Router router) {
        persist(router);
    }

    public void deleteByMac(String mac) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("apMac", mac));
        Router router = (Router)criteria.uniqueResult();
        delete(router);
    }

    @SuppressWarnings("unchecked")
    public List<Router> findAllRouters() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("routerName"));
        return (List<Router>)criteria.uniqueResult();
    }
}
