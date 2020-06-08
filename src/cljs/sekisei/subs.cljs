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

(re-frame/reg-sub
 ::width-height
 (fn [db]
   (select-keys db [:width :height])))

(re-frame/reg-sub
 ::current-cell
 (fn [db]
   (:current-cell db)))

(re-frame/reg-sub
 ::focused-number
 (fn [db]
   (:focused-number db)))

(re-frame/reg-sub
 ::mode
 (fn [db]
   (:mode db)))

(re-frame/reg-sub
 ::cell-number
 (fn [db [_ x y]]
   (get db [x y])))

(re-frame/reg-sub
 ::cell-letter
 (fn [db [_ n]]
   (get-in db [:numbers n])))

(re-frame/reg-sub
 ::cell-style
 (fn [_ [_ cell-number focused-number]]
   (when (= cell-number focused-number)
     {:color "red"})))
