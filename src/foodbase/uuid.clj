(ns foodbase.uuid)

(defn generate-raw-uuid
	"Return new randomUUID"
	[]
	(str (java.util.UUID/randomUUID)))

(defn new-uuid-by-category
	"Generate new uuid based on type (food, ingredient, jne.)"
	[category]
	(let [x (case category
			:food "f-"
			:ingredient "i-")]
		(str x (generate-raw-uuid))))