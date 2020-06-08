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

(defn nav-panel []
  [:div.nav
   [:table
    [:tbody
     [:tr
      [:th "Width"]
      [:td [width-input]]
      [:th "Height"]
      [:td [height-input]]]]]])

(defn number-input [x y]
  [:input {:type        "text"
           :id          (db/cell-id x y)
           :on-change   #(>evt [::events/set-number x y (-> % .-target .-value)])
           :on-focus    #(>evt [::events/current-cell x y])
           :on-key-down #(do (.persist %)
                             (>evt [::events/cell-move %]))}])

(defn puzzle-panel []
  (let [{:keys [width height]} (<sub [::subs/width-height])]
    [:div.puzzle
     [:table
      [:tbody
       (for [y (range height)]
         ^{:key y}
         [:tr
          (for [x (range width)]
            ^{:key (str x y)}
            [:td [number-input x y]])])]]]))

(defn main-panel []
  [:div.container
   [nav-panel]
   [puzzle-panel]])
