(ns ^:figwheel-hooks my-reagent-project.sample
  (:require
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))

(println "This text is printed from src/my_reagent_project/sample.cljs. Go ahead and edit it and see reloading in action.")

(defn multiply [a b] (* a b))

(defn lister [items]
  [:ul
   (for [item items]
     ^{:key item} [:li "Item " item])])

(defn lister-user []
  [:div
   "Here is a list:"
   [lister (range 3)]])

(defn mount []
  (rdom/render [lister-user] (.-body js/document)))

(mount)

(defn ^:after-load on-reload []
  (mount))
