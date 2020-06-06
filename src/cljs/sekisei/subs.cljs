(ns sekisei.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::width
 (fn [db]
   (:width db)))

(re-frame/reg-sub
 ::height
 (fn [db]
   (:height db)))
