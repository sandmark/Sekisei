(ns sekisei.db)

(def default-db
  {:width  10
   :height 10
   :mode   :number})

(defn toggle-mode [mode]
  (mode {:number :letter
         :letter :number}))

(defn cell-id [x y]
  (str "cell" x "_" y))
