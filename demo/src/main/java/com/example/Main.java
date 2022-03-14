package com.example;

import com.example.models.Programa;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/*
Aaron Beltran 21092
Raul Sanchez 18212
Roberto Rios 18049
Luis Montenegro Lisp
 */


/**
 * Clase principal
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //Para leer el archivo
        Scanner read = new Scanner(System.in);
        System.out.println("Ingrese el nombre del archivo Lisp:");
        String nombreArchivo = read.next();
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

        Programa programa = new Programa(archivo);
        InterpreteLISP interprete = new InterpreteLISP(programa);
    }
}