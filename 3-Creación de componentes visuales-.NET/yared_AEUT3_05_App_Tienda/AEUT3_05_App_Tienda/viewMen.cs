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
    public partial class viewMen : Form
    {

        Controlador controlador;
        DataGridViewRow fila = null;

        public viewMen(Controlador controlador)
        {
            InitializeComponent();
            this.controlador = controlador;
            this.FormBorderStyle = FormBorderStyle.FixedToolWindow;
            rellenarTabla();
            dataTable.ReadOnly = true;
            rellenarLista();

        }

        public void rellenarTabla()
        {

            this.dataTable.DataSource = this.controlador.getCatman();

            DataGridViewColumnCollection columns = this.dataTable.Columns;
            
            for (int i = 0; i < columns.Count; i++)
            {
                columns[i].AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
                
            }

        }

        public void rellenarLista()
        {
            
            this.listFiltrar.DataSource = this.controlador.getCatmanCategories();

        }

        private void btnFiltrar_Click(object sender, EventArgs e)
        {

            object selectedItem = this.listFiltrar.SelectedItem;

            if (!selectedItem.ToString().Equals("All"))
            {

                this.dataTable.DataSource = this.controlador.findByCategory(selectedItem.ToString());

            }
            else
            {
                this.dataTable.DataSource = this.controlador.getCatman();

            }

            
            

        }

        private void selectCell(object sender, DataGridViewCellEventArgs e)
        {

            fila = dataTable.CurrentRow;

        }

        private void btnView_Click(object sender, EventArgs e)
        {

            MessageBox.Show(
                "ID: " + fila.Cells[0].Value.ToString() + "\n" +
                "Name: " + fila.Cells[1].Value.ToString() + "\n" +
                "Category: " + fila.Cells[2].Value.ToString() + "\n" +
                "Description: " + fila.Cells[3].Value.ToString() + "\n" +
                "Price: " + fila.Cells[4].Value.ToString() + "\n" +
                "Quantity: " + fila.Cells[5].Value.ToString() + "\n",
                "Info", MessageBoxButtons.OK,
                MessageBoxIcon.Information

                );

        }

        private void btnAdd_Click(object sender, EventArgs e)
        {

            FormAdd form = new FormAdd(controlador, fila);
            form.ShowDialog();
            rellenarTabla();

        }
    }
}
