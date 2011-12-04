(ns retreat1.test.core
  (:use [retreat1.core])
  (:use [midje.sweet]))

(defn neighbors [points]
  (apply disj
    (set
      (for [p points
            i (range (dec (:x p)) (+ 2 (:x p)))
            j (range (dec (:y p)) (+ 2 (:y p)))]
        {:x i :y j}))
    points))

(defn evolution-of-lives [points]
  (set (keep (fn [p]
               (if (#{2 3} (count (neighbors [p]))) p)) 
         points)))

(defn newborns [points]
  (let [unborn (apply disj (neighbors points) points)]
    (set
      (keep (fn [ub]
            
              (if (= 3 (count (neighbors [ub])))
                ub))
        unborn))))

(defn next-world [points]
  (set (concat (evolution-of-lives points) 
               (newborns points))))

(fact
  (next-world #{ {:x 1 :y 1} {:x 1 :y 2} }) => #{})

(fact
  (neighbors #{ {:x 1 :y 1} {:x 1 :y 2} }) => #{ {:x 0 :y 0} {:x 0 :y 1} {:x 0 :y 2} {:x 0 :y 3}
                                                 {:x 1 :y 0}                         {:x 1 :y 3}
                                                 {:x 2 :y 0} {:x 2 :y 1} {:x 2 :y 2} {:x 2 :y 3} })

;
;(tabular
;  (fact
;    (next-lifestyle ?lifestyle ?live-neighbors) => ?result) 
;  
;  ?lifestyle ?live-neighbors ?result 
;  :dead      0               :dead
;  :dead      1               :dead
;  :dead      2               :dead
;  :dead      3               :live
;  :dead      4               :dead
;  :dead      5               :dead
;  :dead      6               :dead
;  :dead      7               :dead
;  :dead      8               :dead
;  :live      0               :dead
;  :live      1               :dead
;  :live      2               :live
;  :live      3               :live
;  :live      4               :dead
;  :live      5               :dead
;  :live      6               :dead
;  :live      7               :dead
;  :live      8               :dead )        