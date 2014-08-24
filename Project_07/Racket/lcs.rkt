#lang racket

(require htdp/testing)

;                                                                       
;                                                                       
;                                                                       
;     ;;;;;     ;;;;    ;;;     ;;;  ;;;;;;       ;;;     ;;;     ;;;   
;    ;    ;    ;    ;    ;;;    ;;    ;    ;     ;   ;   ;   ;   ;   ;  
;   ;;     ;  ;      ;   ; ;   ; ;    ;    ;     ;   ;   ;   ;   ;   ;  
;   ;         ;      ;   ; ;   ; ;    ;    ;     ;   ;       ;   ;   ;  
;   ;         ;      ;   ; ;;  ; ;    ;;;;;         ;;    ;;;       ;;  
;   ;         ;      ;   ;  ; ;  ;    ;             ;        ;;     ;   
;   ;;     ;  ;      ;   ;  ;;;  ;    ;            ;     ;    ;    ;    
;    ;;   ;    ;    ;    ;  ;;   ;    ;           ;  ;   ;   ;;   ;  ;  
;     ;;;;      ;;;;    ;;;  ;  ;;;  ;;;         ;;;;;    ;;;;   ;;;;;  
;                                                                       
;                                                                       
;                                                                       
;                                                                       
;                                                                       

;; ============================================================================== ;;

;; IMPROVED VERSION OF LCS-NAIVE:
;; ----> RETURN ACTUAL SUBSEQUENCE REPRESENTED AS A DATA STRUCTURE.
;; result is a data structure that;
;; -- holds length of subsequence and actual subsequence
(define-struct result (length longSeq))

;; lcs-memoized: listofX listofX xPositionOnCacheMaxtrix yPositionOnCacheMaxtrix---> result
;; Purpose: To find longest common subsequence with dynamic programming approach.

;(define (lcs-naive seqA seqB i j)
;(if
;;(= (vector-ref (vector-ref i) j) -inf.0)
;  (cond
;    [(or (empty? seqA) (empty? seqB)) ...]
;    [(= (first seqA) (first seqB)) 
;     ...]
;    [else ...
;      (lcs seqA (rest seqB))...
;      (lcs (rest seqA) seqA))...]
;    ))

;; Example & Test:

(check-expect (actual (lcs-memoize (list 1 2 3 4 5 6 7 8 9 10)(list 234 2 3 1 6 7 8 1 2 3))) 
              (list 5 (list 2 3 6 7 8)))
(check-expect (actual (lcs-memoize empty empty)) (list 0 empty))
(check-expect (actual (lcs-memoize empty (list 1 2 3))) (list 0 empty))
(check-expect (actual (lcs-memoize (string->list "kemal") (string->list "kemalsadas"))) 
              (list 5 (list #\k #\e #\m #\a #\l))) 
(check-expect (actual (lcs-memoize 
               (string->list "aatgcusgtcuattcg") (string->list "aatgcusastsggatgtcuattcg"))) 
'(16 (#\a #\a #\t #\g #\c #\u #\s #\g #\t #\c #\u #\a #\t #\t #\c #\g)))
(check-expect (actual (lcs-memoize (list 1 2) (list 2 1))) (list 1 (list 2)))

;; matrix-ref: vectorofvectorX i j --> X
;; Purpose: To return ith row jth column element from given matrix.

(define (matrix-ref matrix i j)
  (vector-ref 
   (vector-ref matrix i) 
   j))

;; matrix-set!: vectorofvectorX i j --> void
;; Purpose: To set a value to ith row jth column element of given matrix.

(define (matrix-set! matrix i j value)
  (vector-set! 
   (vector-ref matrix i) 
   j value))

;; maxOfResult: result result --> result
;; Purpose: To return maximum result compare to length property of result.

(define (maxOfResult r1 r2)
  (if (> (result-length r1) (result-length r2)) r1 r2))


;; Function:
(define (lcs-memoize seqA seqB)
  (local
    ((define cacheMatrix
       (build-vector (length seqA)
                     (λ (x)
                       (build-vector 
                        (length seqB) (λ (x) -inf.0)))))
     (define (lcs-find-answer seqA seqB i j)
       (cond
         [(equal? (first seqA) (first seqB))
          (make-result
           (+ 1
              (result-length 
               (lcs-memoize-inner (rest seqA)(rest seqB) (add1 i) (add1 j))))
           (cons
            (first seqA)
            (result-longSeq 
             (lcs-memoize-inner (rest seqA)(rest seqB) (add1 i) (add1 j)))))]
         [else
          (maxOfResult
           (lcs-memoize-inner seqA (rest seqB) i (add1 j))
           (lcs-memoize-inner (rest seqA) seqB (add1 i) j)
           )]))
     (define (lcs-memoize-inner seqA seqB i j)
       (cond
         [(or (empty? seqA) (empty? seqB)) (make-result 0 empty)]
         [(not (equal? (matrix-ref cacheMatrix i j) -inf.0))
          (matrix-ref cacheMatrix i j)]
         [else
          (begin
            (matrix-set! cacheMatrix i j 
                         (lcs-find-answer seqA seqB i j))
            (matrix-ref cacheMatrix i j))
          ]
         )))
    (lcs-memoize-inner seqA seqB 0 0))
  )

;; actual: result --> listofX
;; Purpose: A helper function being able to compare complex data structure result!
(define (actual result)
  (list (result-length result)
        (result-longSeq result)))

(generate-report)