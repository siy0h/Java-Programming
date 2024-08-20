#lang scheme
(define (different L)
  (if (list? L) 
      (do-different L)
      'list-error))

(define (do-different L)
  (cond
    ((null? L) #t)
    ((member (car L) (cdr L)) #f)
    (else (do-different (cdr L)))))


(define (extract4Columns L)
  (list (list (list-ref (list-ref L 0) 0) (list-ref (list-ref L 1) 0) (list-ref (list-ref L 2) 0) (list-ref (list-ref L 3) 0))
        (list (list-ref (list-ref L 0) 1) (list-ref (list-ref L 1) 1) (list-ref (list-ref L 2) 1) (list-ref (list-ref L 3) 1))
        (list (list-ref (list-ref L 0) 2) (list-ref (list-ref L 1) 2) (list-ref (list-ref L 2) 2) (list-ref (list-ref L 3) 2))
        (list (list-ref (list-ref L 0) 3) (list-ref (list-ref L 1) 3) (list-ref (list-ref L 2) 3) (list-ref (list-ref L 3) 3))))

(define (extract4Quadrants L)
  (list (list (list-ref (list-ref L 0) 0) (list-ref (list-ref L 0) 1) (list-ref (list-ref L 1) 0) (list-ref (list-ref L 1) 1))
        (list (list-ref (list-ref L 0) 2) (list-ref (list-ref L 0) 3) (list-ref (list-ref L 1) 2) (list-ref (list-ref L 1) 3))
        (list (list-ref (list-ref L 2) 0) (list-ref (list-ref L 2) 1) (list-ref (list-ref L 3) 0) (list-ref (list-ref L 3) 1))
        (list (list-ref (list-ref L 2) 2) (list-ref (list-ref L 2) 3) (list-ref (list-ref L 3) 2) (list-ref (list-ref L 3) 3))))
  
        

(define (merge3 L V M)
  (let ((x (append L V M))) x))


(define (checkSudoku L)
  (let ((x (merge3 '() (extract4Columns L) (extract4Quadrants L)))) (not (member #f (map different x)))))

  
  
  
