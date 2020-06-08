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
 ::set-letter
 (fn [db [_ n letter]]
   (assoc-in db [:numbers n] letter)))

(re-frame/reg-event-db
 ::current-cell
 (fn [db [_ x y]]
   (assoc db :current-cell [x y])))

(re-frame/reg-event-db
 ::focused-number
 (fn [db [_ n]]
   (assoc db :focused-number n)))

(re-frame/reg-event-db
 ::toggle-mode
 (fn [db _]
   (update db :mode db/toggle-mode)))

(defn focus [id]
  (when-let [elm (.getElementById js/document id)]
    (.focus elm)
    (.select elm)))

(defn focus-cell [e [x y]]
  (.preventDefault e)
  (focus (db/cell-id x y)))

(re-frame/reg-event-fx
 ::cell-move
 (fn [{:keys [db]} [_ e]]
   (let [[x y] (:current-cell db)]
     (focus-cell e
                 (case (.-key e)
                   "ArrowLeft"  [(dec x) y]
                   "ArrowRight" [(inc x) y]
                   "ArrowUp"    [x (dec y)]
                   "ArrowDown"  [x (inc y)]
                   nil)))))
