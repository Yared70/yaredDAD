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
    public partial class FormAdd : Form
    {

        Controlador controlador;
        DataGridViewRow fila = null;


        public FormAdd(Controlador controlador, DataGridViewRow fila)
        {
            InitializeComponent();
            this.controlador = controlador;
            this.FormBorderStyle = FormBorderStyle.FixedToolWindow;
            this.CenterToScreen();
            this.fila = fila;
            lblId.Text = "ID: " + fila.Cells[0].Value.ToString();
            lblName.Text = "Name: " + fila.Cells[1].Value.ToString();
            lblCantidad.Text = "Quantity: " + fila.Cells[5].Value.ToString();

        }

        private void button1_Click(object sender, EventArgs e)
        {

            int cantidadPedida = int.Parse(txtCantidad.Text.ToString());
            int cantidadActual = int.Parse(fila.Cells[5].Value.ToString());

            if(cantidadPedida > cantidadActual)
            {

                MessageBox.Show("No puedes solicitar mas cantidad de la disponible actualmente",
                    "Informacion", MessageBoxButtons.OK, MessageBoxIcon.Information);

            }
            else
            {

                int cantidadFinal = cantidadActual - cantidadPedida;
                double precio = double.Parse(fila.Cells[4].Value.ToString());
                double total = cantidadPedida * precio;
                int id = int.Parse(fila.Cells[0].Value.ToString());
                string name = fila.Cells[1].Value.ToString();
                controlador.saveCompra(id, name, total);
                controlador.updateQuantity(id, cantidadFinal);

            }

        }
    }
}
