package goncharuk.ross.service;

import goncharuk.ross.model.Router;

import java.util.List;

public interface RouterService {

    Router findById(int id);
    Router findByMac(String mac);
    void saveRouter(Router router);
    void updateRouter(Router router);
    void deleteRouterByMac(String mac);
    List<Router> findAllRouters();
    boolean isRouterMacUnique(Integer id, String mac);
}
