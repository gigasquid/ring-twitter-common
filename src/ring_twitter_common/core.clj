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
    (include-css "/css/ring-twitter-common.css")
    (include-js "/script/vendor/jquery-1.4.1.min.js")
    (include-js "/script/ring-twitter-common.js")
    (include-js "/script/vendor/Raptorize-Kit/jquery.raptorize.1.0.js")]
   [:div#top
    [:img#logo {:src "/image/incommon.png"}]
    [:span#top-title "Twitter Followers In Common"]]
   [:div#content
    [:h3 "Find out the how many of the followers that two Twitter users have in common"]
    [:div#form
     [:span#user1.user (label :title "Twitter Username #1")
              (text-field "user1text")]
            [:span#user2.user (label :title "Twitter Username #2")
             (text-field "user2text")]
            [:br]
            [:button.large {:id "run-button" :type "submit"} "Submit"]]
    [:div#results]
    [:div#footer
     [:p "Powered by Clojure Compojure on Heroku Cedar. Checkout the code on "
      [:a {:href "https://github.com/gigasquid/ring-twitter-common"} "github"]]]]))

(defn get-followers-in-common [name1 name2]
  (try
    (n-followers-in-common name1 name2 5)
    (catch java.lang.Exception e
      (do
        (println (.getMessage e))
        {:error
         ["Sorry about this ... but there is an error from Twitter"
         (.getMessage e)
         (html (link-to "/" [:div#raptor "Raptor Attack!!!"]))]}))))


(defn incommon-page [user1 user2]
  (let [results (get-followers-in-common user1 user2)
        size (:size results)
        users (:users results)
        error (:error results)]
    (html
    [:div#incommon
     [:h3 (str "Found " size  " Followers in Common between " user1 " and " user2)]
     [:h3 "Here are up to five followers that they share:"]
     (when error (unordered-list error))
     (if (empty? users)
       [:p "We couldn't find any followers in common."]
       (unordered-list users))])))

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



