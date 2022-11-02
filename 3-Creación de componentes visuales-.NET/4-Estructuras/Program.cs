using _4_Estructuras;
using System.Linq.Expressions;

internal class Program
{
    private static void Main(string[] args)
    {

        GestorFicheros gf = new GestorFicheros();

        string archivoStr = gf.leerArchivo("D:\\Archivos\\Repositorios\\yaredDAD\\3-Creación de componentes visuales-.NET\\4-Estructuras\\datos_temperaturas.csv");

        string[] lineas = archivoStr.Split("\n");

        List<decimal> datosTemperaturasMaximos = new List<decimal>();
        List<decimal> datosTemperaturasMinimas = new List<decimal>();
        List<decimal> datosTemperaturasMedias = new List<decimal>();
        try
        {
            foreach (string linea in lineas)
            {
              
                string[] datos = linea.Split(",");
                
                datosTemperaturasMaximos.Add(Math.Round(decimal.Parse(datos[1].Replace(".", ",")), 2));
                datosTemperaturasMinimas.Add(Math.Round(decimal.Parse(datos[2].Replace(".", ",")), 2));
                datosTemperaturasMedias.Add(Math.Round(decimal.Parse(datos[3].Replace(".", ",")), 2));
            
            }
        }catch(Exception e){ }

        Console.WriteLine("La media aritmética de las temperaturas máximas es: " + Math.Round((datosTemperaturasMaximos.Sum()/datosTemperaturasMaximos.Count), 2));
        Console.WriteLine("La media aritmética de las temperaturas mínimas es: " + Math.Round((datosTemperaturasMinimas.Sum()/datosTemperaturasMinimas.Count), 2));
        Console.WriteLine("La media aritmética de las temperaturas medias es: " + Math.Round((datosTemperaturasMedias.Sum()/datosTemperaturasMedias.Count), 2));

        Console.WriteLine("La temperatura máxima mas frecuente ha sido: " + Decimal.Truncate(datosTemperaturasMaximos.GroupBy(x => x).OrderByDescending(x => x.Count()).First().Key));
        Console.WriteLine("La temperatura mínima mas frecuente ha sido: " + Decimal.Truncate(datosTemperaturasMinimas.GroupBy(x => x).OrderByDescending(x => x.Count()).First().Key));
        Console.WriteLine("La temperatura media mas frecuente ha sido: " + Decimal.Truncate(datosTemperaturasMedias.GroupBy(x => x).OrderByDescending(x => x.Count()).First().Key));



    }
}