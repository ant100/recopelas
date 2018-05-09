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
    public class RegisterController : Controller
    {
        public ActionResult Index()
        {
            return View (new RegisterModel());
        }

        [HttpPost]
		public ActionResult Index(RegisterModel register)
        {
            if (!ModelState.IsValid){
                return View(register);
            }
			var client = new RestClient("http://localhost:8080/recopelas-ws/api/security/");          
			var request = new RestRequest("register", Method.POST); 
			string jsonToSend = JsonConvert.SerializeObject(register);
			request.AddParameter("application/json; charset=utf-8", jsonToSend, ParameterType.RequestBody);
            request.RequestFormat = DataFormat.Json;          

			IRestResponse response = client.Execute(request);
            var content = response.Content;

			ViewData["Data"] = content;
			return View();
        }
    }
}
