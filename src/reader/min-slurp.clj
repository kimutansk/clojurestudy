(ns reader.min-slurp
  (:import (java.io FileOutputStream OutputStreamWriter BufferedWriter)))

(defn min-slurp [dst content]
    (with-open [writer (-> dst FileOutputStream. OutputStreamWriter. BufferedWriter.)]
        (.write writer (str content))))

(defn make-writer [dst] (-> dst FileOutputStream. OutputStreamWriter. BufferedWriter.))