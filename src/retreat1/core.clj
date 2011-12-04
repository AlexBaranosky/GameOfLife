(ns retreat1.core
  (:use [retreat1.core])
  (:use [clojure.set])
  (:use [midje.sweet]))

(defn neighbors [& people]
  (let [neighbors+people (set
                           (for [{:keys [x y]} people
                                 i (range (dec x) (+ 2 x))
                                 j (range (dec y) (+ 2 y))]
                             {:x i :y j}))]
    (difference neighbors+people people)))

(defn survivors [people]
  (select (fn [p] (#{2 3} (count (neighbors p)))) people))

(defn newborns [people]
  (let [unborn (apply neighbors people)]
    (select (fn [ub] (= 3 (count (neighbors ub)))) unborn)))

(defn next-world [people]
  (union (survivors people)
    (newborns people)))
 