using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Model;

namespace Controller
{
    public class Controlador
    {

        UserDAO userDao;
        CategoryDAO categoryDAO;
        CatmanDAO catmanDAO;
        BasketDAO basketDAO;
        public Controlador() {
        
            userDao = new UserDAO();
            categoryDAO = new CategoryDAO();
            catmanDAO = new CatmanDAO();
            basketDAO = new BasketDAO();
        }


        public Boolean comprobarLogin(int id_staff, string password)
        {


            User usuarioDDBB = userDao.FindById(id_staff);

            if(usuarioDDBB != null)
            {

                if (usuarioDDBB.Password.Equals(password))
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            else
            {
                return false;
            }
        }

        public List<Category> getCategories()
        {

            List<Category> categories = categoryDAO.findAll();

            return categories;


        }

        public List<Catman> getCatman()
        {


            List<Catman> catman = catmanDAO.findAll();

            return catman;

        }

        public List<String> getCatmanCategories()
        {


            List<String> categories = catmanDAO.getCategories();

            return categories;

        }


        public List<Catman> findByCategory(string categoryFind)
        {
            List<Catman> catman = catmanDAO.findAllByCategories(categoryFind);

            return catman;
        }

        public void saveCompra(int id, string name, double total)
        {

            basketDAO.save(id, name, total);

        }

        public void updateQuantity(int id, int cantidadNueva)
        {

            catmanDAO.updateQuantity(id, cantidadNueva);

        }
    }
}
