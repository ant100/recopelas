using System.Web.Mvc;
using System.Web.Routing;

namespace recopelas
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");
 
            routes.MapRoute(
                name: "Title",
                url: "titulo/{id}",
                defaults: new { controller = "Title", action = "Index", id = UrlParameter.Optional }
            );
			routes.MapRoute(
                name: "Register",
                url: "registrarse/",
                defaults: new { controller = "Register", action = "Index"}
            );
			routes.MapRoute(
                name: "Login",
                url: "ingresar/",
                defaults: new { controller = "Login", action = "Index" }
            );
            routes.MapRoute(
                name: "Default",
                url: "{controller}/{action}/{id}",
                defaults: new { controller = "Home", action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}
