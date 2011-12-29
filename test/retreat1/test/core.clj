(ns retreat1.test.core
  (:use [retreat1.core])
  (:use [clojure.set])
  (:use [midje.sweet]))

;(doseq [?items (map #(repeat % :x) [2 3])]
;  (fact "stupid test of internal function"
;    (survivors #{  {:x 1 :y 2} }) => #{{:x 1 :y 2}}
;    (provided
;      (neighbors anything) => ?items)))
;
;(doseq [?items (map #(repeat % :x) [0 1 4 5 6 7 8])]
;  (fact "stupid test of internal function"
;    (survivors #{  {:x 1 :y 2} }) => #{}
;    (provided
;      (neighbors anything) =>  ?items)))
;
;(fact "newborn occurs when surrounded by 3 people"
;  (newborns #{             {:x 0 :y 1} 
;               {:x 1 :y 0}             {:x 1 :y 2}
;              }) => #{ {:x 1 :y 1} }
;  (provided
;    (unpopulated-neighboring-spots {:x 1 :y 1}) => #{              {:x 0 :y 1} 
;                                   {:x 1 :y 0}             {:x 1 :y 2}  }))

(fact "newborn occurs when surrounded by 3 people"
  (next-world #{             {:x 0 :y 1} 
                 {:x 1 :y 0}             {:x 1 :y 2}
                }) => #{ {:x 0 :y 1}
                         {:x 1 :y 1} })

(fact "asd"
  (neighbors {:x 1 :y 2} #{{:x 1 :y 1} {:x 1 :y 2} {:x 1 :y 3}} ) => 
                         #{{:x 1 :y 1}             {:x 1 :y 3}} )

(fact
  (next-world #{            {:x 0 :y 2}
                {:x 1 :y 1} {:x 1 :y 2}  {:x 1 :y 3} }) => #{ {:x 0 :y 1} {:x 0 :y 2} {:x 0 :y 3}
                                                              {:x 1 :y 1} {:x 1 :y 2} {:x 1 :y 3} 
                                                                          {:x 2 :y 2}             })

(fact
  (next-world #{ {:x 1 :y 1} {:x 1 :y 2} }) => #{})

(fact
  (unpopulated-neighboring-spots {:x 1 :y 1} {:x 1 :y 2}) => #{{:x 0 :y 0} {:x 0 :y 1} {:x 0 :y 2} {:x 0 :y 3}
                                                               {:x 1 :y 0}                         {:x 1 :y 3}
                                                               {:x 2 :y 0} {:x 2 :y 1} {:x 2 :y 2} {:x 2 :y 3} })
      