(ns retreat1.test.core
  (:use [retreat1.core])
  (:use [clojure.set])
  (:use [midje.sweet]))

(doseq [?items (map #(repeat % :x) [2 3])]
  (fact "stupid test of internal function"
    (survivors #{  {:x 1 :y 2} }) => #{{:x 1 :y 2}}
    (provided
      (neighbors anything) =>  ?items)))

(doseq [?items (map #(repeat % :x) [0 1 4 5 6 7 8])]
  (fact "stupid test of internal function"
    (survivors #{  {:x 1 :y 2} }) => #{}
    (provided
      (neighbors anything) =>  ?items)))

(fact "newborn occurs when surrounded by 3 people"
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
  (neighbors {:x 1 :y 1} {:x 1 :y 2}) => #{{:x 0 :y 0} {:x 0 :y 1} {:x 0 :y 2} {:x 0 :y 3}
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