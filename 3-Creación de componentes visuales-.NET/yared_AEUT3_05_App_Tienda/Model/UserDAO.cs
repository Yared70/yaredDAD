using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class UserDAO
    {

        private static DBConnection dataSource;

        public UserDAO()
        {

            dataSource = DBConnection.getInstance();

        }

        public User FindById(int id)
        {

            MySqlConnection connection = null;
            MySqlCommand mySqlCommand = null;
            MySqlDataReader mysqlReader = null;
            string sql = "Select * from users where id_staff = " + id;
            User usuarioDDBB = null;

            try
            {
                connection = dataSource.getConnection();
                connection.Open();

                mySqlCommand = new MySqlCommand(sql, connection);

                mysqlReader = mySqlCommand.ExecuteReader();


                while (mysqlReader.Read())
                {
                    int id_staff = Convert.ToInt32(mysqlReader["id_staff"]);
                    string password = mysqlReader["password"].ToString();
                    string role = mysqlReader["role"].ToString();

                    usuarioDDBB = new User(id_staff, password, role);

                    

                }
            }catch(Exception e)
            {
                Console.WriteLine("error " + e.ToString());
            }
            finally
            {
                if (mySqlCommand != null) mySqlCommand.Dispose();
                if (mysqlReader != null) mysqlReader.Dispose();
                if (connection != null) connection.Close();
            }

            return usuarioDDBB;



        }

    }
}
