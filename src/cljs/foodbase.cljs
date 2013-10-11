(ns foodbase.search
	(:require
		[domina :as d]
		[clojure.browser.event :as event]))

(def search-button (d/by-id "search-button"))

(event/listen
	search-button
	"click"
	(fn [] (js/alert (d/value (d/by-id "searchtext")))))
