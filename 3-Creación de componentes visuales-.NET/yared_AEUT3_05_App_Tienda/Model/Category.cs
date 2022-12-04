using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    public class Category
    {

        int id_category;
        string description;

        public Category(int id_category, string description)
        {
            this.id_category = id_category;
            this.description = description;
        }

        public int Id_category { get => id_category; set => id_category = value; }
        public string Description { get => description; set => description = value; }
    }
}
