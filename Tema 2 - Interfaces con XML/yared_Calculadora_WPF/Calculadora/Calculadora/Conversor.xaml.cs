using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Calculadora.Model;
using Newtonsoft.Json;


namespace Calculadora
{

    public partial class Conversor : Page
    {
        Double rateActual,unrateActual,rateSegundo,unrateSegundo,numeroValor, valorCambioMoneda;
        String nameCode,nameCodeSegundo;

        public Conversor()
        {
            InitializeComponent();
            OutputJson();



        }
        public void OutputJson()
        {

            string json = System.IO.File.ReadAllText(@"usd.json");
           
            Dictionary<string, Moneda> jsonResulttodict = JsonConvert.DeserializeObject<Dictionary<string, Moneda>>(json);
            

            

            List<Moneda> monedas = new List<Moneda>();
            Moneda moneda = new Moneda("USD", "Dolar", "1", jsonResulttodict["gbp"].date, "1");
            monedas.Add(moneda);
            
            
            
            foreach(Moneda m in jsonResulttodict.Values){
                monedas.Add(m);

            }



            monedas = monedas.OrderBy(x => x.name).ToList();
            comboPrimeraMoneda.DisplayMemberPath = "name";
            comboSegundaMoneda.DisplayMemberPath = "name";
            foreach (Moneda m in monedas)
            {
                
                comboPrimeraMoneda.Items.Add(m);
                comboSegundaMoneda.Items.Add(m);

            }

            comboPrimeraMoneda.SelectedItem = monedas[44];
            comboSegundaMoneda.SelectedItem = monedas[38];
        }

        private void SegundoDisplay_TextChanged(object sender, TextChangedEventArgs e)
        {
   
            String aux = SegundoDisplay.Text;
            numeroValor = Convert.ToDouble(aux);
            double resultado = valorCambioMoneda * numeroValor;
            PrimerDisplay.Text = ""+Math.Round(resultado, 2);
          
        }

        private void btnUno_KeyDown(object sender, KeyEventArgs e)
        {

        }

        private void Page_KeyDown(object sender, KeyEventArgs e)
        {
           
        }


        private void btnAC_Click(object sender, RoutedEventArgs e)
        {
          
            SegundoDisplay.Text = "0";
        }

        private void btnDelete_Click(object sender, RoutedEventArgs e)
        {
            if (SegundoDisplay.Text.Length > 1)
            {
                if (!SegundoDisplay.Text.Equals(""))
                {
                    String textoOriginal = SegundoDisplay.Text.ToString();
                    String textonuevo = textoOriginal.Remove(textoOriginal.Length - 1);

                    SegundoDisplay.Text = textonuevo;
                }
                else
                {
                    SegundoDisplay.Text = "0";
                }

            }
            else
            {
                SegundoDisplay.Text = "0";
            }
            
            
        }


        private void btnComa_Click(object sender, RoutedEventArgs e)
        {
            
            SegundoDisplay.Text = SegundoDisplay.Text.ToString().Contains(",") ? SegundoDisplay.Text : $"{SegundoDisplay.Text},";
            
        }

        private void numeroBoton_Click(object sender, RoutedEventArgs e)
        {
            int valorSeleccionado = int.Parse((sender as Button).Content.ToString());
          
            SegundoDisplay.Text = SegundoDisplay.Text.ToString() == "0" ? $"{valorSeleccionado}" : $"{SegundoDisplay.Text}{valorSeleccionado}";
            
        }

        private void comboSegundaMoneda_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Moneda m = (Moneda)comboSegundaMoneda.SelectedItem;

            nameCodeSegundo = m.code;

            String s = m.rate.Replace('.', ',');
            rateSegundo = Convert.ToDouble(s);
            String a = m.inverseRate.Replace('.', ',');
            unrateSegundo = Convert.ToDouble(a);
            nameCode = m.code;

            SegundoDisplay.Text = "" + 1;
            label2.Content = "" + nameCodeSegundo;

             valorCambioMoneda = rateActual * unrateSegundo;
            PrimerDisplay.Text = "" + Math.Round(valorCambioMoneda, 2);

            setLabelChangeData();

           
        }

        private void comboPrimeraMoneda_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            Moneda m = (Moneda)comboPrimeraMoneda.SelectedItem;

            String s = m.rate.Replace('.', ',');
            rateActual = Convert.ToDouble(s);
            String a = m.inverseRate.Replace('.', ',');
            unrateActual = Convert.ToDouble(a);
            nameCode = m.code;

            valorCambioMoneda = rateActual * unrateSegundo;

            label1.Content = nameCode;
            PrimerDisplay.Text =""+ Math.Round(valorCambioMoneda, 2);

            if(comboSegundaMoneda.SelectedItem != null)
            {
                setLabelChangeData();
            }


        }

        private void setLabelChangeData()
        {

            Moneda mArriba = (Moneda)comboSegundaMoneda.SelectedItem;
            Moneda mAbajo = (Moneda)comboPrimeraMoneda.SelectedItem;

            double cambioMoneda = rateActual * unrateSegundo;
            double valor = 1 * cambioMoneda;
            

            labelCambioActual.Content = "1 " + mArriba.code + " = " + Math.Round(valor, 4) + " " + mAbajo.code;


            
        }







        


    }
}
