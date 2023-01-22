using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Controller;

namespace AEUT3_05_App_Tienda
{
    public partial class ViewLogin : Form
    {

        Controlador controlador;

        public ViewLogin()
        {
            InitializeComponent();
            this.FormBorderStyle = FormBorderStyle.FixedToolWindow;
            controlador = new Controlador();
            this.KeyDown += new KeyEventHandler(submit);
            this.CenterToScreen();
            

            
        }

        private void btnEnviar_Click(object sender, EventArgs e)
        {

            int id_staff = int.Parse(txtNumber.Text);
            string password = txtPassword.Text;

            bool v = controlador.comprobarLogin(id_staff, password);

            if (!v)
            {

                MessageBox.Show("Login incorrecto. Comprueba el id y password introducidos", "Login", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtNumber.Text = "";
                txtPassword.Text = "";

            }
            else
            {
                ViewCategories viewCategories = new ViewCategories(controlador);
                viewCategories.ShowDialog();
                txtNumber.Text = "";
                txtPassword.Text = "";
            }

        }

        private void submit(object sender, KeyEventArgs e)
        {

            if(e.KeyCode == Keys.Enter)
            {

                MessageBox.Show("ha funcionado");

            }

        }
    }
}
