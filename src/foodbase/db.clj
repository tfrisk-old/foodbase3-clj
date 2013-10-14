(ns foodbase.db
	(:require [taoensso.carmine :as car :refer (wcar)]))

;Redis connection
(def server1-conn {
	:pool {}
	:spec {:host "redishost" :port 6379}})
(defmacro wcar* [& body] `(car/wcar server1-conn ~@body))

;Redis wrappers
(defn redis-get
	"Get data from database"
	[dbkey]
	(wcar* (car/get dbkey)))

(defn redis-set
	"Save data to database"
	[dbkey dbval]
	(wcar* (car/set dbkey dbval)))

(defn redis-del
	"Save data to database"
	[dbkey]
	(wcar* (car/del dbkey)))

(defn save-item-map
	"Save item, uses :id keyword as database key. Be sure that data contains :id keyword."
	[data]
	(redis-set (:id data) data))

(defn load-item-by-id
	"Load item by id"
	[id]
	(redis-get (str id)))

(defn load-manufacturer-list
	"Load list of all manufacturers"
	[]
	(redis-get "manufacturerlist"))

(defn save-manufacturer-list
	"Save list of all manufacturers"
	[newlist]
	(redis-set "manufacturerlist" newlist))

(defn load-ingredient-list
	"Load list of all ingredients"
	[]
	(redis-get "ingredientlist"))

(defn save-ingredient-list
	"Save list of all ingredients"
	[newlist]
	(redis-set "ingredientlist" newlist))

(defn load-food-list
	"Load list of all foods"
	[]
	(redis-get "foodlist"))

(defn save-food-list
	"Save list of all foods"
	[newlist]
	(redis-set "foodlist" newlist))
