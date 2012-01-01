(ns retreat1.core
  (:use [retreat1.core])
  (:use [clojure.set])
  (:use [midje.sweet]))

(letfn [(surrounding-spots [{:keys [x y]}]
          (for [i (range (dec x) (+ 2 x))
                j (range (dec y) (+ 2 y))
                :when (not= [i j] [x y])]
            {:x i :y j} ))]

  (defn unpopulated-neighboring-spots [people]
    (difference (set (mapcat surrounding-spots people)) people))
  
  (defn neighbors [person all-people]
    (intersection all-people (set (surrounding-spots person)))))

(defn survivors [people]
  (select (fn [p] (#{2 3} (count (neighbors p people)))) people))

(defn newborns [people]
  (->> people unpopulated-neighboring-spots (select #(= 3 (count (neighbors % people))))))

(defn next-world [people]
  (union (survivors people) (newborns people)))   