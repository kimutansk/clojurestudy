; START: namespace
(ns reader.snake
  (:import (java.awt Color Dimension) 
	   (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener))
  (:use examples.import-static))
(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN)
; END: namespace

; Game Window Width
(def width 75)
; Game Window Height
(def height 50)
; Snake and Target Size
(def point-size 10)
; Display Update Interval
(def turn-millis 75)
; Snake Finish Length
(def win-length 5)
; Snake Direction Move Value
(def dirs { VK_LEFT [-1 0] VK_RIGHT [ 1 0] VK_UP [ 0 -1] VK_DOWN [ 0 1]})

; add Several Points
(defn add-points [& pts]
  (vec (apply map + pts)))

; Convert Point to Rectangles
(defn point-to-screen-rect [pt]
  (map #(* point-size %)
  [(pt 0) (pt 1) 1 1]))

; Create Apple
(defn create-apple []
    {:location [(rand-int width) (rand-int height)] :color (new Color 210 50 90) :type :apple})

; Create Snake
(defn create-snake []
    {:body (list [1 1]) :dir [1 0] :type :snake :color (new Color 15 160 70)})

; Move Snake
(defn move-snake [{:keys [body dir] :as snake} & grow]
    (assoc snake :body (cons (add-points (first body) dir)
        (if grow body (butlast body)))))

; Judge Win
(defn game-win? [{body :body}]
    (>= (count body) win-length))

; Judge overlap=lose
(defn head-overlaps-body? [{body :body}]
    (contains? (set (rest body)) (first body)))
(def lose? head-overlaps-body?)

; Judge eats?
(defn eats? [{[snake-head] :body} {apple :location}]
    (= snake-head apple))

; Turn Snake
(defn turn-snake [snake newdir]
    (assoc snake :dir newdir))

; Initialize Game
(defn initialize-game [snake apple]
  (dosync (ref-set apple (create-apple))
          (ref-set snake (create-snake)) nil))

; Update Snake Direction
(defn update-direction [snake direction]
  (when direction (dosync (alter snake turn-snake direction))))

; Update Snake Position
(defn update-position [snake apple]
  (dosync 
    (if (eats? @snake @apple)
      (do (ref-set apple (create-apple)) (alter snake move-snake :grow))
      (alter snake move-snake)) nil))

; Fill-Points
(defn fill-point [graphic pt color]
   (let [[x y width heignt] (point-to-screen-rect pt)]
     (.setColor graphic color)
     (.fillRect graphic x y width heignt)))

; Paint Multi Method
(defmulti paint (fn [graphic object & _] (:type object)))

(defmethod paint :apple [graphic {:keys [location color]}]
  (fill-point graphic location color))

(defmethod paint :snake [graphic {:keys [body color]}]
  (doseq [point body]
    (fill-point graphic point color)))

; Create Game Panel
(defn game-panel [frame snake apple]
  (proxy [JPanel ActionListener KeyListener] []
    (paintComponent [graphic]
      (proxy-super paintComponent graphic)
      (paint graphic @snake)
      (paint graphic @apple))
    (actionPerformed [event]
      (update-position snake apple)
      (when (lose? @snake) (initialize-game snake apple) (JOptionPane/showMessageDialog frame "You lose!"))
      (when (game-win? @snake) (initialize-game snake apple) (JOptionPane/showMessageDialog frame "You win!"))
      (.repaint this))
    (keyPressed [event]
      (update-direction snake (dirs (.getKeyCode event))))
    (getPreferredSize []
      (new Dimension (* (inc width) point-size) (* (inc height) point-size)))
    (keyReleased [event])
    (keyTyped [event])))

; Start Game
(defn game []
  (let [snake (ref (create-snake)) apple (ref (create-apple)) frame (new JFrame "Snake Game") panel (game-panel frame snake apple) timer (new Timer turn-millis panel)]
    (doto panel (.setFocusable true) (.addKeyListener panel))
    (doto frame (.add panel) (.pack) (.setVisible true))
    (.start timer)
    [snake, apple, timer]))