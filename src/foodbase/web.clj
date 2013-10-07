(ns foodbase.web
	(:use compojure.core)
	(:use [foodbase.food :as food])
	(:use [hiccup core form page element])
	(:require [compojure.route :as route]))

(defn layout [& content]
	(html5
		[:head [:title "Foodbase"]]
		[:body content]))

(defn show-list-row [item]
	[:li
		(link-to (str "/ingredients/" (:id item)) (str (:name item)))
	])

(defn show-ingredient-list []
	[:ul (map show-list-row
		(food/get-ingredient-list))])

(defn show-food-list []
	[:ul (map show-list-row
		(food/get-food-list))])

(defn index-page []
	(layout
		[:h1 "Foodbase"]
		[:h2 "Food list"] (show-food-list)
		[:h2 "Ingredient list"] (show-ingredient-list)
))
