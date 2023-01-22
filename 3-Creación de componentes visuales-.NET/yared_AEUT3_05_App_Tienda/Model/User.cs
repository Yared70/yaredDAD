using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class User
    {

        int id_staff;
        string password;
        string role;

        public User(int id_staff, string password, string role)
        {
            this.id_staff = id_staff;
            this.password = password;
            this.role = role;
        }

        public int Id_staff { get => id_staff; set => id_staff = value; }
        public string Password { get => password; set => password = value; }
        public string Role { get => role; set => role = value; }
    }
}
