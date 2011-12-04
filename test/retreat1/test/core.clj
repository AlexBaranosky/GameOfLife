(ns retreat1.test.core
  (:use [retreat1.core])
  (:use [midje.sweet]))

(defn- neighbors [people]
  (apply disj
    (set
      (for [p people
            i (range (dec (:x p)) (+ 2 (:x p)))
            j (range (dec (:y p)) (+ 2 (:y p)))]
        {:x i :y j}))
    people))

(defn- evolution-of-lives [people]
  (set (for [p people
             :when (#{2 3} (count (neighbors #{p})))]
         p)))

(defn- newborns [people]
  (let [unborn (apply disj (neighbors 
                             
                             people) people)]
    (set
      (for [ub unborn
            :when (= 3 (count (neighbors #{ub})))]
        ub))))

(defn next-world [people]
  (set (concat (evolution-of-lives people) 
               (newborns people))))

(fact
  (evolution-of-lives #{ {:x 1 :y 1} {:x 1 :y 2}  {:x 1 :y 3} }) => #{{:x 1 :y 2}}

  (evolution-of-lives #{            {:x 0 :y 2}
                        {:x 1 :y 1} {:x 1 :y 2}  {:x 1 :y 3} }) => #{{:x 1 :y 2} })

(fact
  (next-world #{             {:x 0 :y 1} 
                 {:x 1 :y 0}             {:x 1 :y 2}
                }) => #{ {:x 1 :y 1} })

(fact
  (next-world #{ {:x 1 :y 1} {:x 1 :y 2}  {:x 1 :y 3} }) => #{{:x 1 :y 2}}
  (next-world #{            {:x 0 :y 2}
                {:x 1 :y 1} {:x 1 :y 2}  {:x 1 :y 3} }) => #{{:x 1 :y 2} })

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