package goncharuk.ross.service;

import goncharuk.ross.dao.RouterDao;
import goncharuk.ross.model.Router;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("routerService")
@Transactional
public class RouterServiceImpl implements RouterService {

    private RouterDao routerDao;

    public Router findById(int id) {
        return routerDao.findById(id);
    }

    public Router findByMac(String mac) {
        return routerDao.findByMac(mac);
    }

    public void saveRouter(Router router) {
        routerDao.save(router);
    }

    public void updateRouter(Router router) {
        Router entity = routerDao.findById((int)router.getId());
        if(entity != null){
            entity.setApMac(router.getApMac());
            entity.setRouterName(router.getRouterName());
            entity.setEvent(router.getEvent());
        }
    }

    public void deleteRouterByMac(String mac) {
        routerDao.deleteByMac(mac);
    }

    public List<Router> findAllRouters() {
        return routerDao.findAllRouters();
    }

    public boolean isRouterMacUnique(Integer id, String mac) {
        Router router = findByMac(mac);
        return (router == null||((id != null)&&(router.getId() == id)));
    }
}
