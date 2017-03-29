package goncharuk.ross.dao;

import goncharuk.ross.model.Event;

import java.util.List;

public interface EventDao {

    Event findById(int id);
    Event findByName(String name);
    void save(Event event);
    void deleteByName(String name);
    List<Event> findAllEvents();
}
