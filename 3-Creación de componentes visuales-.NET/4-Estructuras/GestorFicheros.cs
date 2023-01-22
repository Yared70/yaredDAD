using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _4_Estructuras
{
    internal class GestorFicheros
    {

        public GestorFicheros() { }


        public string leerArchivo(string archivoPath)
        {
            string archivoStr = "";
            string line = "";

            try
            {
                StreamReader sr = new StreamReader(archivoPath);
                line = sr.ReadLine();

                while(line != null)
                {
                    if (line.Contains("2022"))
                    {
                        archivoStr += line + "\n";
                    }
                    line = sr.ReadLine();

                }

                sr.Close();

            }catch(Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

            return archivoStr;
            


        }


    }
}
