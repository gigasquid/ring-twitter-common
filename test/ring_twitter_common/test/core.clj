(ns ring-twitter-common.test.core
  (:use [ring-twitter-common.core] :reload)
  (:use [clojure.test]
        [midje.sweet]))

(with-test
  (def result (incommon-page "carinmeier" "zero"))
  (def match  (re-seq #"We couldn't find any followers in common." result))

  (testing "When there are no twitter users in common"
    (fact match =not=> nil?)))

(with-test
  (def result (incommon-page "carinmeier" "asdfasdsdfasdffasdfa"))
  (def match  (re-seq #"Sorry about this." result))

  (testing "When there a twitter user cannot be found"
    (fact match =not=> nil?)))


