(ns reader.TaskList
  (:gen-class ; <label id="code.TaskList.genclass"/>
   :extends org.xml.sax.helpers.DefaultHandler ; <label id="code.TaskList.extends"/>
   :state state ; <label id="code.TaskList.state"/>
   :init init) ; <label id="code.TaskList.init"/>
  (:use [clojure.java.io :only (reader)])
  (:import [java.io File]
           [org.xml.sax InputSource]
	   [org.xml.sax.helpers DefaultHandler]
	   [javax.xml.parsers SAXParserFactory]))

(defn task-list [filepath]
  (let [handler (new reader.TaskList)]
    (.parse (.. SAXParserFactory newInstance new SAXParser)
           (new InputSource (reader (new File filepath))) handler)
  @(.state handler)))

(defn -main [& args]
    (doseq [arg args]
        (println (task-list arg))))


