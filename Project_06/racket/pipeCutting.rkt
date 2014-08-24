#lang racket

(require htdp/testing)

;                                                                  
;                                                                  
;    ;;;;    ;;;;  ;     ;  ;;;;;            ;;;;    ;;;;    ;;;;  
;   ;    ;  ;    ; ;;   ;;  ;    ;          ;    ;  ;    ;  ;    ; 
;   ;    ;  ;    ; ; ; ; ;  ;    ;          ;    ;  ;    ;  ;    ; 
;   ;       ;    ; ;  ;  ;  ;    ;               ;       ;       ; 
;   ;       ;    ; ;     ;  ;    ;              ;     ;;;       ;  
;   ;       ;    ; ;     ;  ;;;;;              ;         ;     ;   
;   ;       ;    ; ;     ;  ;                 ;          ;    ;    
;   ;    ;  ;    ; ;     ;  ;                ;      ;    ;   ;     
;   ;    ;  ;    ; ;     ;  ;               ;       ;    ;  ;      
;    ;;;;    ;;;;  ;     ;  ;               ;;;;;;   ;;;;   ;;;;;; 
;                                                                  
;                                                                  

;; Author : Kemal Akkoyun
;; Comp232 - PRoject - 06
;; Pipe Cutting Problem with Dynamic Programming aproach.
;; ======================================================================================== ;;


;; all-possible-cuts: number --> listOfnumbers
;; Purpose: To create a list of numbers that,
;; --- represents all possible cuts from given pipe length.

;; Example:
; (all-possible-cuts 0) -> 0
; (all-possible-cuts 1) -> 1
; (all-possible-cuts 2) -> 1 2
; (all-possible-cuts 3) -> 1 2 3
; (all-possible-cuts 4) -> 1 2 3 4
; (all-possible-cuts 5) -> 1 2 3 4 5

(define (all-possible-cuts n)
  (build-list n 
              (λ (x) (add1 x))))
;; Tests:
(check-expect (all-possible-cuts 0) empty)
(check-expect (all-possible-cuts 1) '(1))
(check-expect (all-possible-cuts 2) '(1 2))
(check-expect (all-possible-cuts 3) '(1 2 3))
(check-expect (all-possible-cuts 4) '(1 2 3 4))
(check-expect (all-possible-cuts 5) '(1 2 3 4 5))

;; cut-pipe: vectorOfNumbers number --> number
;; Purpose: Solve pipe cutting problem and return value of optimum cut for given pipe length.

;; Example:
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 1) -> 1
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 2) -> 5
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 3) -> 8
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 4) -> 10
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 5) -> 13
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 6) -> 17
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 7) -> 18
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 8) -> 22
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 9) -> 25
; (cut-pipe ( 1 5 8 9 10 17 17 20 24 30 ) 10) -> 30

(define (cut-pipe price length)
  (local
    ((define (cut-pipe-inner price length cutList)
       (cond
         [(empty? cutList) 0]
         [(< (vector-length price) (first cutList)) (cut-pipe-inner price length (rest cutList))]
         [else 
          (max 
           (+ (vector-ref price (- (first cutList) 1))
              (cut-pipe price (- length (first cutList))))
           (cut-pipe-inner price length (rest cutList))
           )]
         )))
    (cond
      [(= length 0) 0]
      [(= length 1) (vector-ref price 0)]
      [else 
       (cut-pipe-inner price length (all-possible-cuts length))]
      )))



;; Sample Price Table for Testing.
(define samplePriceVector
  (vector 1 5 8 9 10 17 17 20 24 30))

;; Tests:
(check-expect (cut-pipe samplePriceVector 1) 1)
(check-expect (cut-pipe samplePriceVector 2) 5)
(check-expect (cut-pipe samplePriceVector 3) 8)
(check-expect (cut-pipe samplePriceVector 4) 10)
(check-expect (cut-pipe samplePriceVector 5) 13)
(check-expect (cut-pipe samplePriceVector 6) 17)
(check-expect (cut-pipe samplePriceVector 7) 18)
(check-expect (cut-pipe samplePriceVector 8) 22)
(check-expect (cut-pipe samplePriceVector 9) 25)
(check-expect (cut-pipe samplePriceVector 10) 30)

;; =================================================================================================== ;;

;; pipe-cutting-memoize: vectorOfnumbers number --> listOfnumber
;; Purpose: Solve pipe cutting problem with dynamic programming aproach.

;; Example:
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 1) -> 1
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 2) -> 5
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 3) -> 8
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 4) -> 10
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 5) -> 13
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 6) -> 17
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 7) -> 18
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 8) -> 22
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 9) -> 25
; (cut-pipe-memoize ( 1 5 8 9 10 17 17 20 24 30 ) 10) -> 30

(define (pipe-cutting-memoize price length)
  (pipe-cutting-memoize-helper price length 
                               (build-vector length 
                                             (λ (x) -inf.0))))

;; Tests:
(check-expect (pipe-cutting-memoize samplePriceVector 1) 1)
(check-expect (pipe-cutting-memoize samplePriceVector 2) 5)
(check-expect (pipe-cutting-memoize samplePriceVector 3) 8)
(check-expect (pipe-cutting-memoize samplePriceVector 4) 10)
(check-expect (pipe-cutting-memoize samplePriceVector 5) 13)
(check-expect (pipe-cutting-memoize samplePriceVector 6) 17)
(check-expect (pipe-cutting-memoize samplePriceVector 7) 18)
(check-expect (pipe-cutting-memoize samplePriceVector 8) 22)
(check-expect (pipe-cutting-memoize samplePriceVector 9) 25)
(check-expect (pipe-cutting-memoize samplePriceVector 10) 30)


;; pipe-cutting-memoize-helper : (vectorof number) number (vectorofnumber) --> number
;; Purpose: Help pipe-cutting-memoize to solve problem by cacheing solutions of subproblems. 

;; Tests & Examples:
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 1 (vector -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0)) 1)
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 2 (vector -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0)) 5)
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 3 (vector -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0)) 8)
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 4 (vector 1 5 8 10 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0)) 10)
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 5 (vector 1 5 8 10 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0)) 13)
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 6 (vector 1 5 8 10 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0)) 17)
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 7 (vector 1 5 8 10 13 -inf.0 -inf.0 -inf.0 -inf.0 -inf.0)) 18)
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 8 (vector 1 5 8 10 13 17 -inf.0 -inf.0 -inf.0 -inf.0)) 22)
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 9 (vector 1 5 8 10 13 17 18 -inf.0 -inf.0 -inf.0)) 25)
(check-expect 
 (pipe-cutting-memoize-helper samplePriceVector 10 (vector 1 5 8 10 13 17 18 22 -inf.0 -inf.0)) 30)


(define (pipe-cutting-memoize-helper price length memoize) 
  (local 
    ;; solve-problem : vectorOfNumbers number --> number
    ;; Purpose: A function solve pipe cutting problem by all possible cuts. 
    ((define (solve-problem price length)
       (cond
         [(= length 1) (vector-ref price 0)]
         [else
          (solve-all-possible-cuts price length (all-possible-cuts length) memoize)])
       ))
    (cond
      [(= length 0) 0]
      [(= (vector-ref memoize (- length 1)) -inf.0)
       (begin 
         (vector-set! memoize
                      (- length 1)
                      (solve-problem price length))
         (vector-ref memoize (- length 1)))]
      [else
       (vector-ref memoize (- length 1))]
      )))

;; solve-all-possible-cuts : vectorOfNumbers number listOfNumbers --> number
;; Purpose: Divide pipe with all possible cuts and solve for each piece. 

;; A test instance:
(define cache
  (vector 1 5 8 10 13 17 18 22 -inf.0 -inf.0))

;;Test & Examples:
(check-expect 
 (solve-all-possible-cuts samplePriceVector 1 (list 1) cache) 1)
(check-expect 
 (solve-all-possible-cuts samplePriceVector 2 (list 1 2) cache) 5)
(check-expect 
 (solve-all-possible-cuts samplePriceVector 3 (list 1 2 3) cache) 8)
(check-expect 
 (solve-all-possible-cuts samplePriceVector 4 (list 1 2 3 4) cache) 10)
(check-expect 
 (solve-all-possible-cuts samplePriceVector 5 (list 1 2 3 4 5) cache) 13)
(check-expect 
 (solve-all-possible-cuts samplePriceVector 6 (list 1 2 3 4 5 6) cache) 17)
(check-expect 
 (solve-all-possible-cuts samplePriceVector 7 (list 1 2 3 4 5 6 7) cache) 18)
(check-expect
 (solve-all-possible-cuts samplePriceVector 8 (list 1 2 3 4 5 6 7 8) cache) 22)
(check-expect 
 (solve-all-possible-cuts samplePriceVector 9 (list 1 2 3 4 5 6 7 8 9) cache) 25)
(check-expect 
 (solve-all-possible-cuts samplePriceVector 10 (list 1 2 3 4 5 6 7 8 9 10) cache) 30)


(define (solve-all-possible-cuts price n cutList memoize)
  (cond
    ;; There is no possible cuts left.
    [(empty? cutList) 0]
    ;; This cut larger then what market ask from us.
    [(> (first cutList) (vector-length price)) 
     (solve-all-possible-cuts price n (rest cutList) memoize)]
    [else
     (max
      (+ (vector-ref price (- (first cutList) 1))
         (pipe-cutting-memoize-helper price (- n (first cutList)) memoize))
      (solve-all-possible-cuts price n (rest cutList) memoize))]
    ))

;; ==================================================================== ;;
;; =========== Time tracking for Performance Testing ================== ;;
;; ==================================================================== ;;

(time (cut-pipe samplePriceVector 10))
(time (pipe-cutting-memoize samplePriceVector 10))

(time (cut-pipe samplePriceVector 15))
(time (pipe-cutting-memoize samplePriceVector 15))

(time (cut-pipe samplePriceVector 20))
(time (pipe-cutting-memoize samplePriceVector 20))

;; (time (cut-pipe samplePriceVector 25))
(time (pipe-cutting-memoize samplePriceVector 25))

;; (time (cut-pipe samplePriceVector 50))
(time (pipe-cutting-memoize samplePriceVector 50))

;; (time (cut-pipe samplePriceVector 125))

(time (pipe-cutting-memoize samplePriceVector 125))
(time (pipe-cutting-memoize samplePriceVector 625))
(time (pipe-cutting-memoize samplePriceVector 3125))
;; (time (pipe-cutting-memoize samplePriceVector 15625))

;; I exclude these case because it takes a lot of time, more than my patience!

(generate-report)