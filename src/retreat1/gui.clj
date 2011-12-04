(ns retreat1.gui
  (:use retreat1.core
        seesaw.core))

(let [open-action (action 
                    :handler (fn [e] (+ 1 2))
                    :name "Open ..."
                    :key  "menu O"
                    :tip  "Open a new something something.")]

  (defn -main [& args]
    (invoke-later
      (-> (frame :title "Game of Life"
                 :content open-action 
                 :on-close :exit )
        pack!
        show!))))