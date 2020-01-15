(ns random-password-generator.core
    (:require
      [reagent.core :as r] [clojure.edn :as edn]))

(def password-length (r/atom 0))
(def password (r/atom ""))
;; -------------------------
;; Views
(def characters ["!","@","#","$","%","^","&","*",".","+","-","="])
(def alphabets ["a","b","c","d"])
(def numbers (range 0 10))
(def collection {0 numbers 1 characters 2 alphabets})
(defn get-characters [] (collection (rand-int (count collection))))
(defn get-rand-character [] (let [characters (get-characters)] (nth characters (rand-int (count characters)))))
(defn create-password [password-length] (apply str (take (edn/read-string password-length) (repeatedly get-rand-character))))

(defn submit [] (reset! password (create-password @password-length)))

(defn home-page []
  [:div [:input
{:type "number"
:placeholder "Password length"
:on-blur #(reset! password-length (-> % .-target .-value))}
]
[:button {:on-click submit} "Submit"]

[:div 
@password]
])

;; -------------------------#(reset! word (-> % .-target .-value))
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))

