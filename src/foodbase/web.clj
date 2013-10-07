(ns foodbase.web
	(:use compojure.core)
	(:use [foodbase.food :as food])
	(:use [hiccup core form page element])
	(:require [compojure.route :as route]))

(defn layout [& content]
	(html5
		[:head [:title "Foodbase"]]
		[:body content]))

(defn print-ingredient-item [item]
	[:li
		(link-to (str "/details/" (:id item)) (str (:text item)))
	])

(defn print-nutrition-item [item]
	[:li (str (key item) ":" (val item))])

(defn print-tag [tag]
	[:li (str (key tag))])

(defn show-food-details [id]
	(layout
		[:h1 "Food detail page"]
		(let [fooddata (food/get-food-data id)]
			[:ul "Basic information"
			[:li "Name: " (:name fooddata)]
			[:li "Description: " (:description fooddata)]
			[:li "Manufacturer: " (:manufacturer fooddata)]
			[:li "Barcode: " (:barcode fooddata)]
			[:li "Weight: " (:weight fooddata)]
			[:li "Origin: " (:origin fooddata)]])
		(let [ingredients (food/get-food-ingredients id)]
			[:ul "Ingredients" (map print-ingredient-item ingredients)])
		(let [nutritions (food/get-food-nutritions id)]
			[:ul "Nutritions" (map print-nutrition-item nutritions)])
		
	(link-to "/" "Back to index")))

(defn show-ingredient-details [id]
	(layout
		[:h1 "Ingredient detail page"]
		(let [ingredientdata (food/get-ingredient-data id)]
			[:ul "Basic information"
			[:li "Name: " (:name ingredientdata)]])
		(let [tags (food/get-ingredient-details id :tags)]
			[:ul "Tags" (map print-tag tags)])
		
	(link-to "/" "Back to index")))

(defn detail-page [id]
	(case (first id)
		\f (show-food-details id)
		\i (show-ingredient-details id)))

; ----------- index page ----------------
(defn show-list-row [item]
	[:li
		(link-to (str "/details/" (:id item)) (str (:name item)))
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
