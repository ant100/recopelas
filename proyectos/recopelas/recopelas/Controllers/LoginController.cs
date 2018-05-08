using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using recopelas.Models;
using RestSharp;
using Newtonsoft.Json;
using System.Net;
using System.IO;

namespace recopelas.Controllers
{
    public class LoginController : Controller
    {
        public ActionResult Index()
        {
			ViewData["Data"] = "";
            return View ();
        }
      
        [HttpPost]
		public ActionResult Index(LoginModel login)
        {
			var client = new RestClient("http://localhost:8080/recopelas-ws/api/security/");          
			var request = new RestRequest("login", Method.POST); 
			string jsonToSend = JsonConvert.SerializeObject(login);          
            
			request.AddParameter("application/json; charset=utf-8", jsonToSend, ParameterType.RequestBody);
            request.RequestFormat = DataFormat.Json;          

			IRestResponse response = client.Execute(request);
            var content = response.Content;

			ViewData["Data"] = content;
			return View();
        }
    }
}