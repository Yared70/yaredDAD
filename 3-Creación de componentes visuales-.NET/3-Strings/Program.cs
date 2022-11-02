internal class Program
{
    private static void Main(string[] args)
    {
        Console.WriteLine("Introduce una base de cadena de ADN (A,C,G,T) o una F para finalizar");
        string? letra = Console.ReadLine().ToUpper();
        List<String> basesIntroducidas = new List<string>();
        Dictionary<string, int> contadorBases = new Dictionary<string, int>();

        // Solicitamos que nos vayan introduciendo las bases por teclado

        if (!letra.Equals("F"))
        {
            contadorBases.Add("A", 0);
            contadorBases.Add("C", 0);
            contadorBases.Add("T", 0);
            contadorBases.Add("G", 0);

            while (!letra.Equals("F"))
            {
                basesIntroducidas.Add(letra);
                contadorBases.TryGetValue(letra, out int contador);
                contadorBases[letra] = contador + 1;

                Console.WriteLine("Introduce una base de cadena de ADN (A,C,G,T) o una F para finalizar");
                letra = Console.ReadLine().ToUpper();
                 
            }
        }


        // Contamos cuantas veces se repite cada base

        string basesResultantes = "";

        foreach (var dato in contadorBases)
        {
            basesResultantes += dato.Value + " " + dato.Key + ", ";
        }

        // Calcular base que mas veces seguidas se repite

        string joinBasesIntroducidas = string.Join("", basesIntroducidas);

        Dictionary<string, int> contadorBasesRepetidas = new Dictionary<string, int>();
        string letraFor = "";
        string letraForSiguiente = "";
        int contadorRepetidas = 0;
        string cadenaRepetida = "";

        for (int i = 0; i < (joinBasesIntroducidas.Length -1); i++)
        {
            letraFor = joinBasesIntroducidas[i] + "";
            letraForSiguiente = joinBasesIntroducidas[i + 1] + "";
            int j = i + 1;
            if (letraFor.Equals(letraForSiguiente))
            {
                cadenaRepetida += letraFor;
                contadorRepetidas++;
                while (letraFor.Equals(letraForSiguiente))
                {
                    cadenaRepetida += letraForSiguiente;
                    contadorRepetidas++;
                    j++;
                    letraForSiguiente = joinBasesIntroducidas[j] + ""; 

                }

                bool yaExiste = contadorBasesRepetidas.TryAdd(cadenaRepetida, contadorRepetidas);
                cadenaRepetida = "";
                contadorRepetidas = 0;

            }
        }

        string? MaxKey = contadorBasesRepetidas.FirstOrDefault(x => x.Value == contadorBasesRepetidas.Values.Max()).Key;
        int maxValue = contadorBasesRepetidas[MaxKey];


        // Comprobar el codon que predomina

        Dictionary<string, int> contadorCodonRepetidos = new Dictionary<string, int>();
        string primeraBase = "";
        string segundaBase = "";
        string terceraBase = "";
        string codon = "";

        for (int i = 0; i < (joinBasesIntroducidas.Length); i++)
        {

            if ((i + 1) < joinBasesIntroducidas.Length && (i+2) < joinBasesIntroducidas.Length)
            {
                primeraBase = joinBasesIntroducidas[i] + "";
                segundaBase = joinBasesIntroducidas[i + 1] + "";
                terceraBase = joinBasesIntroducidas[i + 2] + "";
                codon = primeraBase + segundaBase + terceraBase;

                bool existeCodon = contadorCodonRepetidos.TryGetValue(codon, out int numeroRep);
                if (existeCodon)
                {
                    contadorCodonRepetidos[codon] = numeroRep + 1;
                }
                else
                {
                    contadorCodonRepetidos.Add(codon, 1);
                }

            }


        }

        string codonPredominante = contadorCodonRepetidos.FirstOrDefault(x => x.Value == contadorCodonRepetidos.Values.Max()).Key;

        // Salida Final

        Console.WriteLine(string.Join("", basesIntroducidas) + " contiene:");
        Console.WriteLine("> " + basesResultantes);
        Console.WriteLine("> la base que mas veces seguidas se repite es la " + MaxKey[0] + " con " + maxValue + " repeticiones seguidas");
        Console.WriteLine("> el codon que predomina es " + codonPredominante);
    }
}