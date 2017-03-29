package goncharuk.ross.dao;

import goncharuk.ross.model.Event;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("eventDao")
public class EventDaoImpl extends AbstractDao<Integer,Event> implements EventDao {

    public Event findByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name",name));
        Event event =(Event) criteria.uniqueResult();
        if (event != null){
            Hibernate.initialize(event.getRouterList());
        }
        return event;
    }

    public Event findById(int id) {
        Event event = getByKey(id);
        if (event != null){
            Hibernate.initialize(event.getRouterList());
        }
        return event;
    }

    public void save(Event event) {
        persist(event);
    }

    public void deleteByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name",name));
        Event event = (Event) criteria.uniqueResult();
        delete(event);
    }

    @SuppressWarnings("unchecked")
    public List<Event> findAllEvents() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return (List<Event>)criteria.list();
    }
}
