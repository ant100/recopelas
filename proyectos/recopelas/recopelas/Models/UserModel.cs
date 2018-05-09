using System;
using System.Runtime.Serialization;
      
namespace recopelas.Models
{
    public class UserModel
    {
		public string email { get; set; }
        public string lastnameFather { get; set; }
        public string lastnameMother { get; set; }
        public string name { get; set; }
        public string document { get; set; }
        public string password { get; set; }
        public string phone { get; set; }
        public string address { get; set; }
    }
}
