(ns foodbase.routes
	(:use
		compojure.core
		[foodbase.web :as web]
		[hiccup.middleware :only (wrap-base-url)])
	(:require
		[compojure.route :as route]
		[compojure.handler :as handler]
		[compojure.response :as response]))

(defroutes app-routes
	(GET "/" [] (web/index-page))
	(GET "/details/:id" [id] (web/detail-page id))
	(GET "/search" [] (web/search-page))
	(route/resources "/")
	(route/not-found "Page not found"))

(def app
	(-> (handler/site app-routes)
		(wrap-base-url)))
