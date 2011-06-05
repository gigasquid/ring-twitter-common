(ns ring-twitter-common.core
  (:use ring.util.response
        ring.adapter.jetty
        (hiccup form-helpers page-helpers core)
        ring-twitter-common.incommon
        compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))


(defn main-page []
  (html
   [:head
    [:title "Twitter Followers In Common"]
    (include-css "/css/ring-twitter-common.css")]
   [:div#content
    [:h1 "Twitter Followers In Common"]
    [:p "Find out the followers in common between two usernames"]
     (form-to {:id "usernames-submission"} [:post "/incommon"]
            (label :title "Twitter Username #1")
            (text-field "user1")
            (label :title "Twitter Username #2")
            (text-field "user2")
            [:button.large {:id "run-button" :type "submit"} "Submit"])
    [:div#footer
     [:p "Powered by Clojure Compojure on Heroku!"]]]))

(defn get-followers-in-common [name1 name2]
  (followers-in-common name1 name2))

(defn incommon-page [user1 user2]
  (html
   [:div#incommon
    [:h3 (str "Followers in Common of " user1 " and " user2)]
    (unordered-list (get-followers-in-common user1 user2))]))


(defroutes main-routes
  (GET "/" [] (main-page))
  (POST "/incommon" [user1 user2] (incommon-page user1 user2))
  (route/resources "/")
  (route/resources "/incommon")
  (route/files "/" {:root "resources/public"})
  (route/not-found "Page not found"))

(def app
  (handler/site main-routes))


(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))



