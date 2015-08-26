package cat.alkaid.intrastat.web;


import cat.alkaid.intrastat.service.ReportService;
import cat.alkaid.intrastat.web.auth.AuthResource;
import cat.alkaid.intrastat.web.auth.AuthSecurityInterceptor;
import cat.alkaid.intrastat.web.rest.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xavier on 8/07/15.
 */

@ApplicationPath("resources")
public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // Register my custom provider.
        //classes.add(SecurityRequestFilter.class);
        // Register resources.
        classes.add(cat.alkaid.intrastat.model.Proveedor.class);
        classes.add(cat.alkaid.intrastat.service.ProveedorService.class);
        classes.add(cat.alkaid.intrastat.web.rest.Proveedores.class);
        classes.add(ProveedorResource.class);
        classes.add(cat.alkaid.intrastat.model.Category.class);
        classes.add(cat.alkaid.intrastat.service.CategoryService.class);
        classes.add(cat.alkaid.intrastat.web.rest.Categories.class);
        classes.add(CategoryResource.class);
        classes.add(cat.alkaid.intrastat.model.Item.class);
        classes.add(cat.alkaid.intrastat.service.ItemService.class);
        classes.add(cat.alkaid.intrastat.web.rest.Items.class);
        classes.add(ItemResource.class);
        classes.add(cat.alkaid.intrastat.model.Periodo.class);
        classes.add(cat.alkaid.intrastat.service.PeriodoService.class);
        classes.add(cat.alkaid.intrastat.web.rest.Periodos.class);
        classes.add(PeriodoResource.class);
        classes.add(cat.alkaid.intrastat.model.Pais.class);
        classes.add(cat.alkaid.intrastat.service.PaisService.class);
        classes.add(cat.alkaid.intrastat.web.rest.Paises.class);
        classes.add(PaisResource.class);
        classes.add(AuthSecurityInterceptor.class);
        classes.add(cat.alkaid.intrastat.auth.AuthAccessElement.class);
        classes.add(cat.alkaid.intrastat.auth.AuthLoginElement.class);
        classes.add(cat.alkaid.intrastat.auth.AuthService.class);
        classes.add(AuthResource.class);
        classes.add(cat.alkaid.intrastat.model.User.class);
        classes.add(cat.alkaid.intrastat.service.UserService.class);
        classes.add(UserResource.class);
        classes.add(cat.alkaid.intrastat.web.rest.Users.class);
        classes.add(AuthSecurityInterceptor.class);
        classes.add(ReportResource.class);
        classes.add(ReportService.class);
        classes.add(cat.alkaid.intrastat.model.Transporte.class);
        classes.add(cat.alkaid.intrastat.service.TransporteService.class);
        classes.add(TransporteResource.class);
        return classes;
    }

}

