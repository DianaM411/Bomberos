package com.diana;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        //definimos variables
        double totalFuego = 0;
        double totalConstr = 0;
        double totalSalva = 0;
        double totalAgua = 0;
        double totalDiversos = 0;
        double totalSinInterv = 0;
        double totalServVarios = 0;
        double totalTodas = 0;

        HashMap<Double, String> mayorMenorIncidencias = new HashMap<Double, String>();

        try {
            //la carpeta Bomberos
            String pwd = System.getProperty("user.dir");//ruta directorio actual de trabajo
            String rutaAbsoluta = pwd + File.separator + "Bomberos";// File.separator funciona para Windows y Linux
            File carpetaBomberos = new File(rutaAbsoluta);

            //verifico que la carpeta existe y leo los archivos de dentro
            if (carpetaBomberos.exists()) {
                File[] contenidoCarpeta = carpetaBomberos.listFiles();
                for (File i : contenidoCarpeta) {

                    FileInputStream fis = new FileInputStream(i);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    String line = null;
                    int count = 0;


                    while ((line = br.readLine()) != null) { // read through file line by line
                        if (count != 0) { // count == 0 means the first line
                            String data = line;
                            String parte[] = data.split(";");//dividimos cada linea en partes separadas por ;
                            //sumamos todos los campos de cada columna
                            //menos la primera fila (nombres)
                            totalFuego += Double.parseDouble(parte[3]);
                            totalConstr += Double.parseDouble(parte[4]);
                            totalSalva += Double.parseDouble(parte[5]);
                            totalAgua += Double.parseDouble(parte[6]);
                            totalDiversos += Double.parseDouble(parte[7]);
                            totalSinInterv += Double.parseDouble(parte[8]);
                            totalServVarios += Double.parseDouble(parte[9]);
                            totalTodas += Double.parseDouble(parte[10]);
                        }
                        count++; // count increments as you read lines
                    }
                    br.close(); // do not forget to close the resources
                }
                //Calculamos la media de incidencias por ano: TOTAL / nr de anos(de archivos que hay en la carpeta)
                double mediaAnos = totalTodas / carpetaBomberos.list().length;

                // Anadir claves y valor al HashMap (tipoIncidencia, numeroTotal)
                mayorMenorIncidencias.put(totalFuego, "fuego");
                mayorMenorIncidencias.put(totalConstr, "danoConstr");
                mayorMenorIncidencias.put(totalSalva, "salvamento");
                mayorMenorIncidencias.put(totalAgua, "agua");
                mayorMenorIncidencias.put(totalDiversos, "diversos");
                mayorMenorIncidencias.put(totalSinInterv, "sinIntervencion");
                mayorMenorIncidencias.put(totalServVarios, "servVarios");

                ArrayList<Double> claves = new ArrayList<Double>();
                claves.addAll(mayorMenorIncidencias.keySet());
                //ordenamos los valores de menor a mayor
                Collections.sort(claves);

                // Imprimimos el Hashmap entero
                System.out.println("####################   HASHMAP   #####################");
                for (Double i : mayorMenorIncidencias.keySet()) {
                    System.out.println("key: " + i + " value: " + mayorMenorIncidencias.get(i));
                }

                System.out.println("#######################################################");

                //sacamos la temparatura minima (la primera posicion)
                System.out.println("Intervencion con menos salidas: " + mayorMenorIncidencias.get(claves.get(0)) + " , numero de salidas: " + claves.get(0));
                //sacamos la temparatura maxima (ultima posicion)
                System.out.println("Intervencion con mas salidas: " + mayorMenorIncidencias.get(claves.get(claves.size() - 1)) + " , numero de salidas: " + claves.get(claves.size() - 1));
                //Imprimimos la media de incidencia por ano
                System.out.println("Media incidencia por ano: " + mediaAnos);
                //Imprimimos la media de incidencia por mes
                System.out.println("Media incidencia por mes: " );
            }

        } catch (Exception ex) {//manejamos excepciones
            ex.printStackTrace();
        } finally {
            lector.close();//cerramos el scanner
        }
    }
}