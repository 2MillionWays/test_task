package goncharuk.ross.service;

import goncharuk.ross.model.Event;

import java.util.List;

public interface EventService {

    Event findById(int id);
    Event findByName(String name);
    void saveEvent(Event event);
    void updateEvent(Event event);
    void deleteEventByName(String name);
    List<Event> findAllEvents();
    boolean isEventNameUnique(Integer id, String name);
}
