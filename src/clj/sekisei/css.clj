(ns sekisei.css
  (:require [garden.def :as g]
            [garden.selectors :as gs]))

(g/defcssfn translate)
(g/defcssfn rgba)

(gs/defpseudoelement placeholder)

(g/defstyles screen
  [:.puzzle {:padding      "1em"
             :position     :absolute
             :top          "50%"
             :left         "50%"
             :margin-right "-50%"
             :transform    (translate "-50%" "-50%")}
   [:table :tbody :tr :td {:margin  0
                           :padding 0}]
   [(gs/input (gs/attr= :type :text)) {:font-family "monospace"
                                       :font-size   "20px"
                                       :font-weight "bold"
                                       :text-align "right"
                                       :width       "20px"
                                       :height      "20px"
                                       :padding     0
                                       :margin      0}]
   [placeholder {:color "silver"}]
   [(gs/input (gs/attr :disabled)) {:background "gray"
                                    :border     [["2px" "gray" "solid"]]}]]

  [:.nav {:color            "white"
          :background-color "gray"
          :height           "50px"}])
