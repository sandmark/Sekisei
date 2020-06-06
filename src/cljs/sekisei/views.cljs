(ns sekisei.views
  (:require
   [re-frame.core :as re-frame]
   [sekisei.subs :as subs]
   [sekisei.events :as events]))

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

(defn main-panel []
  [nav-panel])
