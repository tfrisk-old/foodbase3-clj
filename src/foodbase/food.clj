(ns foodbase.food
	(:use 	[foodbase.db :as db]
			[foodbase.date :as date]
			[foodbase.uuid :as uuid]))

(defn get-ingredient-data [searchid]
	"Get ingredient data"
	(first ; return first result assuming there will always be only one result for a given id
		(filter identity ; filter out nil entries
		(into [] (map (fn [m] ; go thru the whole collection
			(if (= (:id m) searchid) m))
			db/ingredientlist))))) ; collection to use for searching

(defn get-food-data [searchid]
	"Get food data"
	(first ; return first result assuming there will always be only one result for a given id
		(filter identity ; filter out nil entries
		(into [] (map (fn [m] ; go thru the whole collection
			(if (= (:id m) searchid) m))
			db/foodlist))))) ; collection to use for searching

(defn get-food-ingredients
	"Return ingredients for specified food"
	[foodid]
	(:ingredients (get-food-data foodid)))

(defn get-food-nutritions
	"Return nutrition values for specified food"
	[foodid]
	(:nutritions (get-food-data foodid)))

(defn find-item-category
	"Find correct input data for given id"
	;TODO: data handling should be better. Currently this returns the whole data sets
	[id]
	(case (first id)
		\f get-food-data
		\i get-ingredient-data))

(defn get-tags
	"Get all tags for specified item"
	[id]
	(let [datasource (find-item-category id)]
		(:tags (datasource id))))

(defn is-tagged-with?
	"Check if item is tagged with specified tag"
	[id tag]
	(let [lst (find-item-category id)]
		(tag (get-tags id))))

(defn food-contains-ingredient?
	"Check if food contains ingredient. Returns vector [n desc] if found, otherwise nil"
	;TODO: data structure changed and this isn't working anymore
	[foodid ingredientid]
	((keyword ingredientid) (get-food-ingredients foodid)))

(defn search-by-id
	"Search items by id. The first character of an id dictates the item type (ingredient, food, etc.)"
	[id]
	(let [lst (find-item-category id)]
		((keyword id) lst)))

;(into [] map()) approach perhaps isn't the best for this but it works for now.
;TODO: use regexp for name matching
(defn search-ingredients-by-name [searchtext]
	"Search ingredients by name"
	(vec ; return vector
		(filter identity ; filter out nil entries
		(into [] (map (fn [m] ; go thru the whole collection
			(if (= (:name m) searchtext) (:id m))) ; add current id to results if name matches
			db/ingredientlist))))) ; collection to use for searching
