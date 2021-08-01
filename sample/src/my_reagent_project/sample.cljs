(ns ^:figwheel-hooks my-reagent-project.sample
  (:require
   [goog.dom :as gdom]
   [reagent.core :as r :refer [atom]]
   [reagent.dom :as rdom]))

(println "This text is printed from src/my_reagent_project/sample.cljs. Go ahead and edit it and see reloading in action.")

(defn multiply [a b] (* a b))

(defn simple-component []
  [:div
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} "and red "] "text."]])

(defn mount []
  (rdom/render [simple-component] (gdom/getElement "app")))

(mount)

(defn ^:after-load on-reload []
  (mount))
