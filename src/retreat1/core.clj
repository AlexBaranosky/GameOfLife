(ns retreat1.core
  (:use [retreat1.core])
  (:use [clojure.set])
  (:use [midje.sweet]))

(defn unpopulated-neighboring-spots [& people]
  (let [neighbors+people (set
                           (for [{:keys [x y]} people
                                 i (range (dec x) (+ 2 x))
                                 j (range (dec y) (+ 2 y))]
                             {:x i :y j}))]
    (difference neighbors+people people)))

(defn neighbors [{:keys [x y]} all-people]
  (intersection all-people (set 
                             (for [i (range (dec x) (+ 2 x))
                                   j (range (dec y) (+ 2 y))
                                   :when (not= [i j] [x y])]
                               {:x i :y j} ))))

(defn survivors [people]
  (select (fn [p] (#{2 3} (count (neighbors p people)))) people))

(defn newborns [people]
  (let [unborn (apply unpopulated-neighboring-spots people)]
    (select (fn [ub] (= 3 (count (neighbors ub people)))) unborn)))

(defn next-world [people]
  (union (survivors people) (newborns people)))
 