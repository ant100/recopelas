using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace recopelas.Controllers
{
    public class RecommenderController : Controller
    {
        public ActionResult Index()
        {
            return View ();
        }

        public ActionResult Detail()
        {
            return View();
        }
    }
}
