(ns ^:figwheel-hooks my-reagent-project.sample
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r :refer [atom]]
   [reagent.dom :as rdom]))

(println "This text is printed from src/my_reagent_project/sample.cljs. Go ahead and edit it and see reloading in action.")

(defn multiply [a b] (* a b))

(defn timer-component []
  (let [seconds-elapsed (r/atom 0)]
    (fn []
      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      [:div "Seconds Elaspsed: " @seconds-elapsed])))

(defn mount []
  (rdom/render [timer-component] (gdom/getElement "app")))

(mount)

(defn ^:after-load on-reload []
  (mount))
