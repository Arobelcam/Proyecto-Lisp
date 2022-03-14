/**
 * Logica
 * Objetivo: Establecer logica para leer el archivo lisp
 * 
 * @author Aaron Beltran
 * @author Paulo Sánchez
 * @author Luis Montenegro
 * @author Roberto Rios
 * @version 1.0 finalizado 22/02/2022
 */
import java.util.ArrayList;
public class Logica {
private ArrayList<String> lines;  
/**
 * Metodos constructores
 */
public Logica(ArrayList<String> commands){
this.lines=commands;
}
public Logica(){
    this.lines= new ArrayList<String>();
}
/**
 * Se añade una instruccion a los arraylist
 * @param commands
 */
public void adCommand(String commands){
    this.lines.add(commands);
}
/**
 * Se obtienen las lineas al arraylist
 * @return lines
 */
public ArrayList<String> getLines(){
    return this.lines;
}
/**
 * String para almacenamiento de todas las lineas
 * @param logica
 */
public String string(){
    String logica="";
    for (String lines: lines){
        logica+= lines;
    }
    return logica;
}
}
