package com.diana;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        try {
            String rutaRelativa = "Bomberos" + File.separator + "2017.csv";
            String pwd = System.getProperty("user.dir");//ruta directorio actual de trabajo
            String rutaAbsoluta = pwd + File.separator + rutaRelativa;// File.separator funciona para Windows y Linux


            File ficheroALeer = new File(rutaAbsoluta);

            if (ficheroALeer.isFile()) {
                lector = new Scanner(ficheroALeer);//leemos el fichero
                System.out.println("Lo leo");
            }

        } catch (Exception ex) {//manejamos excepciones
            ex.printStackTrace();
        } finally {
            lector.close();//cerramos el scanner
        }
    }
}
