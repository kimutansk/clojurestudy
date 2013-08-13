

(defprotocol Filter
  (init-filter [filter])
  (filter-output-stream [filter])
  (filter-input-stream [filter]))