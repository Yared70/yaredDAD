using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Model
{
    class Basket
    {

        int id;
        string name;
        double total;

        public Basket(int id, string name, double total)
        {
            this.id = id;
            this.name = name;
            this.total = total;
        }

        public int Id { get => id; set => id = value; }
        public string Name { get => name; set => name = value; }
        public double Total { get => total; set => total = value; }
    }
}
