■T■u■f■B■■■N■g■■■܂■■̓t■@■C■■ C:\Develop\Source\GitHub\clojurestudy\\classes ■͊■■݂■■܂■■B

Clojure 1.5.1

user=> (doc commute)
-------------------------

clojure.core/commute

([ref fun & args])

  Must be called in a transaction. Sets the in-transaction-value of
  ref to:

  (apply fun in-transaction-value-of-ref args)

  and returns the in-transaction-value of ref.

  At the commit point of the transaction, sets the value of ref to be:

  (apply fun most-recently-committed-value-of-ref args)

  Thus fun should be commutative, or, failing that, you must accept
  last-one-in-wins behavior.  commute allows for more concurrency than
  ref-set.

nil

user=> (defn add-message-update [msg] (dosync (alter current-messages conj msg)))
CompilerException java.lang.RuntimeException: Unable to resolve symbol: current-messages in this context, compiling:(NO_SOURCE_PATH:2:40) 

user=> (def current-messages (ref ()))
#'user/current-messages

user=> (defn add-message-update [msg] (dosync (alter current-messages conj msg)))
#'user/add-message-update

user=> (defrecord Message [user text])
user.Message

user=> (add-message-update (new user.Message "User1" "Message1"))
(#user.Message{:user "User1", :text "Message1"})

user=> (defn add-message-update [msg] (dosync (commute current-messages conj msg)))
#'user/add-message-update

user=> (add-message-update (new user.Message "User1" "Message1"))
(#user.Message{:user "User1", :text "Message1"} #user.Message{:user "User1", :text "Message1"})

user=> (add-message-update (new user.Message "User1" "Message1"))

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

(def student {:name "Student" :email "student@test.com"})
(def serializable-student (with-meta student {:serializable true :status 1}))
(def student-state (assoc serializable-student :state "state" :flag true))
(def account {:id "accountid" :pass "password"})
(def rank-account (with-meta account {:rank 10 :is-active true}))

(defn #^{:tag "String"} shout [arg] (.toUpperCase arg))

(ns subl_test.function)

(doc str)
(def print-range (for [index (range 1 3)] (do (println index) index)))

(let [m (re-matcher #"\w+" "the quick brown fox")]
(loop [match (re-find m)]
(when match
(println match)
(recur (re-find m)))))
| the
| quick

■T■u■f■B■■■N■g■■■܂■■̓t■@■C■■ C:\Develop\Source\GitHub\clojurestudy\\classes ■͊■■݂■■܂■■B

Clojure 1.5.1

user=> (import ’(java.io File))
ClassNotFoundException ■java.io  java.net.URLClassLoader$1.run (URLClassLoader.java:366)

RuntimeException Unmatched delimiter: )  clojure.lang.Util.runtimeException (Util.java:219)

user=> (import '(java.io File))
java.io.File

user=> (.listFiles (new File "."))
#<File[] [Ljava.io.File;@12f1eff>

user=> (seq (.listFiles (new File ".")))
(#<File .\.git> #<File .\.gitignore> #<File .\classes> #<File .\lib> #<File .\README.md> #<File .\src> #<File .\startRepl - ■R■s■[.bat> #<File .\startRepl.bat>)

user=> (map (fn [target] (.getName target)) (seq (.listFiles (new File ".")))
)
(".git" ".gitignore" "classes" "lib" "README.md" "src" "startRepl - ■R■s■[.bat" "startRepl.bat")

user=> )
RuntimeException Unmatched delimiter: )  clojure.lang.Util.runtimeException (Util.java:219)

user=> (map (fn [target] (.getName target)) (seq (.listFiles (new File "."))))
(".git" ".gitignore" "classes" "lib" "README.md" "src" "startRepl.bat")

user=> (map (fn [target] (.getName target)) (.listFiles (new File ".")))
(".git" ".gitignore" "classes" "lib" "README.md" "src" "startRepl.bat")

user=> (class (map (fn [target] (.getName target)) (.listFiles (new File "."))))
clojure.lang.LazySeq

user=> (class (seq (.listFiles (new File "."))))
clojure.lang.ArraySeq

user=> (file-seq (new File "."))
(#<File .> #<File .\.git> #<File .\.git\branches> #<File .\.git\config> #<File .\.git\description> #<File .\.git\FETCH_HEAD> #<File .\.git\HEAD> #<File .\.git\hooks> #<File .\.git\hooks\applypatch-msg.sample> #<File .\.git\hooks\commit-msg.sample> #<File .\.git\hooks\post-update.sample> #<File .\.git\hooks\pre-applypatch.sample> #<File .\.git\hooks\pre-commit.sample> #<File .\.git\hooks\pre-rebase.sample> #<File .\.git\hooks\prepare-commit-msg.sample> #<File .\.git\hooks\update.sample> #<File .\.git\index> #<File .\.git\info> #<File .\.git\info\exclude> #<File .\.git\logs> #<File .\.git\logs\HEAD> #<File .\.git\logs\refs> #<File .\.git\logs\refs\heads> #<File .\.git\logs\refs\heads\master> #<File .\.git\logs\refs\remotes> #<File .\.git\logs\refs\remotes\origin> #<File .\.git\logs\refs\remotes\origin\HEAD> #<File .\.git\logs\refs\remotes\origin\master> #<File .\.git\objects> #<File .\.git\objects\00> #<File .\.git\objects\00\1c601ea877efcb2bc7048eb2d30c2eec49089c> #<File .\.git\objects\05> #<File .\.git\objects\05\60d3fe37df6e1874e63d79dd1c257c9fde6801> #<File .\.git\objects\11> #<File .\.git\objects\11\b12c55acb2ef44a864561e07b3484166bd9866> #<File .\.git\objects\16> #<File .\.git\objects\16\1b3aad01909eea9a7a00b84c9fe8fbc4d51c69> #<File .\.git\objects\1d> #<File .\.git\objects\1d\e02cfb0a1daa3059680cc2c843c01a8c7069d6> #<File .\.git\objects\20> #<File .\.git\objects\20\d4e066c95fc913137d3cddc5548c5a4f6b2823> #<File .\.git\objects\2c> #<File .\.git\objects\2c\362943c2aa398591bb994af0477da7d578dc85> #<File .\.git\objects\40> #<File .\.git\objects\40\fbb28912a83e9f7d552d85eb0f27a8db916f05> #<File .\.git\objects\45> #<File .\.git\objects\45\e10c1854693b119ba87b7e44f827d548d6efc9> #<File .\.git\objects\46> #<File .\.git\objects\46\ac4dcba7aafa2ebbec35f67166cde58ee49517> #<File .\.git\objects\4b> #<File .\.git\objects\4b\a5222c611f143d3d1360ea74b9a30b79603ee2> #<File .\.git\objects\57> #<File .\.git\objects\57\875501546b0b2cf067a10d842a44c21df99c9d> #<File .\.git\objects\59> #<File .\.git\objects\59\fe689032451833b8ff16cf57fbaf3d4a5afbdc> #<File .\.git\objects\66> #<File .\.git\objects\66\0337af2bd7cedca96e2940afb6fecfe6517ce2> #<File .\.git\objects\66\c43f9aea95fe94c8c820168b73acbc77075bc2> #<File .\.git\objects\68> #<File .\.git\objects\68\464854761eff395911d091f40195fa8a848429> #<File .\.git\objects\68\69e8392d21cb5e930498b001bf158f8b50d76b> #<File .\.git\objects\68\e67139923ca2f37380f1a1fa2b639081fb1bbe> #<File .\.git\objects\6b> #<File .\.git\objects\6b\fa94da802c513e398d3f2db7422b18748a1d29> #<File .\.git\objects\6f> #<File .\.git\objects\6f\bc00bcdb7808601b08156eab9f88457ee146ce> #<File .\.git\objects\70> #<File .\.git\objects\70\521d64b169b72b98b5d4b19f5f99e286fe3112> #<File .\.git\objects\7a> #<File .\.git\objects\7a\cb14364c365392161e655e541fe2a6fc1107aa> #<File .\.git\objects\85> #<File .\.git\objects\85\fb5239e68bf632b97a68fe9f94930cb20b0cd6> #<File .\.git\objects\87> #<File .\.git\objects\87\f463d561fd5833090afbf70aea030e533f937f> #<File .\.git\objects\8a> #<File .\.git\objects\8a\df8627b6fdf6fd8e56c0282b45af4545c186de> #<File .\.git\objects\91> #<File .\.git\objects\91\caf68f5a8e02dc24099472fd9f25d558962701> #<File .\.git\objects\97> #<File .\.git\objects\97\65dd71de0d99f04f6ce982d3d529802b7f965c> #<File .\.git\objects\a0> #<File .\.git\objects\a0\4b2409a482ece009a567c99357b2cb90733df2> #<File .\.git\objects\a7> #<File .\.git\objects\a7\61740642a0c97ce111ef6b8555fe2c8822ff08> #<File .\.git\objects\ad> #<File .\.git\objects\ad\626d44e60d3ceca0810258dcd02966d6b3f6c9> #<File .\.git\objects\b8> #<File .\.git\objects\b8\2d370373a1ec689c64b835b9c8039457d8a363> #<File .\.git\objects\c7> #<File .\.git\objects\c7\4fbca6ec8219da10ff8a40f2a6db98e59d9112> #<File .\.git\objects\d6> #<File .\.git\objects\d6\b9f9f747cb085bc6dfd9b17a57328d4c1b7987> #<File .\.git\objects\d8> #<File .\.git\objects\d8\e31805f2920f87050165ec0f2e0dc0fc9b053b> #<File .\.git\objects\d9> #<File .\.git\objects\d9\20de0d5183d98e6bcb8b0df58c2ac80a814e4a> #<File .\.git\objects\dc> #<File .\.git\objects\dc\ee2bba4d75db7e5c3d948cf1df4404a9761d64> #<File .\.git\objects\e0> #<File .\.git\objects\e0\6e3023280b83f18fd2d095040c966ca78c6362> #<File .\.git\objects\e8> #<File .\.git\objects\e8\fc2b7399cb03e53e92a9445422b5a5d4c469ec> #<File .\.git\objects\e9> #<File .\.git\objects\e9\7a095a522fa3c705ae80cdb25275d16dbfdd55> #<File .\.git\objects\fc> #<File .\.git\objects\fc\7a38891743a4f5d74ac420ee214088e470fe41> #<File .\.git\objects\info> #<File .\.git\objects\pack> #<File .\.git\ORIG_HEAD> #<File .\.git\packed-refs> #<File .\.git\refs> #<File .\.git\refs\heads> #<File .\.git\refs\heads\master> #<File .\.git\refs\remotes> #<File .\.git\refs\remotes\origin> #<File .\.git\refs\remotes\origin\HEAD> #<File .\.git\refs\remotes\origin\master> #<File .\.git\refs\tags> #<File .\.gitignore> #<File .\classes> #<File .\classes\reader> #<File .\classes\reader\TaskList$fn__24.class> #<File .\classes\reader\TaskList$fn__36.class> #<File .\classes\reader\TaskList$fn__4.class> #<File .\classes\reader\TaskList$fn__45.class> #<File .\classes\reader\TaskList$loading__4910__auto__.class> #<File .\classes\reader\TaskList$task_list.class> #<File .\classes\reader\TaskList$_main.class> #<File .\classes\reader\TaskList.class> #<File .\classes\reader\TaskList__init.class> #<File .\lib> #<File .\lib\clojure-1.5.1.jar> #<File .\lib\clojure-contrib-1.2.0.jar> #<File .\lib\criterium-0.4.0.jar> #<File .\README.md> #<File .\src> #<File .\src\functions.clj> #<File .\src\reader> #<File .\src\reader\TaskList.clj> #<File .\src\Tset.clj> #<File .\src\[.clj> #<File .\startRepl.bat>)

user=> (count (file-seq (new File ".")))
142

user=> (doc file-seq)
-------------------------

clojure.core/file-seq

([dir])

  A tree seq on java.io.Files

nil

user=> (defn modified? [targetFile] (> (.lastModified file) (- (System/currentTimeMillis) 600000)))
CompilerException java.lang.RuntimeException: Unable to resolve symbol: file in this context, compiling:(NO_SOURCE_PATH:15:33) 

user=> (defn modified? [targetFile] (> (.lastModified file) (- (System/currentTimeMillis) 600000)))
CompilerException java.lang.RuntimeException: Unable to resolve symbol: file in this context, compiling:(NO_SOURCE_PATH:16:33) 

user=> (- (System/currentTimeMillis) 600000)
1369951661136

user=> (defn modified? [targetFile] (> (.lastModified targetFile) (- (System/currentTimeMillis) 600000)))
#'user/modified?

user=> (filter modified? (file-seq (new File ".")))
()

user=> (defn modified? [targetFile] (> (.lastModified targetFile) (- (System/currentTimeMillis) 6000000)))
#'user/modified?

user=> (filter modified? (file-seq (new File ".")))
(#<File .>)

user=> (use ’[clojure.java.io :only (reader)])
RuntimeException Unmatched delimiter: ]  clojure.lang.Util.runtimeException (Util.java:219)

RuntimeException Unmatched delimiter: )  clojure.lang.Util.runtimeException (Util.java:219)

user=> (use '[clojure.java.io :only (reader)])
nil

user=> (line-seq (reader "src/functions.clj")
)
("(defn hello \"Returns the form 'Hello, username.'\"" "    [username]" "    (str \"Hello, \" username))" "" "(defn hello-handlenoarg " "    \"Returns the form 'Hello, username. Default username is 'world'\"" "    ([] (hello-handlenoarg \"world\"))" "    ([username] (str \"Hello, \" username))" "    )" "" "(defn hello-multi" "    \"Returns the from 'Hello, usename and XXX persons'\"" "    ([] (hello-multi \"world\"))" "    ([username & persons] (str \"Hello, \" username \" and \" (count persons) \" persons\"))" "    )" "" "(defn target-words [text]" "    (let [target-word? #(> (count %) 2)]" "    (filter target-word? (re-split #\"\\W+\" text))))" "" "(defn make-hellofunc [hello-prefix]" "    (fn [username] (str hello-prefix \", \" username)))" "" "(defn triple [number] (* 3 number))" "" "(defn square-corner [bottom left size]" "    (let [top (+ bottom size) right (+ left size)]" "        [[bottom left] [top left] [top right] [bottom right]]))" "" "(defn greet-author [author]" "    (println (str \"Hello,\" (:first-name author))))" "" "(defn greet-author-sub [{authorname :first-name}]" "    (println (str \"Hello,\" authorname)))" "" "(defn is-small? [number]" "    (if (< number 100) \"yes\"))" "" "(defn is-small-withno? [number]" "    (if (< number 100) \"yes\" \"no\"))" "" "(defn is-small-print? [number]" "    (if (< number 100) " "        (do " "            (println \"Inputed Small Nimber \" number)" "            \"yes\")" "        (do" "            (println \"Inputed Big Number \" number)" "            \"no\")))" "" "(defn square [number]" "    (do " "        (println \"Inputed\" number)" "        (* number number)))" "" "(defn create-decr-array [number]" "    (loop [result [] num number]" "        (if (zero? num) result" "        (recur (conj result num) (dec num)))))" "" "(defn count-down [result number]" "    (if (zero? number) result" "        (recur (conj result number) (dec number))))" "" "(defn filtertest [chars target]" "    (filter chars target))" "" "(defn index-filter [pred coll]" "    (for [[index targetChar] (indexed coll) :when (pred targetChar)] index))" "" "(defn indexed-filter [pred coll]" "    (when pred" "    (for [[index targetChar] (indexed coll) :when (pred targetChar)] index)))" "" "(defn index-of-any [pred coll]" "    (first (index-filter pred coll)))" "" "(def student {:name \"Student\" :email \"student@test.com\"})" "(def serializable-student (with-meta student {:serializable true :status 1}))" "(def student-state (assoc serializable-student :state \"state\" :flag true))" "(def account {:id \"accountid\" :pass \"password\"})" "(def rank-account (with-meta account {:rank 10 :is-active true}))" "" "(defn #^{:tag \"String\"} shout [arg] (.toUpperCase arg))" "" "(ns subl_test.function)" "" "(doc str)")

user=> (line-seq (reader "src/functions.clj"))
("(defn hello \"Returns the form 'Hello, username.'\"" "    [username]" "    (str \"Hello, \" username))" "" "(defn hello-handlenoarg " "    \"Returns the form 'Hello, username. Default username is 'world'\"" "    ([] (hello-handlenoarg \"world\"))" "    ([username] (str \"Hello, \" username))" "    )" "" "(defn hello-multi" "    \"Returns the from 'Hello, usename and XXX persons'\"" "    ([] (hello-multi \"world\"))" "    ([username & persons] (str \"Hello, \" username \" and \" (count persons) \" persons\"))" "    )" "" "(defn target-words [text]" "    (let [target-word? #(> (count %) 2)]" "    (filter target-word? (re-split #\"\\W+\" text))))" "" "(defn make-hellofunc [hello-prefix]" "    (fn [username] (str hello-prefix \", \" username)))" "" "(defn triple [number] (* 3 number))" "" "(defn square-corner [bottom left size]" "    (let [top (+ bottom size) right (+ left size)]" "        [[bottom left] [top left] [top right] [bottom right]]))" "" "(defn greet-author [author]" "    (println (str \"Hello,\" (:first-name author))))" "" "(defn greet-author-sub [{authorname :first-name}]" "    (println (str \"Hello,\" authorname)))" "" "(defn is-small? [number]" "    (if (< number 100) \"yes\"))" "" "(defn is-small-withno? [number]" "    (if (< number 100) \"yes\" \"no\"))" "" "(defn is-small-print? [number]" "    (if (< number 100) " "        (do " "            (println \"Inputed Small Nimber \" number)" "            \"yes\")" "        (do" "            (println \"Inputed Big Number \" number)" "            \"no\")))" "" "(defn square [number]" "    (do " "        (println \"Inputed\" number)" "        (* number number)))" "" "(defn create-decr-array [number]" "    (loop [result [] num number]" "        (if (zero? num) result" "        (recur (conj result num) (dec num)))))" "" "(defn count-down [result number]" "    (if (zero? number) result" "        (recur (conj result number) (dec number))))" "" "(defn filtertest [chars target]" "    (filter chars target))" "" "(defn index-filter [pred coll]" "    (for [[index targetChar] (indexed coll) :when (pred targetChar)] index))" "" "(defn indexed-filter [pred coll]" "    (when pred" "    (for [[index targetChar] (indexed coll) :when (pred targetChar)] index)))" "" "(defn index-of-any [pred coll]" "    (first (index-filter pred coll)))" "" "(def student {:name \"Student\" :email \"student@test.com\"})" "(def serializable-student (with-meta student {:serializable true :status 1}))" "(def student-state (assoc serializable-student :state \"state\" :flag true))" "(def account {:id \"accountid\" :pass \"password\"})" "(def rank-account (with-meta account {:rank 10 :is-active true}))" "" "(defn #^{:tag \"String\"} shout [arg] (.toUpperCase arg))" "" "(ns subl_test.function)" "" "(doc str)")

user=> (line-seq (reader "src/functions.clj"))
("(defn hello \"Returns the form 'Hello, username.'\"" "    [username]" "    (str \"Hello, \" username))" "" "(defn hello-handlenoarg " "    \"Returns the form 'Hello, username. Default username is 'world'\"" "    ([] (hello-handlenoarg \"world\"))" "    ([username] (str \"Hello, \" username))" "    )" "" "(defn hello-multi" "    \"Returns the from 'Hello, usename and XXX persons'\"" "    ([] (hello-multi \"world\"))" "    ([username & persons] (str \"Hello, \" username \" and \" (count persons) \" persons\"))" "    )" "" "(defn target-words [text]" "    (let [target-word? #(> (count %) 2)]" "    (filter target-word? (re-split #\"\\W+\" text))))" "" "(defn make-hellofunc [hello-prefix]" "    (fn [username] (str hello-prefix \", \" username)))" "" "(defn triple [number] (* 3 number))" "" "(defn square-corner [bottom left size]" "    (let [top (+ bottom size) right (+ left size)]" "        [[bottom left] [top left] [top right] [bottom right]]))" "" "(defn greet-author [author]" "    (println (str \"Hello,\" (:first-name author))))" "" "(defn greet-author-sub [{authorname :first-name}]" "    (println (str \"Hello,\" authorname)))" "" "(defn is-small? [number]" "    (if (< number 100) \"yes\"))" "" "(defn is-small-withno? [number]" "    (if (< number 100) \"yes\" \"no\"))" "" "(defn is-small-print? [number]" "    (if (< number 100) " "        (do " "            (println \"Inputed Small Nimber \" number)" "            \"yes\")" "        (do" "            (println \"Inputed Big Number \" number)" "            \"no\")))" "" "(defn square [number]" "    (do " "        (println \"Inputed\" number)" "        (* number number)))" "" "(defn create-decr-array [number]" "    (loop [result [] num number]" "        (if (zero? num) result" "        (recur (conj result num) (dec num)))))" "" "(defn count-down [result number]" "    (if (zero? number) result" "        (recur (conj result number) (dec number))))" "" "(defn filtertest [chars target]" "    (filter chars target))" "" "(defn index-filter [pred coll]" "    (for [[index targetChar] (indexed coll) :when (pred targetChar)] index))" "" "(defn indexed-filter [pred coll]" "    (when pred" "    (for [[index targetChar] (indexed coll) :when (pred targetChar)] index)))" "" "(defn index-of-any [pred coll]" "    (first (index-filter pred coll)))" "" "(def student {:name \"Student\" :email \"student@test.com\"})" "(def serializable-student (with-meta student {:serializable true :status 1}))" "(def student-state (assoc serializable-student :state \"state\" :flag true))" "(def account {:id \"accountid\" :pass \"password\"})" "(def rank-account (with-meta account {:rank 10 :is-active true}))" "" "(defn #^{:tag \"String\"} shout [arg] (.toUpperCase arg))" "" "(ns subl_test.function)" "" "(doc str)")

user=> (take 2 (line-seq (reader "src/functions.clj")))
("(defn hello \"Returns the form 'Hello, username.'\"" "    [username]")

user=> (with-open (take 2 (line-seq (reader "src/functions.clj"))))
IllegalArgumentException with-open requires a vector for its binding in user:29  clojure.core/with-open (core.clj:3450)

user=> (doc with-open)
-------------------------

clojure.core/with-open

([bindings & body])

Macro

  bindings => [name init ...]

  Evaluates body in a try expression with names bound to the values
  of the inits, and a finally clause that calls (.close name) on each
  name in reverse order.

nil

user=> (with-open [targetreader (reader "src/functions.clj")] (take 2 (line-seq targetreader)))
IOException Stream closed  java.io.BufferedReader.ensureOpen (BufferedReader.java:115)

(user=> (with-open [targetreader (reader "src/functions.clj")] (count (line-seq targetreader)))
88

user=> (with-open [targetreader (reader "src/functions.clj")] (take 2 (doall-(line-seq targetreader))))
CompilerException java.lang.RuntimeException: Unable to resolve symbol: doall- in this context, compiling:(NO_SOURCE_PATH:33:64) 

user=> (with-open [targetreader (reader "src/functions.clj")] (take 2 (doall (line-seq targetreader))))
("(defn hello \"Returns the form 'Hello, username.'\"" "    [username]")

user=> (with-open [targetreader (reader "src/functions.clj")] (count (filter #(re-find #"\S" %) (line-seq targetreader))))
66

user=> 

(defn non-blank? [line] (if (re-find #"\S" line) true false))
(defn clojure-realline [file] (with-open [targetreader (reader file)] (count (filter non-blank? (line-seq targetreader)))))
(defn clojure-source? [file] (.endsWith (.toString file) ".clj"))
(defn clojure-loc-dir [basefile] (reduce + (for [file (file-seq basefile) :when (clojure-source? file)] (clojure-realline file))))

(use '[clojure.xml :only (parse)])


(xml-seq (parse (new java.io.File "clojure-master/pom.xml")))

(for [x (xml-seq (parse (new java.io.File "clojure-master/pom.xml"))) :when (= (:tag x) :groupId)] :content)

(defn calc-fibo [n]
    (cond 
      (= n 0) 0
      (= n 1) 1
      :else (+ (calc-fibo (- n 1))  (calc-fibo (- n 2)))))

(defn calc-fibo-tail [n]
  (letfn [(fib [current next n] 
             (if (zero? n) current (fib next (+ current next) (dec n))))]
  (fib 0N 1N n)))

(defn calc-fibo-recur [n]
  (letfn [(fib [current next n] 
             (if (zero? n) current (recur next (+ current next) (dec n))))]
  (fib 0N 1N n)))

(defn calc-fibo-lazy 
  ([] (concat [0 1] (calc-fibo-lazy 0N 1N)))
  ([a b]
    (let [n (+ a b)]
        (lazy-seq (cons n (calc-fibo-lazy b n))))))

(defn calc-fibo-lazy 
  ([] (concat [0 1]))
  ([a b]
    (let [n (+ a b)]
        (lazy-seq (cons n (calc-fibo-lazy b n))))))


(defn multi
    ([] 1)
    ([n] (* n n))
    ([n m] (* n m )))

(defn calc-fibo-seqlib (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))

(defn count-pattern [vect]
  (loop [cnt 0 vect vect]
    (if (empty? vect) cnt
    (recur (if (and (= :A (first vect)) (= :B (second vect))) (inc cnt) cnt) (rest vect)))))


(defn coll-pairs [coll]
    (let [take-pair #(when (next %) (take 2 %))]
        (lazy-seq
          (when-let [pair (seq (take-pair coll))]
            (cons pair (coll-pairs (rest coll)))))))

(defn count-pattern-pair [vect]
    (count (filter (fn [elem] (and (= :A (first elem)) (= :B (second elem)))) (coll-pairs vect))))



(defn count-heads-pairs [coll]
(count (filter (fn [pair] (every? #(= :h %) pair))
(by-pairs coll))))


(declare stack-odd? stack-even?)

(defn stack-odd? [n]
    (if (= n 0) false #(stack-even? (dec n))))

(defn stack-even? [n]
    (if (= n 0) true #(stack-odd? (dec n))))

(defn parity [n]
    (loop [n n pari 0]
        (if (= n 0) pari
         (recur (dec n) (- 1 pari)))))

(defn stack-odd? [n] (= 1 (parity n)))
(defn stack-even? [n] (= 0 (parity n)))

(defn stack-even? [n]
    (if (= n 0) true (stack-odd? (dec n))))

(■T■u■f■B■■■N■g■■■܂■■̓t■@■C■■ C:\Develop\Source\GitHub\clojurestudy\\classes ■͊■■݂■■܂■■B

Clojure 1.5.1

user=> (def current-state (ref "INACTIVE"))
#'user/current-state

user=> @current-state

"INACTIVE"

user=> deref current-state

#<core$deref clojure.core$deref@687963ac>

#<Ref@3a9e7c70: "INACTIVE">

user=> (deref current-state
)
"INACTIVE"

user=> current-state

#<Ref@3a9e7c70: "INACTIVE">

user=> (ref-set current-state
 "ACTIVE")
IllegalStateException No transaction running  clojure.lang.LockingTransaction.getEx (LockingTransaction.java:208)

user=> (dosync (ref-set current-state "ACTIVE"))
"ACTIVE"

user=> @current-state
"ACTIVE"

user=> (deref current-state
)
"ACTIVE"

user=> (def current-flag (ref "true"))
#'user/current-flag

user=> (dosync (ref-set current-state "INACTIVE") (ref-set current-flag
 "false"))
"false"

user=> @current-state
"INACTIVE"

user=> @current-flag

"false"

user=> (defrecord Message ["user" "text"])
ClassCastException java.lang.String cannot be cast to clojure.lang.IObj  clojure.core/with-meta (core.clj:214)

user=> (defrecord Message [user text])
user.Message

user=> (new user.Message "User1" "Message1")
#user.Message{:user "User1", :text "Message1"}

user=> (def current-messages (ref ()))
#'user/current-messages

(defn add-message [msg] (dosync (ref-set current-messages (cons msg @current-messages))))

(defn add-message-update [msg] (dosync (alter messages conj msg)))
(defn add-message-update [msg] (dosync (commute messages conj msg)))

(defrecord Message [user text])

(def messages (ref () :validator #(every? #(and (:sender %) (:text %)))))

(def validate-message-list
(partial every? #(and (:sender %) (:text %))))

■T■u■f■B■■■N■g■■■܂■■̓t■@■C■■ C:\Develop\Source\GitHub\clojurestudy\\classes ■͊■■݂■■܂■■B

Clojure 1.5.1

user=> (doc ref)
-------------------------

clojure.core/ref

([x] [x & options])

  Creates and returns a Ref with an initial value of x and zero or
  more options (in any order):

  :meta metadata-map

  :validator validate-fn

  :min-history (default 0)
  :max-history (default 10)

  If metadata-map is supplied, it will become the metadata on the
  ref. validate-fn must be nil or a side-effect-free fn of one
  argument, which will be passed the intended new state on any state
  change. If the new state is unacceptable, the validate-fn should
  return false or throw an exception. validate-fn will be called on
  transaction commit, when all refs have their final values.

  Normally refs accumulate history dynamically as needed to deal with
  read demands. If you know in advance you will need history you can
  set :min-history to ensure it will be available when first needed (instead
  of after a read fault). History is limited, and the limit can be set
  with :max-history.

nil

user=> (def messages (ref () :validator (every? #(and (:sender %) (:text %)))))
ArityException Wrong number of args (1) passed to: core$every-QMARK-  clojure.lang.AFn.throwArity (AFn.java:437)

user=> (def messages (ref () :validator #(every? #(and (:sender %) (:text %)))))
IllegalStateException Nested #()s are not allowed  clojure.lang.LispReader$FnReader.invoke (LispReader.java:638)

CompilerException java.lang.RuntimeException: Can't take value of a macro: #'clojure.core/and, compiling:(NO_SOURCE_PATH:0:0) 

CompilerException java.lang.RuntimeException: Unable to resolve symbol: % in this context, compiling:(NO_SOURCE_PATH:3:49) 

CompilerException java.lang.RuntimeException: Unable to resolve symbol: % in this context, compiling:(NO_SOURCE_PATH:3:61) 

RuntimeException Unmatched delimiter: )  clojure.lang.Util.runtimeException (Util.java:219)

RuntimeException Unmatched delimiter: )  clojure.lang.Util.runtimeException (Util.java:219)

RuntimeException Unmatched delimiter: )  clojure.lang.Util.runtimeException (Util.java:219)

RuntimeException Unmatched delimiter: )  clojure.lang.Util.runtimeException (Util.java:219)

user=> (def validate-message-list
(partial every? #(and (:sender %) (:text %))))
#'user/validate-message-list

user=> (class validate-message-list
)
clojure.core$partial$fn__4190

user=> (class messages-validator 
(partial every? #(and (:sender %) (:text %))))
CompilerException java.lang.RuntimeException: Unable to resolve symbol: messages-validator in this context, compiling:(NO_SOURCE_PATH:8:1) 

user=> (defn validate [msg] (and (:sender msg) (:text msg)))
#'user/validate

user=> (validate (new user.Message "User1" "Message1"))
CompilerException java.lang.ClassNotFoundException: user.Message, compiling:(NO_SOURCE_PATH:11:11) 

user=> (defrecord Message [sender text])
user.Message

user=> (validate (new user.Message "User1" "Message1"))
"Message1"

user=> (defrecord MessageText [sender message])
user.MessageText

user=> (validate (new user.MessageText "User1" "Message1"))
nil

user=> (def messages (ref () :validator validate)
)
IllegalStateException Invalid reference state  clojure.lang.ARef.validate (ARef.java:33)

user=> (def messages (ref () :validator validate))
IllegalStateException Invalid reference state  clojure.lang.ARef.validate (ARef.java:33)

user=> (defn validate-func [msg] (and (:sender msg) (:text msg)))
#'user/validate-func

user=> (def messages (ref () :validator validate-func))
IllegalStateException Invalid reference state  clojure.lang.ARef.validate (ARef.java:33)

user=> (def validate-message (partial every? (validate-func %)))
CompilerException java.lang.RuntimeException: Unable to resolve symbol: % in this context, compiling:(NO_SOURCE_PATH:21:39) 

user=> (def validate-message (partial every? #(validate-func %)))
#'user/validate-message

user=> (def messages (ref () :validator validate-message))
#'user/messages

user=> (defn add-message-update [msg] (dosync (alter current-messages conj msg)))
CompilerException java.lang.RuntimeException: Unable to resolve symbol: current-messages in this context, compiling:(NO_SOURCE_PATH:24:40) 

user=> (defn add-message-update [msg] (dosync (alter messages conj msg)))
#'user/add-message-update

user=> (defn add-message-para [msg] (dosync (commute messages conj msg)))
#'user/add-message-para

user=> (defn add-message-update "Test")
IllegalArgumentException Parameter declaration missing  clojure.core/assert-valid-fdecl (core.clj:6716)

user=> (defn add-message-update (new user.Message "User1" "Message1"))
IllegalArgumentException Parameter declaration new should be a vector  clojure.core/assert-valid-fdecl (core.clj:6732)

user=> (defn add-message-update (new user.MessageText "User1" "Message1"))
IllegalArgumentException Parameter declaration new should be a vector  clojure.core/assert-valid-fdecl (core.clj:6732)

user=> (def validate-message-list
(partial every? #(and (:sender %) (:text %))))
#'user/validate-message-list

user=> (def messages (ref () :validator validate-message-list))
#'user/messages

user=> (defn add-message-update (new user.MessageText "User1" "Message1"))
IllegalArgumentException Parameter declaration new should be a vector  clojure.core/assert-valid-fdecl (core.clj:6732)

user=> (defn add-message-update (new user.MessageText "User1" "Message1"))
IllegalArgumentException Parameter declaration new should be a vector  clojure.core/assert-valid-fdecl (core.clj:6732)

user=> (new user.MessageText "User1" "Message1")
#user.MessageText{:sender "User1", :message "Message1"}

user=> (def messages (ref ())
)
#'user/messages

user=> (new user.MessageText "User1" "Message1")
#user.MessageText{:sender "User1", :message "Message1"}

user=> (defn add-message-update (new user.MessageText "User1" "Message1"))
IllegalArgumentException Parameter declaration new should be a vector  clojure.core/assert-valid-fdecl (core.clj:6732)

user=> (defrecord MessageText [sender message])
user.MessageText

user=> (defn add-message-update (new user.MessageText "User1" "Message1"))
IllegalArgumentException Parameter declaration new should be a vector  clojure.core/assert-valid-fdecl (core.clj:6732)

user=> (defn add-message-update (user.MessageText. "User1" "Message1"))
IllegalArgumentException Parameter declaration user.MessageText. should be a vector  clojure.core/assert-valid-fdecl (core.clj:6732)

user=> 