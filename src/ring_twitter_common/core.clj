(ns ring-twitter-common.core
  (:use ring.util.response
        ring.adapter.jetty
        (hiccup form-helpers page-helpers core)
        clojure-twitter-in-common.core))

(defn main-page []
  (html
   [:div#header
    [:h1 "Twitter Followers In Common"]
    [:p "Find out the common followers between two usernames"]]
   (form-to {:id "usernames-submission"} [:post "/problems/submit"])))

(defn get-followers-in-common [name1 name2]
  (followers-in-common name1 name2))


(defn app [req]
  (response (main-page)))

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))



