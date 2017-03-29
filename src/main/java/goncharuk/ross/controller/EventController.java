package goncharuk.ross.controller;

import goncharuk.ross.model.Event;
import goncharuk.ross.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/", "/eventslist"},method = RequestMethod.GET)
    public String listEvents(ModelMap modelMap){
        List<Event> events = eventService.findAllEvents();
        modelMap.addAttribute("events", events);
        return "listevents";
    }

    @RequestMapping(value = {"/newevent"},method = RequestMethod.GET)
    public String newEvent(ModelMap modelMap){
        Event event = new Event();
        modelMap.addAttribute("event", event);
        modelMap.addAttribute("edit",false);
        return "addEvent";
    }

    @RequestMapping(value = {"/newevent"}, method = RequestMethod.POST)
    public String saveEvent(@Valid Event event, BindingResult result, ModelMap modelMap){

        if (result.hasErrors()){
            return "addEvent";
        }

        if (!eventService.isEventNameUnique((int)event.getId(),event.getName())){
            FieldError nameError = new FieldError("event","name",messageSource.getMessage("non.unique.name",
                    new String[]{event.getName()}, Locale.getDefault()));
            result.addError(nameError);
            return "addEvent";
        }

        eventService.saveEvent(event);

        modelMap.addAttribute("success","Event "+ event.getName()+ " at "+ event.getLocation()
        +" from " + event.getDateFrom()+ " to "+ event.getDateTo()+ " added successfully!");

        return "addEventSuccess";
    }

    @RequestMapping(value = {"/edit/event/{name}"}, method = RequestMethod.GET)
    public String editEvent(@PathVariable String name, ModelMap modelMap){
        Event event = eventService.findByName(name);
        modelMap.addAttribute("event", event);
        modelMap.addAttribute("edit", true);
        return "addEvent";
    }

    @RequestMapping(value = {"/edit/event/{name}"}, method = RequestMethod.POST)
    public String updateEvent(@Valid Event   event, BindingResult result, ModelMap modelMap, @PathVariable String name){

        if (result.hasErrors()){
            return "addEvent";
        }

        if(!eventService.isEventNameUnique((int)event.getId(),event.getName())){
            FieldError nameError = new FieldError("event","name", messageSource.getMessage("non.unique.name",
                    new String[]{event.getName()}, Locale.getDefault()));
            result.addError(nameError);
            return "addEvent";
        }

        eventService.updateEvent(event);

        modelMap.addAttribute("success","Event "+ event.getName()+ " at "+ event.getLocation()
                +" updated successfully!");

        return "addEventSuccess";
    }

    @RequestMapping(value = {"/delete/event/{name}"}, method = RequestMethod.GET)
    public String deleteEvent(@PathVariable String name){
        eventService.deleteEventByName(name);
        return "redirect:/listevents";
    }
}