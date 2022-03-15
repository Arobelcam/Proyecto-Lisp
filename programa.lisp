;;;; Descripcion del  models.Programa
;;; Comentario
;; Comentario dentro del codigo
; Comentario despues de linea de codigo

;Operaciones Aritmeticas
(print (+ 5 4))
(print (- 5 4))
(print (* 5 4))
(print (/ 10 5))

;Definicion de funciones
(defun hello-you (name apellido)
    ;(format t "Hello ~a! ~%" name)
    (print name apellido)
)

;Predicados ATOM, LIST, EQUAL, <, >
(print (ATOM 1))
(print (ATOM (LIST 1 2 3 4 5)))
(print (EQUAL (LIST 1 2 3 4 5) (LIST 1 2 3 4 5)))
(print (EQUAL (LIST 1 2 5 4 5) (LIST 1 2 3 4 5)))
(print (= 1 1))
(print (= 1 2))
(print (< 1 3))
(print (< 5 3))

;Condiciones
(print
    (COND ((< 6 5) "suspenso")
          ((< 6 7) "aprobado")
    )
)

(print
    (*
        (COND
            ((= 7 1) 1)
            ((ATOM 1) 6)
        )
        7
    )
)

;Llamada de funcion y otras
(print "Como te llamas? ")
(hello-you Willi RO)

;(format t "Hola Mundo")
;(defvar name "Willi")
;(defvar num 0)
;(setf num 6)
;(print num)

;suma
(defun sum(a b)
    (+ a b)
) 
;(print (sum 10 8))


; Conversion de fahrenheit a celsius
(defun FTOC(tempe)
    (/ (- tempe 32) 1.8))
    
;(print(FTOC 153))   


;Fibonacci
(defun Fibonacci (N)
(if (<= N 0)
      0
      (if (= N 1)
        1
           (+ (Fibonacci(- N 1)) (Fibonacci(- N 2)))))) 
           
;(print(Fibonacci 3))

;suma de los elementos de una lista
(defun sumlist(l)
    (print(reduce '+ l)))

;(sumlist '(1 2 3 4 5 6))
    
    
;  factorial 
(defun factorial(num)
    (if(= num 0) 1
    (* num(factorial(- num 1)))))
    
(print(factorial 4))

; variables y listas

(defun show(list)
    (loop for n in list
    do(print n)))
    
(defvar dias '("Lunes" "Martes" "Miercoles" "Jueves" "Viernes" "Sabado" "Domingo"))
;(show dias)

    
 ;multiplicacion de los elementos de una lista  
(defun mult(list num)
    (mapcar
        (lambda (x) (* x num)
        list )))
;(print (mult '(1 2 3 4 5 6) 2))