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

(defn get-food-data
	"Get food data"
	[searchid]
	(db/load-item-by-id searchid))

(defn get-ingredient-data
	"Get ingredient data"
	[searchid]
	(db/load-item-by-id searchid))

(defn get-manufacturer-data
	"Get manufacturer data"
	[searchid]
	(db/load-item-by-id searchid))

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

(defn search-ingredients-by-name
	"Search ingredients by name"
	[searchtext]
	(search-vector-of-maps (db/load-ingredient-list) [:name searchtext]))

(defn get-food-list
	"Get list of all foods"
	[]
	(seq (db/load-food-list)))

(defn get-ingredient-list
	"Get list of all ingredients"
	[]
	(seq (db/load-ingredient-list)))

(defn get-manufacturer-list
	"Get list of all manufacturers"
	[]
	(seq (db/load-manufacturer-list)))

(defn search-foods-by-ingredient-id
	"Search foods by ingredient id. Returns lazy sequence of ids"
	[searchid]
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

(defn search-foods-by-manufacturer-id
	"Search foods by manufacturer id"
	[searchid]
	(filter identity (map
		(fn [foodentry]
			(let [foodid (:id foodentry)]
				(if (= (:manufacturer (db/load-item-by-id foodid)) (str searchid))
					(hash-map :id foodid :name (get-food-details foodid :name))
					nil)))
			(seq (get-food-list))
	)))

(defn parse-search-entries [searchtext entry]
	(let [id (:id entry)]
		(if-not (nil?
			(re-matches
				(re-pattern (str ".*" (clojure.string/lower-case searchtext) ".*"))
				(clojure.string/lower-case (:name (db/load-item-by-id id)))))
			(hash-map :id id :name (get-food-details id :name)))))

(defn search-foods-by-name
	"Search foods by name, case insensitive."
	[searchtext]
	(filter identity
		(map #(parse-search-entries searchtext %)
			(seq (get-food-list)))))

(defn search-ingredients-by-name
	"Search ingredients by name, case insensitive."
	[searchtext]
	(filter identity
		(map #(parse-search-entries searchtext %)
			(seq (get-ingredient-list)))))

(defn search-manufacturers-by-name
	"Search manufacturers by name, case insensitive."
	[searchtext]
	(filter identity
		(map #(parse-search-entries searchtext %)
			(seq (get-manufacturer-list)))))

(defn save-new-manufacturer [mname]
	(println "save-new-manufacturer:" mname)
	(let [id (uuid/new-uuid-by-category :manufacturer)
		newentry (hash-map :name (str mname) :id id)]
			;add new entry to manufacturerlist
			(db/save-manufacturer-list
				(conj (get-manufacturer-list) newentry))
			;save entry by id
			(db/save-item-map newentry)
	))
