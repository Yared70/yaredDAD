using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class CategoryDAO
    {

        private static DBConnection dataSource;

        public CategoryDAO()
        {

            dataSource = DBConnection.getInstance();

        }

        public List<Category> findAll()
        {


            MySqlConnection connection = null;
            MySqlCommand mySqlCommand = null;
            MySqlDataReader mysqlReader = null;
            string sql = "Select * from categories";
            List<Category> categories = new List<Category>();

            try
            {
                connection = dataSource.getConnection();
                connection.Open();

                mySqlCommand = new MySqlCommand(sql, connection);

                mysqlReader = mySqlCommand.ExecuteReader();


                while (mysqlReader.Read())
                {
                    int id_category = Convert.ToInt32(mysqlReader["id_category"]);
                    string description = mysqlReader["description"].ToString();
                    

                    categories.Add(new Category(id_category, description));



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

            return categories;



        }

    }
}
