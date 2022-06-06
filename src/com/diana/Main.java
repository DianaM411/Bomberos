package com.diana;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

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

                    FileReader fr = new FileReader(i);
                    BufferedReader bf = new BufferedReader(fr);

                    String linea;
//                    Scanner myReader = new Scanner(i);
                    while ((linea = bf.readLine()) != null) {
                        String data = linea;
                        String parte[] = data.split(";");//dividimos cada linea en partes separadas por ;
                        totalFuego += Double.parseDouble(parte[3]);
                        totalConstr += Double.parseDouble(parte[4]);
                        totalSalva += Double.parseDouble(parte[5]);
                        totalAgua += Double.parseDouble(parte[6]);
                        totalDiversos += Double.parseDouble(parte[7]);
                        totalSinInterv += Double.parseDouble(parte[8]);
                        totalServVarios += Double.parseDouble(parte[9]);
                    }
                    bf.close();
                }
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

                //sacamos la temparatura minima (la primera posicion)
                mayorMenorIncidencias.get(claves.get(0));
                //sacamos la temparatura maxima (ultima posicion)
                mayorMenorIncidencias.get(claves.get(claves.size() - 1));
            }

        } catch (Exception ex) {//manejamos excepciones
            ex.printStackTrace();
        } finally {
            lector.close();//cerramos el scanner
        }
    }
}