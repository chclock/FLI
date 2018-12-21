;  MIT License

;  Copyright guenchi (c) 2018 
         
;  Permission is hereby granted, free of charge, to any person obtaining a copy
;  of this software and associated documentation files (the "Software"), to deal
;  in the Software without restriction, including without limitation the rights
;  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
;  copies of the Software, and to permit persons to whom the Software is
;  furnished to do so, subject to the following conditions:
         
;  The above copyright notice and this permission notice shall be included in all
;  copies or substantial portions of the Software.
         
;  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
;  SOFTWARE.






(library (fli py call)
    (export
        py-call
    )
    (import
        (scheme)
        (match match)
        (fli py ffi))

    (define py-call
        (lambda (lst)
            (py-initialize)
            (let l ((x (compiler lst)))
                (if (not (null? x))  
                    (begin
                        (eval (car x))
                        (l (cdr x)))))
            (Py_Finalize)))

    (define compiler
        (lambda (lst)
            (define Num
                (lambda (x)
                    (match x
                        (,n (guard (number? n)) n))))
            (define Str
                (lambda (x)
                    (match x
                        (,s (guard (string? s)) s))))
            (define Sym
                (lambda (x)
                    (match x
                        (,s (guard (symbol? s)) s))))
            (match lst
                ((import ,(Sym -> lib)) `(define ,lib (py/import-import-module (symbol->string ,lib))))
                ((import ,(Sym -> lib) as ,(Sym -> l)) `(define ,l (py/import-import-module (symbol->string ,lib)))))))

    
    (define list->py-list
        (lambda (lst)
            (define len (length lst))
            (define *p (py/list-new len))
            (let loop ((n 0)(lst lst))
                (if (< n len)
                    (begin
                        (py/list-set-item! *p n (py/long-from-long (car lst)))
                        (loop (+ n 1) (cdr lst)))
                    *p))))
    
    (define vector->py-list
        (lambda (vct)
            (define len (vector-length vct))
            (define *p (py/list-new len))
            (let loop ((n 0))
                (if (< n len)
                    (begin
                        (py/list-set-item! *p n (py/long-from-long (vector-ref vct n)))
                        (loop (+ n 1)))
                    *p))))
    
    (define list->py-tuple
        (lambda (lst)
            (define len (length lst))
            (define *p (py/tuple-new len))
            (let loop ((n 0)(lst lst))
                (if (< n len)
                    (begin
                        (py/tuple-set-item! *p n (py/long-from-long (car lst)))
                        (loop (+ n 1) (cdr lst)))
                    *p))))
    
    (define vector->py-tuple
        (lambda (vct)
            (define len (vector-length vct))
            (define *p (py/tuple-new len))
            (let loop ((n 0))
                (if (< n len)
                    (begin
                        (py/tuple-set-item! *p n (py/long-from-long (vector-ref vct n)))
                        (loop (+ n 1)))
                    *p))))
    
    (define alist->py-dict
        (lambda (lst)
            (define *p (py/dict-new))
            (let loop ((i (car lst))(r (cdr lst)))
                (py/dict-set-item! *p (py/string-from-string (car i)) (py/long-from-long (cdr i)))
                (if (null? r)
                    *p
                    (loop (car r)(cdr r))))))    

)



    ; (define-syntax py-import
    ;     (syntax-rules (as)
    ;         ((_ e)
    ;             (py/run-simple-string (string-append "import " (symbol->string e))))
    ;         ((_ e as k)
    ;             (py/run-simple-string (string-append "import " (symbol->string e) " as " (symbol->string k))))))
    
    ; (define-syntax py-from
    ;     (syntax-rules (import)
    ;         ((_ e import k)
    ;             (py/run-simple-string (string-append "from " (symbol->string e) " import " (symbol->string k))))))
    
    
    ; (define any->string
    ;     (lambda (a)
    ;         (cond
    ;             ((number? a) (number->string a))
    ;             ((symbol? a) (symbol->string a))
    ;             ((string? a) a)
    ;             (else (display "input must be number, symbol or string")))))            
    
    ; (define py-define 
    ;     (lambda (x y)
    ;         (py/run-simple-string (string-append (symbol->string x) " = " (any->string y)))))
    
    
    ; (define py-decorater
    ;     (lambda (x)
    ;         (py/run-simple-string (string-append  "@" (symbol->string x)))))