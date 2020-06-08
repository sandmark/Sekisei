(ns sekisei.css
  (:require [garden.def :as g]
            [garden.selectors :as gs]))

(g/defcssfn translate)
(g/defcssfn rgba)

(g/defstyles screen
  #_[:div.container {:padding      "1em"
                     :position     :absolute
                     :top          "50%"
                     :left         "50%"
                     :margin-right "-50%"
                     :transform    (translate "-50%" "-50%")}]
  [:.puzzle
   [:table :tbody :tr :td {:margin  0
                           :padding 0}]
   [(gs/input (gs/attr= :type :text)) {:width   "20px"
                                       :height  "20px"
                                       :padding 0
                                       :margin  0}]]

  [:.nav {:color            "white"
          :background-color "gray"
          :height           "50px"}])
