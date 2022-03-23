package com.example.models;

/**
 * StackVector
 * Objetivo: Implementaccion final de Stack
 * 
 * @author Aaron Beltran
 * @author Paulo Sánchez
 * @author Luis Montenegro
 * @author Roberto Rios
 * @version 1.0 finalizado 22/02/2022
 */
import java.util.Vector;
public class StackVector<E> extends StackAbstract<E>{
    private Vector<E> vec;
    //Metodos vector
    public StackVector(){
        vec= new Vector<>();
    }
    public StackVector(int i){
        this.vec= new Vector<>(i);
    }
    public Vector <E> getVector() {
        return this.vec;
    }
        @Override
        public void push(E item) {
            // pre:
        // post: se añade el item al stack
        // se aplicara pop si no se utiliza push
        vec.add(item);   
        }

        @Override
        public E pop() {
            // pre: el stack no esta vacio
        // post: el item más reciente es borrado y se regresa el valor 
            return vec.remove(size()-1);
        }

        @Override
        public E peek() {
        // pre: el stack no esta vacio
        // post: El valor que esta más proximo a que se le aplique el pop se obtiene
            return vec.get(size()-1);
        }

        @Override
        public boolean empty() {
        // post: se obtiene true si el stack está vacío
            return vec.isEmpty();
        }

        @Override
        public int size() {
            // post: se obtiene el numero de elementos disponibles en stack
            return vec.size();
        }
        
}
 