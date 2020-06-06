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
