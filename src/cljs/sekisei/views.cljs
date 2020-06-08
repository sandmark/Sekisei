(ns sekisei.views
  (:require
   [re-frame.core :as re-frame]
   [sekisei.subs :as subs]
   [sekisei.events :as events]
   [sekisei.db :as db]
   [clojure.string :as str]))

(def <sub (comp deref re-frame/subscribe))
(def >evt re-frame/dispatch)

(defn width-input []
  (let [width (<sub [::subs/width])]
    [:input {:type      "text" :name "width" :placeholder "Width" :value width
             :on-change #(>evt [::events/update-width (-> % .-target .-value)])}]))

(defn height-input []
  (let [height (<sub [::subs/height])]
    [:input {:type      "text" :name "height" :placeholder "Height" :value height
             :on-change #(>evt [::events/update-height (-> % .-target .-value)])}]))

(defn mode-button [label]
  [:input {:type     "button"
           :value    label
           :on-click #(>evt [::events/toggle-mode])}])

(defn nav-panel []
  [:div.nav
   [:table
    [:tbody
     [:tr
      [:th "Width"]
      [:td [width-input]]
      [:th "Height"]
      [:td [height-input]]
      [:th (str "Mode: " (-> (<sub [::subs/mode]) name str/capitalize))]
      [:td [mode-button "Toggle"]]]]]])

(defn number-input [x y]
  (let [num (<sub [::subs/cell-number x y])]
    [:input {:type          "text"
             :id            (db/cell-id x y)
             :default-value num
             :on-key-up     #(>evt [::events/set-number x y (-> % .-target .-value)])
             :on-focus      #(>evt [::events/current-cell x y])
             :on-key-down   #(do  (.persist %)
                                  (>evt [::events/cell-move %]))}]))

(defn letter-input [x y]
  (let [cell-number    (<sub [::subs/cell-number x y])
        focused-number (<sub [::subs/focused-number])
        style          (<sub [::subs/cell-style cell-number focused-number])
        value          (<sub [::subs/cell-letter cell-number])
        disabled?      ((some-fn empty? nil? zero?) cell-number)]
    [:input {:type        "text"
             :disabled    disabled?
             :id          (db/cell-id x y)
             :style       style
             :value       value
             :placeholder cell-number
             :on-key-down #(do (.persist %)
                               (>evt [::events/cell-move %]))
             :on-focus    #(do (>evt [::events/focused-number cell-number])
                               (>evt [::events/current-cell x y]))
             :on-change   #(>evt [::events/set-letter cell-number (-> % .-target .-value)])}]))

(defn puzzle-panel []
  (let [{:keys [width height]} (<sub [::subs/width-height])
        mode                   (<sub [::subs/mode])]
    [:div.puzzle
     [:table
      [:tbody
       (for [y (range height)]
         ^{:key y}
         [:tr
          (for [x (range width)]
            ^{:key (str x y)}
            [:td (case mode
                   :number [number-input x y]
                   :letter [letter-input x y])])])]]]))

(defn main-panel []
  [:div.container
   [nav-panel]
   [puzzle-panel]])
