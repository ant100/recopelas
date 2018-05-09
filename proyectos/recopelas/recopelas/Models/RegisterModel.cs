using System;
using System.ComponentModel.DataAnnotations;
using System.Runtime.Serialization;
      
namespace recopelas.Models
{
    public class RegisterModel
    {
		[Required]
		public string email { get; set; }
		[Required]        
        public string lastnameFather { get; set; }
		[Required]
        public string lastnameMother { get; set; }
		[Required]
        public string name { get; set; }
		[Required]
        public string document { get; set; }
		[Required]
        public string password { get; set; }
		[Required]
        public string phone { get; set; }
		[Required]
        public string address { get; set; }
    }
}
