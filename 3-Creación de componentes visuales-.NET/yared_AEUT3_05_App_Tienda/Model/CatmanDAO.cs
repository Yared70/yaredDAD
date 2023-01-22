using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;

namespace Model
{
    public class CatmanDAO
    {
        private static DBConnection dataSource;


        public CatmanDAO()
        {
                dataSource = DBConnection.getInstance();
        }



        public List<Catman> findAll()
        {


            MySqlConnection connection = null;
            MySqlCommand mySqlCommand = null;
            MySqlDataReader mysqlReader = null;
            string sql = "Select * from catman";
            List<Catman> catman = new List<Catman>();

            try
            {
                connection = dataSource.getConnection();
                connection.Open();

                mySqlCommand = new MySqlCommand(sql, connection);

                mysqlReader = mySqlCommand.ExecuteReader();

                


                while (mysqlReader.Read())
                {
                    int id = Convert.ToInt32(mysqlReader["id"]);
                    string name = mysqlReader["name"].ToString();
                    string category = mysqlReader["category"].ToString();
                    string description = mysqlReader["description"].ToString();
                    double price = Convert.ToDouble(mysqlReader["price"]);
                    int quantity = Convert.ToInt32(mysqlReader["quantity"]);


                    catman.Add(new Catman(id, name, category, description, price, quantity));



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

            return catman;



        }

        public List<String> getCategories()
        {


            MySqlConnection connection = null;
            MySqlCommand mySqlCommand = null;
            MySqlDataReader mysqlReader = null;
            string sql = "Select distinct category from catman";
            List<String> categories = new List<String>();

            try
            {
                connection = dataSource.getConnection();
                connection.Open();

                mySqlCommand = new MySqlCommand(sql, connection);

                mysqlReader = mySqlCommand.ExecuteReader();




                while (mysqlReader.Read())
                {
                    
                    string category = mysqlReader["category"].ToString();
                   

                    categories.Add(category);



                }
                categories.Add("All");
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

        public List<Catman> findAllByCategories(string categoryFind)
        {


            MySqlConnection connection = null;
            MySqlCommand mySqlCommand = null;
            MySqlDataReader mysqlReader = null;
            string sql = "Select * from catman where category = '" + categoryFind + "'";
            List<Catman> catman = new List<Catman>();

            try
            {
                connection = dataSource.getConnection();
                connection.Open();

                mySqlCommand = new MySqlCommand(sql, connection);

                mysqlReader = mySqlCommand.ExecuteReader();




                while (mysqlReader.Read())
                {
                    int id = Convert.ToInt32(mysqlReader["id"]);
                    string name = mysqlReader["name"].ToString();
                    string category = mysqlReader["category"].ToString();
                    string description = mysqlReader["description"].ToString();
                    double price = Convert.ToDouble(mysqlReader["price"]);
                    int quantity = Convert.ToInt32(mysqlReader["quantity"]);


                    catman.Add(new Catman(id, name, category, description, price, quantity));



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

            return catman;



        }

        public void updateQuantity(int id, int cantidadNueva)
        {
            MySqlConnection connection = null;
            MySqlCommand mySqlCommand = null;
            MySqlDataReader mysqlReader = null;
            string sql = "update catman set quantity = " + cantidadNueva + " where id = " +id;

            try
            {
                connection = dataSource.getConnection();
                connection.Open();

                mySqlCommand = new MySqlCommand(sql, connection);

                mySqlCommand.ExecuteNonQuery();
                

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

