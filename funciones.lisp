;suma
(defun sum(a b)
    (+ a b)
) 
(print (sum 10 8))


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