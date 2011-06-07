(ns ring-twitter-common.incommon
  (:use [clojure.contrib.logging])
  (:require [twitter]  [oauth.client :as oauth]))


;; This suppress warnings on the http client - Invalid Cookie Header
(defn set-log-level! [level]
  "Sets the root logger's level, and the level of all of its Handlers, to level.
   Level should be one of the constants defined in java.util.logging.Level."
  (let [logger (.getLogger (impl-get-log ""))]
    (.setLevel logger level)
    (doseq [handler (.getHandlers logger)]
      (. handler setLevel level))))

(set-log-level! java.util.logging.Level/SEVERE)

(def oauth-access-token 
     "mNGQGY4H2CNHJyeVMgeeA")
(def oauth-access-token-secret
  "Brpf2a4140DmwRgoLsTq8YG4jqJ9BSjkBQYv4qXEVyQ")

(def oauth-consumer (oauth/make-consumer oauth-access-token
                                         oauth-access-token-secret
                                         "https://api.twitter.com/oauth/request_token"
                                         "https://api.twitter.com/oauth/access_token"
                                         "https://api.twitter.com/oauth/authorize"
                                         :hmac-sha1))
(defn get-screen-name-from-id [twitterid]
    (:screen_name (twitter/show-user-by-id  (str twitterid))))

  
(defn followers-in-common [username1 username2]
  (pmap get-screen-name-from-id (clojure.set/intersection (set (twitter/followers-of-name username1))
                                                          (set (twitter/followers-of-name username2)))))

(defn n-followers-in-common [username1 username2 n]
  (pmap get-screen-name-from-id (take n (clojure.set/intersection (set (twitter/followers-of-name username1))
                                                           (set (twitter/followers-of-name username2))))))
