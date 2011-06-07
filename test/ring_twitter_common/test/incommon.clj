(ns ring-twitter-common.test.incommon
  (:use [ring-twitter-common.incommon] :reload)
  (:use [clojure.test])
  (:use [midje.sweet]))

(with-test
  (def carin-followers  (twitter/followers-of-name "carinmeier"))
  (def n-carin-cinijs-followers-in-common (n-followers-in-common "carinmeier" "cincijs" 2))
  (def size (:size n-carin-cinijs-followers-in-common))
  (def users (:users n-carin-cinijs-followers-in-common))

       
  (fact (< 0 (.size carin-followers)) => true)
  (fact (get-screen-name-from-id "205291621") => "cincijs")
  (fact (< 0 size) => true)
  (fact (= 2 (count users)) => true))
