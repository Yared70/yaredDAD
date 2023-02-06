using System;
using System.Collections.Generic;
using System.Linq;
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

namespace Calculadora
{

   
    public enum OperacionSeleccionada
    {
        Suma,
        Resta,
        Multiplicacion,
        Division
    };

   
    public class Calcular
    {
        public static double Suma(double n1, double n2)
        {
            return n1 + n2;
        }
        public static double Resta(double n1, double n2)
        {
            return n1 - n2;
        }
        public static double Multiplicacion(double n1, double n2)
        {
            return n1 * n2;
        }
        public static double Division(double n1, double n2)
        {
            
            if (n2 == 0)
            {
                MessageBox.Show("No se puede dividir entre 0", "Error", MessageBoxButton.OK, MessageBoxImage.Error);
                return 0;
                
            }

            return n1 / n2;
        }

    }

    public partial class Estandar : Page
    {
        double numeroAnterior, resultado;
        OperacionSeleccionada operacionSeleccionada;
  
        public Estandar()
        {
            InitializeComponent();
        }
       
        private void operacionBoton_Click(object sender, RoutedEventArgs e)
        {
            if (double.TryParse(resultadoLabel.Content.ToString(), out numeroAnterior))
            {

                resultadoLabel.Content = numeroAnterior;

            }
          
            operacionSeleccionada = sender == btnMultiplicar ? OperacionSeleccionada.Multiplicacion : operacionSeleccionada;
            operacionSeleccionada = sender == btnDividir ? OperacionSeleccionada.Division : operacionSeleccionada;
            operacionSeleccionada = sender == btnSumar ? OperacionSeleccionada.Suma : operacionSeleccionada;
            operacionSeleccionada = sender == btnRestar ? OperacionSeleccionada.Resta : operacionSeleccionada;

            switch (operacionSeleccionada.ToString())
            {
                case "Suma": labelHistorial.Content = numeroAnterior + " +"; break;
                case "Resta": labelHistorial.Content = numeroAnterior + " -"; break;
                case "Division": labelHistorial.Content = numeroAnterior + " ÷"; break;
                case "Multiplicacion": labelHistorial.Content = numeroAnterior + " x"; break;
            }


        }

  
        private void numeroBoton_Click(object sender, RoutedEventArgs e)
        {

            int valorSeleccionado = int.Parse((sender as Button).Content.ToString());
          


            if( resultadoLabel.Content.ToString() == "0" || resultadoLabel.Content.ToString() == numeroAnterior.ToString())
            {
                resultadoLabel.Content = $"{valorSeleccionado}";
            }
            else
            {
                resultadoLabel.Content = $"{resultadoLabel.Content}{valorSeleccionado}";
            }


        }

        private void btnAC_Click(object sender, RoutedEventArgs e)
        {
           
            resultadoLabel.Content = "0";
            labelHistorial.Content = "0";
        }

     
        private void btnModulo_Click(object sender, RoutedEventArgs e)
        {
            if (double.TryParse(resultadoLabel.Content.ToString(), out numeroAnterior))
            {
                numeroAnterior = numeroAnterior / 100;
                resultadoLabel.Content = $"{numeroAnterior}";

            }
        }
      
        private void btnComa_Click(object sender, RoutedEventArgs e)
        {
       
            resultadoLabel.Content = resultadoLabel.Content.ToString().Contains(",") ? resultadoLabel.Content : $"{resultadoLabel.Content},";
            labelHistorial.Content = resultadoLabel.Content.ToString();
        }

       
        private void btnDelete_Click(object sender, RoutedEventArgs e)
        {
            if (!resultadoLabel.Content.Equals(""))
            {
                String textoOriginal = resultadoLabel.Content.ToString();
                String textonuevo = textoOriginal.Remove(textoOriginal.Length - 1);

                resultadoLabel.Content = textonuevo;
                labelHistorial.Content = resultadoLabel.Content;
            }
            else
            {
                resultadoLabel.Content = "0";
                labelHistorial.Content = resultadoLabel.Content;
            }

        }

        private void MenuItem_ImageFailed(object sender, ExceptionRoutedEventArgs e)
        {

        }


        private void btnIgual_Click(object sender, RoutedEventArgs e)
        {
            double numeroNuevo;
            if (double.TryParse(resultadoLabel.Content.ToString(), out numeroNuevo))
            {
                switch (operacionSeleccionada)
                {
                    case OperacionSeleccionada.Suma:
                        resultado = Calcular.Suma(numeroAnterior, numeroNuevo);

                        break;
                    case OperacionSeleccionada.Resta:
                        resultado = Calcular.Resta(numeroAnterior, numeroNuevo);
                        break;
                    case OperacionSeleccionada.Multiplicacion:
                        resultado = Calcular.Multiplicacion(numeroAnterior, numeroNuevo);
                        break;
                    case OperacionSeleccionada.Division:
                        resultado = Calcular.Division(numeroAnterior, numeroNuevo);
                        break;
                }
            }
            resultadoLabel.Content = $"{resultado}";
            labelHistorial.Content = "";

        }
    }
}


