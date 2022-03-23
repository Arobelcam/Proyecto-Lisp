package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import com.example.models.Logica;
import com.example.models.StackVector;

/**
 * Unit test for simple App.
 * 
 * Todas las pruebas fueron completadas con exito.
 * 
 * @author Aaron Beltran
 * @author Paulo Sánchez
 * @author Luis Montenegro
 * @author Roberto Rios
 * @version 1.0 pruebas para el interprete finalizado 22/02/2022
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {

        String nombreArchivo = "programa";
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

        Logica logica = new Logica(archivo);
        LISP interprete = new LISP(logica);

    }

    @Test
    void stackVectorTest() {
        StackVector<String> stackVectorTest = new StackVector<>();
        assertEquals(true, stackVectorTest.empty());
        stackVectorTest.push("prueba");
        stackVectorTest.push("prueba2");
        stackVectorTest.push("prueba3");
        assertEquals(false, stackVectorTest.empty());
        assertEquals(3, stackVectorTest.size());
        assertEquals("prueba3", stackVectorTest.peek());

    }

    @Test
    void logicaTest() {
        ArrayList<String> prueba = new ArrayList<>();
        prueba.add("prueba");
        prueba.add("prueba2");
        prueba.add("prueba3");
        Logica logic = new Logica(prueba);
        assertEquals("pruebaprueba2prueba3", logic.generadorString());

    }
    
}
