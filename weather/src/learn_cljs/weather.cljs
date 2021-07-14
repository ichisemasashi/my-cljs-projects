(ns ^:figwheel-hooks learn-cljs.weather
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as rdom]))

(println "This text is printed from src/learn_cljs/weather.cljs. Go ahead and edit it and see reloading in action.")

(defn multiply [a b] (* a b))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (reagent/atom {:title "WhichWeather"
                                  :postal-code ""
                                  :temperatures {:today {:label "Today"
                                                         :value nil}
                                                 :tomorrow {:label "Tomorrow"
                                                            :value nil}}}))

(defn get-app-element []
  (gdom/getElement "app"))

(defn hello-world []
  [:div
   [:h1 {:class "app-title"} "Hello, World"]])

(defn title []
  [:h1 (:title @app-state)])

(defn temperature [temp]
  [:div {:class "temperature"}
   [:div {:class "value"}
    (:value temp)]
   [:h2 (:label temp)]])

(defn postal-code []
  [:div {:class "postal-code"}
   [:h3 "Enter your postal code"]
   [:input {:type "text"
            :placeholder "Postal Code"
            :value (:postal-code @app-state)}]
   [:button "Go"]])

(defn app []
  [:div {:class "app"}
   [title]
   [:div {:class "temperatures"}
    (for [temp (vals (:temperatures @app-state))]
      [temperature temp])]
   [postal-code]])

(defn mount [el]
  (rdom/render [hello-world] el))

;;(defn mount-app-element []
;;  (when-let [el (get-app-element)]
;;    (mount el)))
(defn mount-app-element []
  (rdom/render [app] (gdom/getElement "app")))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(def input (.createElement js/document "input"))
(.appendChild (.-body js/document) input)
(set! (.-placeholder input) "Enter something")
;;(defn handle-input [e]
;;  (swap! app-state assoc :text (-> e .-target .-value)))
(defn event-value [e]
  (-> e .-target .-value))
(defn update-text [v]
  (swap! app-state assoc :text v))
(defn handle-input [e]
  (update-text (event-value e)))
(set! (.-onkeyup input) handle-input)
