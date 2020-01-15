(ns random-password-generator.prod
  (:require
    [random-password-generator.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
