package goncharuk.ross.controller;

import goncharuk.ross.model.Router;
import goncharuk.ross.service.RouterService;
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
@RequestMapping("/routers/")
public class RouterController {

    @Autowired
    private RouterService routerService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/listrouters"},method = RequestMethod.GET)
    public String listRouters(ModelMap map){
        List<Router> routers = routerService.findAllRouters();
        map.addAttribute("routers",routers);
        return "routersList";
    }

    @RequestMapping(value = {"/newrouter"},method = RequestMethod.GET)
    public String newRouter(ModelMap map){
        Router router = new Router();
        map.addAttribute("router",router);
        map.addAttribute("edit",false);
        return "addRouter";
    }

    @RequestMapping(value = {"/newrouter"}, method = RequestMethod.POST)
    public String saveRouter(@Valid Router router, BindingResult result, ModelMap map){

        if (result.hasErrors()){
            return "addRouter";
        }

        if (!routerService.isRouterMacUnique((int)router.getId(),router.getApMac())){
            FieldError errorMac = new FieldError("router","apMac",messageSource.getMessage("non.unique.mac",
                    new String[]{router.getApMac()}, Locale.getDefault()));
            result.addError(errorMac);
            return "addRouter";
        }

        routerService.saveRouter(router);

        map.addAttribute("success", "Route "+ router.getRouterName()+" "+ router.getApMac()
                +" added successfully!");

        return "addRouterSuccess";
    }

    @RequestMapping(value = {"/edit/router/{mac}"}, method = RequestMethod.GET)
    public String editRoute(@PathVariable String mac, ModelMap map){
        Router router = routerService.findByMac(mac);
        map.addAttribute("route",router);
        map.addAttribute("edit",true);
        return "addRouter";
    }

    @RequestMapping(value = {"/edit/router/{mac}"}, method = RequestMethod.POST)
    public String updateRouter(@Valid Router router, BindingResult result, ModelMap map, @PathVariable String mac){

        if (result.hasErrors()){
            return "addRouter";
        }

        if (!routerService.isRouterMacUnique((int)router.getId(),router.getApMac())){
            FieldError errorMac = new FieldError("router","apMac", messageSource.getMessage("non.unique.mac",
                    new String[]{router.getApMac()},Locale.getDefault()));
            result.addError(errorMac);
            return "addRouter";
        }

        routerService.updateRouter(router);
        map.addAttribute("success","Router "+router.getRouterName()+" "+router.getApMac()
                +" updated successfully!");
        return "addRouterSuccess";
    }

    @RequestMapping(value = {"/delete/router/{mac}"}, method = RequestMethod.GET)
    public String deleteRouter(@PathVariable String mac){
        routerService.deleteRouterByMac(mac);
        return "redirect:/listrouters";
    }
}
