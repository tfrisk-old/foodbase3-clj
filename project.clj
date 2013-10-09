(defproject foodbase "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
  	[org.clojure/clojure "1.5.1"]
  	[clj-time "0.4.4"]
  	[compojure "1.1.5"]
  	[hiccup "1.0.4"]
    [com.taoensso/carmine "2.2.3"]]
  :plugins [[lein-ring "0.8.3"]]
  :ring {:handler foodbase.routes/app :auto-reload? true}
  :main foodbase.food)
