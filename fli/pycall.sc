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






(library (fli pycall)
    (export
        py-initialize
        py-finalize
        pyrun-simplestring
        py-import
        py-from)
    (import
        (scheme))

(define lib (load-shared-object "../pycall.so"))

(define py-initialize
    (foreign-procedure "Py_Initialize" () int))

(define py-finalize
    (foreign-procedure "Py_Finalize" () int))

(define pyrun-simplestring
    (foreign-procedure "PyRun_SimpleString" (string) int))

(define-syntax py-import
    (syntax-rules (as)
        ((_ e)
            (pyrun-simplestring (string-append "import " (symbol->string e))))
        ((_ e as k)
            (pyrun-simplestring (string-append "import " (symbol->string e) " as " (symbol->string k))))))

(define-syntax py-from
    (syntax-rules (import)
        ((_ e import k)
            (pyrun-simplestring (string-append "from " (symbol->string e) " import " (symbol->string k))))))

)