(ns sekisei.css
  (:require [garden.def :as g]
            [garden.selectors :as gs]))

(g/defcssfn translate)
(g/defcssfn rgba)

(gs/defpseudoelement placeholder)

(gs/defclass form-table)

(g/defstyles screen
  #_[:div.container {:padding      "1em"
                     :position     :absolute
                     :top          "50%"
                     :left         "50%"
                     :margin-right "-50%"
                     :transform    (translate "-50%" "-50%")}]

  [:.nav {:color            "white"
          :background-color "gray"
          :height           "50px"}])
