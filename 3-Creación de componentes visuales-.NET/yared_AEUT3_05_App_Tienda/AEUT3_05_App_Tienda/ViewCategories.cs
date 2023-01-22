using Controller;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AEUT3_05_App_Tienda
{
    public partial class ViewCategories : Form
    {

        Controlador controlador;

        public ViewCategories(Controlador controlador)
        {

            InitializeComponent();
            this.FormBorderStyle = FormBorderStyle.FixedToolWindow;
            this.controlador = controlador;
            
            loadCategories();


        }

        public void loadCategories()
        {

            List<Model.Category> categories = controlador.getCategories();

            for (int i = 0; i < categories.Count; i++)
            {


                Button btn = new Button();
                btn.Margin = new Padding(4, 3, 4, 3);
                btn.BackColor = Color.Black;
                btn.Location = new Point(30, 30 + i * 60);
                btn.Size = new Size(125, 54);
                btn.Text = categories[i].Description.ToString();
                btn.ForeColor = Color.White;
                btn.Name = "btn" + categories[i].Description.ToString();
                this.Controls.Add(btn);

                if (btn.Name.Equals("btnman"))
                {

                    btn.Click += new System.EventHandler(this.selectMan);
                                    

                }

            }

        }

        public void selectMan(object sender, System.EventArgs e)
        {

            viewMen viewMen = new viewMen(controlador);
            viewMen.Show();

        }
    }
}
