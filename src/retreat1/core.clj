(ns retreat1.core
  (:use [retreat1.core])
  (:use [clojure.set])
  (:use [midje.sweet]))

(defn neighbors [& people]
  (difference
    (set
      (for [{:keys [x y]} people
            i (range (dec x) (+ 2 x))
            j (range (dec y) (+ 2 y))]
        {:x i :y j}))
    people))

(defn survivors [people]
  (set (for [p people
             :when (#{2 3} (count (neighbors p)))]
         p)))

(defn newborns [people]
  (let [unborn (apply neighbors people)]
    (set
      (for [ub unborn
            :when (= 3 (count (neighbors ub)))]
        ub))))

(defn next-world [people]
  (union (survivors people)
    (newborns people)))
 