(ns tools.drilling.inxs-test
  (:require [clojure.test :refer [deftest testing is]]))

(deftest test-runner-smoke
  (testing "Test runner working?"
    (is (= 1 1))))
