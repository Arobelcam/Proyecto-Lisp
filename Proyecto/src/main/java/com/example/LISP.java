package com.example;
/**
 * LISP
 * Objetivo: Es la clase que se encarga de la mayor parte programatica del interprete, unifica todos los elementos de java collections utilizados
 * 
 * @author Aaron Beltran
 * @author Paulo Sánchez
 * @author Luis Montenegro
 * @author Roberto Rios
 * @version 1.0 finalizado 20/03/2022
 */
import com.example.models.Logica;
import com.example.models.StackVector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Toda la logica para interpretar las expresiones de lisp.
 */
public class LISP {

    private Logica programaprueba;
    private HashMap<String, StackVector> funciones = new HashMap<>();

    /**
     * Constructor de clase LISP
     * @param lecprograma programa ingresado
     */
    LISP(Logica lecprograma) {
        this.programaprueba = lecprograma;
        interpretar();
    }

    /**
     * Metodo interpretar
     * Ejecuta el archivo leido, elimina los comentarios, luego lo lee y por ultimo utiliza la recursividad
     */
    private void interpretar() {
        ejecutar(leerPrograma(limpiarPrograma()));
    }

    /**
     * Metodo limpairPrograma
     * Limpia el programa ingresadonque ha sido pasado por la lectura de archivos y se  eliminan los comentarios incluidos
     * @return La lectura del archivo de lisp
     */
    private StackVector<Object> limpiarPrograma() {
        StackVector<Object> stack = new StackVector<>();
        Logica log = new Logica();
        String programalimp;

        log.addCommand("(");
        for (String linea : this.programaprueba.getLines()) {
            //Se eliminan los comnetarios que se incluyen en el archivo de lisp
            if (linea.length() >= 1 && linea.charAt(0) != ';') {
                String nuevalinea = "";
                for (int i = 0; i < linea.length(); i++) {
                    if (linea.charAt(i) != ';') {
                        nuevalinea += linea.charAt(i);
                    } else {
                        break;
                    }
                }
                //Instrucciones sin comentarios
                log.addCommand(nuevalinea);
            }
        }
        log.addCommand(")");

        //se encarga de separar los parentesis incluidos en la declaracion de lisp
        programalimp = log.generadorString().replaceAll("\\("," ( ").replaceAll("\\)", " ) ");

        for (String linea : programalimp.trim().split("\\s+")) {
            stack.push(linea);
        }

        return stack;
    }

    /**
     * Metodo leer programa
     * @param programa
     *  Se encarga de realizar la lectura del archivo de lisp linea por linea
     */
    private Object leerPrograma(StackVector<Object> programa) {
        if (programa.empty()) {
            throw new IllegalArgumentException("Unexpected EOF while reading");
        }

        // se se retira el objeto del vector
        Object sec = programa.getVector().remove(0);

        if (sec.equals("(")) {
            StackVector<Object> inst = new StackVector<>(programa.size() - 1);
        
            while (!programa.getVector().get(0).equals(")")) {
                inst.push(leerPrograma(programa));
            }
            programa.getVector().remove(0);
            return inst;
        } else {
            return stringA_Tipo((String) sec);
        }
    }

    /**
     * Metodo ejecutar
     * Se permite la realizacion de la recursividad en el programa
     * @param pr se crea el objeto del vector
     */
    private void ejecutar(Object pr) {
        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            for (int i = 0; i < programa.size(); i++) {
                reconocer(programa.getVector().elementAt(i));
            }

        } else if (pr instanceof String) {
            reconocer(pr);
        }
    }

    /**
     * Metodo reconocer
     * Se encarga de reconocer la operaxion a realizar por el interprete mediante la lectura de datos
     * @param pr se crea el objeto para stackvector
     * @return se regresa el resultado de la operacion.
     */
    private Object reconocer(Object pr) {
        // Se realizan las distintas condiciones que permiten reconocer los elementos del vector para la lectura de datos
        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            if (programa.size() > 0) {
                Object sec = (programa.getVector().firstElement());
                if (sec instanceof String) {
                    ejecutar(programa);
                    if (sec.equals("+")) {
                        Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                        Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                        return num1 + num2;

                    } else if (sec.equals("-")) {
                        Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                        Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                        return num1 - num2;

                    } else if (sec.equals("*")) {
                        Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                        Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                        return num1 * num2;

                    } else if (sec.equals("/")) {
                        Integer num1 = (Integer) reconocer(programa.getVector().elementAt(1));
                        Integer num2 = (Integer) reconocer(programa.getVector().elementAt(2));
                        return num1 / num2;

                    } else if (((String) sec).toUpperCase().equals("DEFUN")) {
                        String nombre = (String) reconocer(programa.getVector().elementAt(1));
                        this.funciones.put(nombre, programa);

                    } else if (((String) sec).toUpperCase().equals("ATOM")) {
                        Object algo = reconocer(programa.getVector().elementAt(1));
                        return isAtom(algo);

                    } else if (((String) sec).toUpperCase().equals("LIST")) {
                        List miLista = new ArrayList();
                        StringBuilder miListaString = new StringBuilder();
                        for (int i = 1; i < programa.size(); i++) {
                            miLista.add(reconocer(programa.getVector().elementAt(i)));
                            miListaString.append(reconocer(programa.getVector().elementAt(i))).append(" ");
                        }
                        return miLista;

                    } else if (((String) sec).toUpperCase().equals("EQUAL")) {
                        List lst1 = (List) reconocer(programa.getVector().elementAt(1));
                        List lst2 = (List) reconocer(programa.getVector().elementAt(2));
                        return esIgual(lst1, lst2);

                    } else if (sec.equals("=") || ((String) sec).toUpperCase().equals("EQ")) {
                        Object e1 = reconocer(programa.getVector().elementAt(1));
                        Object e2 = reconocer(programa.getVector().elementAt(2));
                        return esIgual(e1, e2);

                    } else if (sec.equals("<")) {
                        Integer e1 = (Integer) reconocer(programa.getVector().elementAt(1));
                        Integer e2 = (Integer) reconocer(programa.getVector().elementAt(2));
                        return comparar("<",e1, e2);

                    } else if (sec.equals(">")) {
                        Integer e1 = (Integer) reconocer(programa.getVector().elementAt(1));
                        Integer e2 = (Integer) reconocer(programa.getVector().elementAt(2));
                        return comparar(">",e1, e2);

                    } else if (((String) sec).toUpperCase().equals("COND")) {
                        StackVector condiciones = new StackVector();
                        StackVector retornos = new StackVector();
                        for (int i = 1; i < programa.size(); i++) {
                            if (programa.getVector().elementAt(i) instanceof StackVector) {
                                StackVector cond = (StackVector) programa.getVector().elementAt(i);
                                Object eval = cond.getVector().elementAt(0);
                                Object res = cond.getVector().elementAt(1);
                                condiciones.push(eval);
                                retornos.push(res);
                            }
                        }

                        for (int i = 0; i < condiciones.size(); i++) {
                            if (reconocer(condiciones.getVector().elementAt(i)) instanceof Boolean) {
                                Boolean bool = (Boolean) reconocer(condiciones.getVector().elementAt(i));
                                if (bool) {
                                    return reconocer(retornos.getVector().elementAt(i));
                                }
                            }
                        }

                    } else if (((String) sec).toUpperCase().equals("FORMAT")) {
                        //TODO

                    } else if (((String) sec).toUpperCase().equals("DEFVAR")) {
                        //TODO

                    } else if (((String) sec).toUpperCase().equals("SETF")) {
                        //TODO

                    } else if (((String) sec).toUpperCase().equals("PRINT")) {
                        StringBuilder printString = new StringBuilder();
                        for (int i = 1; i < programa.size(); i++) {
                            printString.append(reconocer(programa.getVector().elementAt(i))).append(" ");
                        }
                        System.out.println(printString);

                    } else {
                        StackVector funcion = this.funciones.get(sec);
                        if (funcion != null) {
                            if (programa.size() >= 2) {
                                StackVector parametros = (StackVector) funcion.getVector().elementAt(2);
                                StackVector ingresados = new StackVector<Object>();
                                for (int i = 1; i < programa.size(); i++) {
                                    ingresados.push(reconocer(programa.getVector().elementAt(i)));
                                }

                                StackVector funcionNueva = new StackVector();
                                for (int i = 3; i < funcion.size(); i++) {
                                    funcionNueva.push(funcion.getVector().elementAt(i));
                                }

                                StackVector funcionOperable = cambiarParametros(parametros, ingresados, funcionNueva);
                                System.out.println();
                                System.out.print(sec+" (");
                                vectorToString(ingresados);
                                System.out.print(") = ");
                                //TODO: NO SE PORQUE IMPRIME LA DIRECCION
                                //System.out.println("Deberia: "+reconocer(ejecutarFuncion(funcionOperable)));
                                return ejecutarFuncion(funcionOperable);

                            }
                        }
                    }
                }
            }

        } else if (pr instanceof String) {
            return pr;
        } else if (pr instanceof  Integer) {
            return pr;
        } else {
            System.out.println("Oh oh");
        }
        return pr;
    }

    /**
     * Metodo StringA_Tipo
     * Se encarga de retornar el tipo de dato en caso de que el tipo que se haya introducido no sea el correcto
     * @param dato valor ingresado
     * @return el tipo de dato cambiado
     */
    private Object stringA_Tipo(String dato) {
        try {
            return Integer.parseInt(dato);
        } catch (NumberFormatException exc) {
            try {
                return Float.parseFloat(dato);
            } catch (NumberFormatException exc2) {
                return dato;
            }
        }
    }

    /**
     * Metodo ejecutrarFuncion
     * Se encarga de retornar el nuevo vector que permitira ejecutar las funciones de lisp 
     * @param fun funcion de lisp a ejecutar
     * @return la funcion ejecutada 
     */
    private Object ejecutarFuncion(Object fun) {
        StackVector funNuevo = new StackVector();
        if (fun instanceof StackVector) {
            StackVector programa = (StackVector) fun;
            for (int i = 0; i < programa.size(); i++) {
                if (programa.getVector().elementAt(i) instanceof StackVector) {
                    ejecutarFuncion(programa.getVector().elementAt(i));
                } else {
                    funNuevo.push(programa.getVector().elementAt(i));
                }
            }
        } else {
            System.out.print("Problema");
        }
        return reconocer(funNuevo);
    }

    /**
     * Metodo isAtom
     * Realiza la funcion atom mostrando true o nil (false) segun la sintaxis de lisp
     * @param algo valor ingresado
     * @return el estado, si no es un string o integer es false
     */
    private Boolean isAtom(Object algo) {
        if (algo instanceof String) {
            return true;
        } else if (algo instanceof Integer) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo esIgual
     * Para las funciones EQUAL y EQ, si los objetos son iguales es true, de lo contrario nil (false)
     * @param e1 objeto 1
     * @param e2 objeto 2
     * @return estado booleano
     */
    private Boolean esIgual(Object e1, Object e2) {
        if (e1.equals(e2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo comparar
     * Valida el tag
     * @param comp string
     * @param e1 int 1
     * @param e2 int 2
     * @return false
     */
    private Boolean comparar(String comp, Integer e1, Integer e2) {
        if (comp.equals("<")) {
            return e1 < e2;
        } else if (comp.equals(">")){
            return e1 > e2;
        }

        return false;
    }

    /**
     * Metodo cambiarParametros
     * Realiza toda la logica de Defun en donde se cambia el valor del parametro por lo ingresado segun el lisp
     * @param parametros reconocido en lisp (nombre de la variable)
     * @param ingresados lo que desea el usuario
     * @param funcionNueva en donde se almacena
     * @return el stack con los parametros nuevos
     */
    private StackVector cambiarParametros(StackVector parametros, StackVector ingresados, Object funcionNueva) {
        StackVector resultado = null;

        if (funcionNueva instanceof StackVector) {
            StackVector funcionOperable = (StackVector) funcionNueva;
            for (int i = 0; i < funcionOperable.size(); i++) {
                if (funcionOperable.getVector().elementAt(i) instanceof StackVector) {
                    cambiarParametros(parametros, ingresados, funcionOperable.getVector().elementAt(i));
                } else {

                    for (int j = 0; j < parametros.size(); j++) {
                        String parametro = (String) parametros.getVector().elementAt(j);
                        Object ingresado = ingresados.getVector().elementAt(j);

                        if (funcionOperable.getVector().elementAt(i) instanceof StackVector) {
                            cambiarParametros(parametros, ingresados, (StackVector) funcionOperable.getVector().elementAt(i));
                        } else {
                            if (funcionOperable.getVector().elementAt(i).equals(parametro)) {
                                funcionOperable.getVector().set(i, ingresado);
                            } else {
                                funcionOperable.getVector().set(i, funcionOperable.getVector().elementAt(i));
                            }
                        }
                    }
                }
            }

            resultado = funcionOperable;
        } else {
            System.out.print("Problema");
        }

        return resultado;
    }

    /**
     * Metodo VectorToString
     * Pasa el vector a una secuencia de caraceres, para que este posteriormentse pueda mostrar en pantalla
     * @param pr objeto
     */
    private void vectorToString(Object pr) {

        if (pr instanceof StackVector) {
            StackVector programa = (StackVector) pr;
            for (int i = 0; i < programa.size(); i++) {
                if (programa.getVector().elementAt(i) instanceof StackVector) {
                    vectorToString(programa.getVector().elementAt(i));
                } else {
                    System.out.print(programa.getVector().elementAt(i) + " ");
                }
            }
        } else {
            System.out.print("Problema");
        }

    }

}
