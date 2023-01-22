
internal class Program
{
    private static void Main(string[] args)
    {
        Console.WriteLine("Introduce el texto que quieras:");

        string texto = Console.ReadLine();
        string[] textoSeparado = texto.Split(' ');

        Console.WriteLine("----- Menú -----");
        Console.WriteLine("1.- Obtener cuantas veces se repite una palabra \n" +
            "2.- Modificar una palabra del texto introducido anteriormente \n" +
            "3.- Borrar una palabra del texto \n" +
            "4.- Salir");

        var introducido = int.Parse(Console.ReadLine());

        while (introducido != 4)
        {

            switch (introducido)
            {
                case 1:
                    Console.WriteLine("Introduce la palabra para saber cuantas veces se repite");
                    var palabra = Console.ReadLine();
                    int contador = 0;
                    for (int i = 0; i < textoSeparado.Length; i++)
                    {
                        if (textoSeparado[i].Equals(palabra))
                        {
                            contador++;
                        }
                    }
                    Console.WriteLine("La palabra " + palabra + " se repite " + contador + " veces en la frase que has introducido");
                    break;
                case 2:
                    Console.WriteLine("Introduce la palabra que quieres modificar del texto introducido");
                    var palabraModificar = Console.ReadLine();
                    Console.WriteLine("¿Cuál es la nueva palabra?");
                    var palabraNueva = Console.ReadLine();
                    for (int i = 0; i < textoSeparado.Length; i++)
                    {
                        if (textoSeparado[i].Equals(palabraModificar))
                        {
                            textoSeparado[i] = palabraNueva;
                        }
                    }
                    Console.WriteLine("Texto nuevo: \n" +
                        string.Join(" ", textoSeparado));
                    break;
                case 3:
                    Console.WriteLine("Introduce la palabra que quieres eliminar del texto introducido");
                    var palabraEliminar = Console.ReadLine();
                    List<String> textoLista = new List<string>();
                    foreach (String palabraStr in textoSeparado)
                    {
                        if (!palabraStr.Equals(palabraEliminar))
                        {
                            textoLista.Add(palabraStr);
                        }
                    }
                    Console.WriteLine("Texto nuevo: \n" +
                        string.Join(" ", textoLista));
                    break;
                case 4:
                    break;
                default:
                    break;

            }
            Console.WriteLine("Introduce un numero entre 1 y 4");
            introducido = int.Parse(Console.ReadLine());
        
        }

    }
}