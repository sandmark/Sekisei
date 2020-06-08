(ns sekisei.db)

(def default-db
  {:width  10
   :height 10})

(defn cell-id [x y]
  (str "cell" x "_" y))
