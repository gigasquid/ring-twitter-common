(defproject ring-twitter-common "1.0.0-SNAPSHOT"
  :description "Example Ring app running on Heroku"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [ring/ring-core "0.3.8"]
                 [ring/ring-jetty-adapter "0.3.8"]
                 [hiccup "0.3.5"]
                 [clj-oauth "1.2.10"]
                 [com.twinql.clojure/clj-apache-http "2.3.1"]
                 [clojure-twitter "1.2.5"]
                 [compojure "0.6.2"]
                 [clj-config "0.1.0"]]
  :dev-dependencies [[lein-ring "0.4.0"]
                     [swank-clojure "1.2.1"]
                     [midje "1.1.1"]]
  :ring {:handler ring-twitter-common.core/app})
