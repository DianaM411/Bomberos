package com.diana;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        try {
            //la carpeta Bomberos
            String pwd = System.getProperty("user.dir");//ruta directorio actual de trabajo
            String rutaAbsoluta = pwd + File.separator + "Bomberos";// File.separator funciona para Windows y Linux
            File carpetaBomberos = new File(rutaAbsoluta);

            //verifico que la carpeta existe y leo los archivos de dentro
            if (carpetaBomberos.exists()) {
                File[] contenidoCarpeta = carpetaBomberos.listFiles();
                for (File i : contenidoCarpeta) {
                    System.out.println(i); //imprimo las rutas de los archivos de dentro
                    FileReader fr = new FileReader(i);
                    BufferedReader bf = new BufferedReader(fr);

                    String linea;
//                    Scanner myReader = new Scanner(i);
                    while ((linea =bf.readLine())!= null) {
                        String data = linea;
                        System.out.println(data);
                    }
                    bf.close();
                }
            }



        } catch (Exception ex) {//manejamos excepciones
            ex.printStackTrace();
        } finally {
            lector.close();//cerramos el scanner
        }
    }
}
