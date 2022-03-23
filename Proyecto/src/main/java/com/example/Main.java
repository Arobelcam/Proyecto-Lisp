package com.example;

/**
 * Universidad Del Valle de Guatemala
 * @author Aaron Beltrán 21092
 * @author Raul Sanchez 21401
 * @author Roberto Rios 20979
 * @author Luis Montenegro  21699
 * Clase principal
 * Fecha de finalizacion 22/03/2022
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import com.example.models.Logica;


public class Main {
    public static void main(String[] args) throws Exception {
        //Se pide al usuario que ingrese el nombre de un archivo.lisp para poder realizar la lectura linea por linea
        Scanner read = new Scanner(System.in);
        System.out.println("Ingrese el nombre del archivo Lisp:");
        String nombreArchivo = read.next();

        //Se convierten los elementos del archivo.lisp en elementos de un arraylist
        ArrayList<String> archivo = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(
                    Paths.get(nombreArchivo + ".lisp"),
                    StandardCharsets.UTF_8
            );
            lines.forEach(archivo::add);
        } catch (IOException e ){
            System.out.println("Ha ocurrido un error, archivo no encontrado");
        }

        //Se realiza la instancia de las clases logica y el interprete de lisp para poder cumplir con el modelo vista controlador
        Logica logica = new Logica(archivo);
        LISP interprete = new LISP(logica);
    }
}
