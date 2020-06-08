(ns sekisei.events
  (:require
   [re-frame.core :as re-frame]
   [sekisei.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::update-width
 (fn [db [_ width]]
   (assoc db :width (some->> width seq (apply str) js/parseInt))))

(re-frame/reg-event-db
 ::update-height
 (fn [db [_ height]]
   (assoc db :height (some->> height seq (apply str) js/parseInt))))

(re-frame/reg-event-db
 ::set-number
 (fn [db [_ x y val]]
   (assoc db [x y] val)))

(re-frame/reg-event-db
 ::current-cell
 (fn [db [_ x y]]
   (assoc db :current-cell [x y])))

(defn focus [id]
  (some->> id (.getElementById js/document) (.focus)))

(defn focus-cell [e [x y]]
  (.preventDefault e)
  (focus (db/cell-id x y)))

(re-frame/reg-event-fx
 ::cell-move
 (fn [{:keys [db]} [_ e]]
   (let [[x y] (:current-cell db)]
     (focus-cell e
                 (case (.-key e)
                   ("Left" "ArrowLeft")   [(dec x) y]
                   ("Right" "ArrowRight") [(inc x) y]
                   ("Up" "ArrowUp")       [x (dec y)]
                   ("Down" "ArrowDown")   [x (inc y)]
                   nil)))))
