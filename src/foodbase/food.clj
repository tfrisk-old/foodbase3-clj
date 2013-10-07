(ns foodbase.food
	(:use 	[foodbase.db :as db]
			[foodbase.date :as date]
			[foodbase.uuid :as uuid]))

;(into [] map()) approach perhaps isn't the best for this but it works for now.
;TODO: use regexp for name matching
(defn search-vector-of-maps
	"Search vector of maps, searchterm is [:key value] pair"
	[inputvector [skey sval]]
	(vec ; return vector
		(filter identity ; filter out nil entries
		(into [] (map (fn [entry] ; go thru the whole collection
			(if (= ((keyword skey) entry) sval) (:id entry))) ; add current id to results if name matches
			inputvector))))) ; collection to use for searching

;TODO: this has duplicate code with search-vector-of-maps
(defn filter-vector-of-maps
	"Filter entries from vector of maps, search by id"
	[inputvector searchid]
	(first ; return first result assuming there will always be only one result for a given id
		(filter identity ; filter out nil entries
		(into [] (map (fn [m] ; go thru the whole collection
			(if (= (:id m) searchid) m))
			inputvector))))) ; collection to use for searching

(defn get-food-data [searchid]
	"Get food data"
	(filter-vector-of-maps db/foodlist searchid))

(defn get-ingredient-data [searchid]
	"Get ingredient data"
	(filter-vector-of-maps db/ingredientlist searchid))

(defn get-food-details
	"Get specific details for specified food"
	[foodid detail]
	((keyword detail) (get-food-data foodid)))

(defn get-ingredient-details
	"Get specific details for specified ingredient"
	[ingredientid detail]
	((keyword detail) (get-ingredient-data ingredientid)))

(defn get-food-ingredients
	"Return ingredients for specified food"
	[foodid]
	(get-food-details foodid :ingredients))

(defn get-food-nutritions
	"Return nutrition values for specified food"
	[foodid]
	(get-food-details foodid :nutritions))

(defn food-contains-ingredient?
	"Check if food contains ingredient. Returns true if found, otherwise false"
	[foodid ingredientid]
	(if (empty? (search-vector-of-maps (get-food-ingredients foodid) [:id ingredientid]))
		false true))

(defn search-foods-by-name [searchtext]
	"Search foods by name"
	(search-vector-of-maps db/foodlist [:name searchtext]))

(defn search-ingredients-by-name [searchtext]
	"Search ingredients by name"
	(search-vector-of-maps db/ingredientlist [:name searchtext]))
