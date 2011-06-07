(ns ring-twitter-common.test.incommon
  (:use [ring-twitter-common.incommon] :reload)
  (:use [clojure.test])
  (:use [midje.sweet]))

(with-test
  (def carin-followers  (twitter/followers-of-name "carinmeier"))
  (def carin-cinijs-followers-in-common (followers-in-common "carinmeier" "cincijs"))
  (def n-carin-cinijs-followers-in-common (n-followers-in-common "carinmeier" "cincijs" 2))
  n-carin-cinijs-followers-in-common
       
  (fact (< 0 (.size carin-followers)) => true)
  (fact (< 0 (.size carin-cinijs-followers-in-common)) => true)
  (fact (get-screen-name-from-id "205291621") => "cincijs"))
  (fact (= 2 (.size n-carin-cinijs-followers-in-common)) => true)
