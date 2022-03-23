

;Implementacion de Operaciones Aritmeticas
(print (+ 9 4))
(print (- 5 4))
(print (* 3 7))
(print (/ 30 5))

;Definicion de funciones 
(defun Saludo (nombre apellido)
    ;(format t "Hola ~a! ~%" hombre)
    (print nombre apellido)
)

;Utilizacion de Predicados ATOM, LIST, EQUAL, <, >
(print (ATOM 1))
(print (ATOM (LIST 1 2 3 4 5)))
(print (EQUAL (LIST 1 2 3 4 5) (LIST 1 2 3 4 5)))
(print (EQUAL (LIST 1 2 5 4 5) (LIST 1 2 3 4 5)))
(print (= 1 1))
(print (= 1 2))
(print (< 1 3))
(print (< 5 3))

;Implementacion de condicionales
(print
    (COND ((< 6 5) "Condicion rechazada")
          ((< 6 7) "Condicion aprobada")
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

;Llamada de otra funcion existente
(print "Como te llamas? ")
(Saludo Nuevo Usuario )



