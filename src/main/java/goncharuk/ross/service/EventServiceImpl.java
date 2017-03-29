package goncharuk.ross.service;

import goncharuk.ross.dao.EventDao;
import goncharuk.ross.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    public Event findById(int id) {
        return eventDao.findById(id);
    }

    public Event findByName(String name) {
        return eventDao.findByName(name);
    }

    public void saveEvent(Event event) {
        eventDao.save(event);
    }

    public void updateEvent(Event event) {
        Event entity = eventDao.findById((int)event.getId());
        if (entity != null){
            entity.setName(event.getName());
            entity.setLocation(event.getLocation());
            entity.setDateFrom(event.getDateFrom());
            entity.setDateTo(event.getDateTo());
            entity.setRouterList(event.getRouterList());
        }
    }

    public void deleteEventByName(String name) {
        eventDao.deleteByName(name);
    }

    public List<Event> findAllEvents() {
        return eventDao.findAllEvents();
    }

    public boolean isEventNameUnique(Integer id, String name) {
        Event event = findByName(name);
        return (event == null||((id != null)&&(event.getId() == id)));
    }
}
