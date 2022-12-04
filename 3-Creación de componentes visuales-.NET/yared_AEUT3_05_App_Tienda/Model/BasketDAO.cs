using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class BasketDAO
    {

        private static DBConnection dataSource;

        public BasketDAO()
        {

            dataSource = DBConnection.getInstance();

        }

        public void save(int id, string name, double quantity)
        {

            MySqlConnection connection = null;
            MySqlCommand mySqlCommand = null;
            MySqlDataReader mysqlReader = null;
            string sql = "insert into basket values (@id, @name, @total)";

            try
            {
                connection = dataSource.getConnection();
                connection.Open();

                using (mySqlCommand = new MySqlCommand(sql, connection))
                {

                    mySqlCommand.Parameters.AddWithValue("@id", id);
                    mySqlCommand.Parameters.AddWithValue("@name", name);
                    mySqlCommand.Parameters.AddWithValue("@total", quantity);



                    mySqlCommand.ExecuteNonQuery();
                }
             
            }
            catch (Exception e)
            {
                Console.WriteLine("error " + e.ToString());
            }
            finally
            {
                if (mySqlCommand != null) mySqlCommand.Dispose();
                if (mysqlReader != null) mysqlReader.Dispose();
                if (connection != null) connection.Close();
            }


        }

    }
}
