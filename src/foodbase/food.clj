(ns foodbase.food
	(:use 	[foodbase.db :as db]
			[foodbase.date :as date]
			[foodbase.uuid :as uuid]))

(defn get-food-ingredients
	"Return ingredients for specified food"
	[foodid]
	(:ingredients ((keyword foodid) db/foodlist)))

(defn get-food-nutritions
	"Return nutrition values for specified food"
	[foodid]
	(:nutritions ((keyword foodid) db/foodlist)))

(defn find-item-category
	"Find correct input data for given id"
	;TODO: data handling should be better. Currently this returns the whole data sets
	[id]
	(case (first id)
		\f db/foodlist
		\i db/ingredientlist))

(defn get-tags
	"Get all tags for specified item"
	[id]
	(let [lst (find-item-category id)]
		(:tags ((keyword id) lst))))

(defn is-tagged-with?
	"Check if item is tagged with specified tag"
	[id tag]
	(let [lst (find-item-category id)]
		(tag (get-tags id))))

(defn food-contains-ingredient?
	"Check if food contains ingredient. Returns vector [n desc] if found, otherwise nil"
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
