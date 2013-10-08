(ns foodbase.food
	(:use 	[foodbase.db :as db]
			[foodbase.date :as date]
			[foodbase.uuid :as uuid]))

;(into [] map()) approach perhaps isn't the best for this but it works for now.
;TODO: use regexp for name matching
;returns only matching id
(defn search-vector-of-maps
	"Search vector of maps, searchterm is [:key value] pair"
	[inputvector [skey sval]]
	(vec ; return vector
		(filter identity ; filter out nil entries
		(into [] (map (fn [entry] ; go thru the whole collection
			(if (= ((keyword skey) entry) sval) (:id entry))) ; add current id to results if name matches
			inputvector))))) ; collection to use for searching

;TODO: this has duplicate code with search-vector-of-maps
;returns the whole matching map
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

(defn get-manufacturer-data [searchid]
	"Get manufacturer data"
	(filter-vector-of-maps db/manufacturerlist searchid))

(defn get-food-details
	"Get specific details for specified food"
	[foodid detail]
	((keyword detail) (get-food-data foodid)))

(defn get-ingredient-details
	"Get specific details for specified ingredient"
	[ingredientid detail]
	((keyword detail) (get-ingredient-data ingredientid)))

(defn get-manufacturer-details
	"Get specific details for specified manufacturer"
	[manufacturerid detail]
	((keyword detail) (get-manufacturer-data manufacturerid)))

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

(defn search-foods-by-manufacturer-id [searchid]
	"Search foods by manufacturer id"
	(let [foodids (search-vector-of-maps db/foodlist [:manufacturer searchid])]
		(map
			(fn [id] (hash-map :id (str id) :name (str (get-food-details id :name))))
			(seq foodids))
	))

(defn search-foods-by-name [searchtext]
	"Search foods by name"
	(search-vector-of-maps db/foodlist [:name searchtext]))

(defn search-ingredients-by-name [searchtext]
	"Search ingredients by name"
	(search-vector-of-maps db/ingredientlist [:name searchtext]))

(defn get-food-list
	"Get list of all foods"
	[]
	(seq db/foodlist))

(defn get-ingredient-list
	"Get list of all ingredients"
	[]
	(seq db/ingredientlist))

(defn get-manufacturer-list
	"Get list of all manufacturers"
	[]
	(seq db/manufacturerlist))

(defn search-foods-by-ingredient-id [searchid]
	"Search foods by ingredient id. Returns lazy sequence of ids"
	(filter identity (map
		(fn [foodentry]
			(let [foodid (:id foodentry) inglist (get-food-ingredients foodid)]
				(if (empty? (filter-vector-of-maps inglist searchid))
					nil
					(hash-map 
						:id foodid
						:name (get-food-details foodid :name)))))
		(seq (get-food-list))
	)))
