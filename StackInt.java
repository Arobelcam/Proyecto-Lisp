/**
 * StackInt
 * Objetivo: Definicion de interfaz 
 * 
 * @author Aaron Beltran
 * @author Paulo Sánchez
  * @author Luis Montenegro
 * @author Roberto Rios
 * @version 1.0 finalizado 14/03/2022
 */
public interface StackInt<E> {
    /**
     * 
     * @param <E> generico
     */
    void push(E item);
    // pre:
    // post: se añade el item al stack
    // se aplicara pop si no se utiliza push

    E pop();
    // pre: el stack no esta vacio
    // post: el item más reciente es borrado y se regresa el valor

    E peek();
    // pre: el stack no esta vacio
    // post: El valor que esta más proximo a que se le aplique el pop se obtiene

    boolean empty();
    // post: se obtiene true si el stack está vacío

    int size();
    // post: se obtiene el numero de elementos disponibles en stack
}
