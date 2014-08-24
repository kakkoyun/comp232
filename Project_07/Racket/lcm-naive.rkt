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



;; lcs-naive: listofX listofX ---> number
;; Purpose: To find longest common subsequence naively.

;(define (lcs-naive seqA seqB)
;  (cond
;    [(or (empty? seqA) (empty? seqB)) ...]
;    [(= (first seqA) (first seqB)) 
;     ...]
;    [else ...
;      (lcs seqA (rest seqB))...
;      (lcs (rest seqA) seqA))...]
;    ))

;; Example & Test:

(check-expect (lcs-naive (list 1 2 3 4 5 6 7 8 9 10)(list 234 2 3 1 6 7 8 1 2 3)) 5)
(check-expect (lcs-naive empty empty) 0)
(check-expect (lcs-naive empty (list 1 2 3)) 0)
(check-expect (lcs-naive (string->list "kemal") (string->list "kemalsadas")) 5)
(check-expect (lcs-naive 
               (string->list "aatgcusgtcuattcg") (string->list "aatgcusastsggatgtcuattcg")) 16)
(check-expect (lcs-naive (list 1 2) (list 2 1)) 1)

;; Function:
(define (lcs-naive seqA seqB)
  (cond
    [(or (empty? seqA) (empty? seqB)) 0]
    [(equal? (first seqA) (first seqB)) 
     (+ 1 
        (lcs-naive (rest seqA)(rest seqB)))]
    [else
     (max
      (lcs-naive seqA (rest seqB))
      (lcs-naive (rest seqA) seqB
                 ))]
    ))

;; Performance:
(time (lcs-naive (build-list 10 (λ (x) (+ x (random 5))))
                 (build-list 10 (λ (x) (+ x (random 5))))))
(time (lcs-naive (build-list 12 (λ (x) (+ x (random 5))))
                 (build-list 12 (λ (x) (+ x (random 5))))))
;;(time (lcs-naive (build-list 15 (λ (x) (+ x (random 5))))
;;               (build-list 15 (λ (x) (+ x (random 5))))))

;; ============================================================================== ;;

;; lcs-memoized: listofX listofX xPositionOnCacheMaxtrix yPositionOnCacheMaxtrix---> number
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

(check-expect (lcs-memoize (list 1 2 3 4 5 6 7 8 9 10)(list 234 2 3 1 6 7 8 1 2 3)) 5)
(check-expect (lcs-memoize empty empty) 0)
(check-expect (lcs-memoize empty (list 1 2 3)) 0)
(check-expect (lcs-memoize (string->list "kemal") (string->list "kemalsadas")) 5)
(check-expect (lcs-memoize 
               (string->list "aatgcusgtcuattcg") (string->list "aatgcusastsggatgtcuattcg")) 16)
(check-expect (lcs-memoize (list 1 2) (list 2 1)) 1)

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
          (+ 1 
             (lcs-memoize-inner (rest seqA)(rest seqB) (add1 i) (add1 j)))]
         [else
          (max
           (lcs-memoize-inner seqA (rest seqB) i (add1 j))
           (lcs-memoize-inner (rest seqA) seqB (add1 i) j)
           )]))
     (define (lcs-memoize-inner seqA seqB i j)
       (cond
         [(or (empty? seqA) (empty? seqB)) 0]
         [(> (matrix-ref cacheMatrix i j) -inf.0)
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


;; Performance:
(time (lcs-memoize (build-list 10 (λ (x) (+ x (random 5))))
                   (build-list 10 (λ (x) (+ x (random 5))))))
(time (lcs-memoize (build-list 12 (λ (x) (+ x (random 5))))
                   (build-list 12 (λ (x) (+ x (random 5))))))
(time (lcs-memoize (build-list 15 (λ (x) (+ x (random 5))))
                   (build-list 15 (λ (x) (+ x (random 5))))))
(time (lcs-memoize (build-list 150 (λ (x) (+ x (random 5))))
                   (build-list 150 (λ (x) (+ x (random 5))))))
(time (lcs-memoize (build-list 500 (λ (x) (+ x (random 5))))
                   (build-list 500 (λ (x) (+ x (random 5))))))
(time (lcs-memoize (build-list 5000 (λ (x) (+ x (random 5))))
                   (build-list 5000 (λ (x) (+ x (random 5))))))
(generate-report)