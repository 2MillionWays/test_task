package goncharuk.ross.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "ROUTER")
public class Router {

    @Id
    @Column(name = "ROUTER_ID")
    @GeneratedValue
    private long id;

    @NotEmpty
    @Column(name = "AP_MAC", unique = true, nullable = false)
    private String apMac;

    @NotEmpty
    @Column(name = "ROUTER_NAME", nullable = false)
    private String routerName;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApMac() {
        return apMac;
    }

    public void setApMac(String apMac) {
        this.apMac = apMac;
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
