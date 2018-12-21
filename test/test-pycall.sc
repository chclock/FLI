
(import (fli pycall))

(py-initialize)

;; test pass values

(begin
    (define x 9)
    (py-define 'x x)
    (define k (py/import-import-module "__main__"))
    (define dic (py/module-get-dict k))
    (define y (py/long-as-long (py/run-string "x * x" py-eval-input dic dic)))
    (display (+ x 8))
    (newline)
    (exit))

;; test list pass and operate

(begin
    (define x '(1 2 3 4 5))
    (define t (py/list->list x))
    (define a (py/list-get-item t 0))
    (define b (py/list-get-item t 1))
    (define c (py/list-get-item t 2))
    (define d (py/list-get-item t 3))
    (define e (py/list-get-item t 4))
    (display (py/long-as-long a))
    (newline)
    (display (py/long-as-long b))
    (newline)
    (display (py/long-as-long c))
    (newline)
    (display (py/long-as-long d))
    (newline)
    (display (py/long-as-long e))
    (newline)
    (exit))


(begin
    (define x `#(1 2 3 4 5))
    (define t (py/vector->list x))
    (define a (py/list-get-item t 0))
    (define b (py/list-get-item t 1))
    (define c (py/list-get-item t 2))
    (define d (py/list-get-item t 3))
    (define e (py/list-get-item t 4))
    (display (py/long-as-long a))
    (newline)
    (display (py/long-as-long b))
    (newline)
    (display (py/long-as-long c))
    (newline)
    (display (py/long-as-long d))
    (newline)
    (display (py/long-as-long e))
    (newline)
    (exit))
    
;; test tuple pass and operate

(begin
    (define x '(1 2 3 4 5))
    (define t (list->*tuple x))
    (define a (py/tuple-get-item t 0))
    (define b (py/tuple-get-item t 1))
    (define c (py/tuple-get-item t 2))
    (define d (py/tuple-get-item t 3))
    (define e (py/tuple-get-item t 4))
    (display (py/long-as-long a))
    (newline)
    (display (py/long-as-long b))
    (newline)
    (display (py/long-as-long c))
    (newline)
    (display (py/long-as-long d))
    (newline)
    (display (py/long-as-long e))
    (newline)
    (exit))

(begin
    (define x `#(1 2 3 4 5))
    (define t (py/vector->tuple x))
    (define a (py/tuple-get-item t 0))
    (define b (py/tuple-get-item t 1))
    (define c (py/tuple-get-item t 2))
    (define d (py/tuple-get-item t 3))
    (define e (py/tuple-get-item t 4))
    (display (py/long-as-long a))
    (newline)
    (display (py/long-as-long b))
    (newline)
    (display (py/long-as-long c))
    (newline)
    (display (py/long-as-long d))
    (newline)
    (display (py/long-as-long e))
    (newline)
    (exit))


(py-finalize)