(defn hello "Returns the form 'Hello, username.'"
    [username]
    (str "Hello, " username))

(defn hello-handlenoarg 
    "Returns the form 'Hello, username. Default username is 'world'"
    ([] (hello-handlenoarg "world"))
    ([username] (str "Hello, " username))
    )

(defn hello-multi
    "Returns the from 'Hello, usename and XXX persons'"
    ([] (hello-multi "world"))
    ([username & persons] (str "Hello, " username " and " (count persons) " persons"))
    )

(defn target-words [text]
    (let [target-word? #(> (count %) 2)]
    (filter target-word? (re-split #"\W+" text))))

(defn make-hellofunc [hello-prefix]
    (fn [username] (str hello-prefix ", " username)))

(defn triple [number] (* 3 number))

(defn square-corner [bottom left size]
    (let [top (+ bottom size) right (+ left size)]
        [[bottom left] [top left] [top right] [bottom right]]))

(defn greet-author [author]
    (println (str "Hello," (:first-name author))))

(defn greet-author-sub [{authorname :first-name}]
    (println (str "Hello," authorname)))

(defn is-small? [number]
    (if (< number 100) "yes"))

(defn is-small-withno? [number]
    (if (< number 100) "yes" "no"))

(defn is-small-print? [number]
    (if (< number 100) 
        (do 
            (println "Inputed Small Nimber " number)
            "yes")
        (do
            (println "Inputed Big Number " number)
            "no")))

(defn square [number]
    (do 
        (println "Inputed" number)
        (* number number)))

(defn create-decr-array [number]
    (loop [result [] num number]
        (if (zero? num) result
        (recur (conj result num) (dec num)))))

(defn count-down [result number]
    (if (zero? number) result
        (recur (conj result number) (dec number))))

(defn filtertest [chars target]
    (filter chars target))

(defn index-filter [pred coll]
    (for [[index targetChar] (indexed coll) :when (pred targetChar)] index))

(defn indexed-filter [pred coll]
    (when pred
    (for [[index targetChar] (indexed coll) :when (pred targetChar)] index)))

(defn index-of-any [pred coll]
    (first (index-filter pred coll)))

