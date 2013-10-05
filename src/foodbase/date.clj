(ns foodbase.date
  (:use [clj-time.core :as time :exclude 'extend])
  (:use [clj-time.format :as time-format]))

(defn currentdate
  "Returns current ISO 8601 compliant date."
  []
  (let [current-date-time (time/to-time-zone (time/now) (time/default-time-zone))]
    (time-format/unparse
     (time-format/with-zone (time-format/formatters :date-time-no-ms) 
                            (.getZone current-date-time))
     current-date-time)))