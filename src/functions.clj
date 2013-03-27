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
