package goncharuk.ross.dao;

import goncharuk.ross.model.Router;

import java.util.List;

public interface RouterDao {

    Router findById (int id);
    Router findByMac (String mac);
    void save(Router router);
    void deleteByMac(String mac);
    List<Router> findAllRouters();
}
