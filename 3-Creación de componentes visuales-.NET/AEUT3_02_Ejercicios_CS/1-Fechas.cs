
internal class Program
{
    private static void Main(string[] args)
    {

        DateTime fecha1 = DateTime.Now;

        DateTime fechaDDC = new DateTime(2023, 4, 30);

        Console.WriteLine("Faltan " + (fechaDDC - fecha1).Days + " días para el próximo Dia de Canarias(30 Mayo)");

        Console.WriteLine("Introduce la fecha para ver cuantos dias quedan para el siguiente dia de Canarías despues de esa fecha");

        String nuevaFecha = Console.ReadLine();

        DateTime fechaIntroducida = Convert.ToDateTime(nuevaFecha);
        DateTime nuevaDDC;

        if(fechaIntroducida.Month > 5)
        {
            int year = fechaIntroducida.Year;
            nuevaDDC = new DateTime((year + 1), 5, 30);
        }
        else {

            nuevaDDC = new DateTime(fechaIntroducida.Year, 5, 30);
        }

        Console.WriteLine("Faltan " + (nuevaDDC - fechaIntroducida).Days + " días desde la fecha introducida hasta su siguiente Dia de Canarias");



    }
}