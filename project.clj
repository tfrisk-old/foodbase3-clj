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
    [com.taoensso/carmine "2.2.3"]
    [org.clojure/clojurescript "0.0-1859" :exclusions [org.apache.ant/ant]]
    [domina "1.0.2"]
    [org.clojure/google-closure-library-third-party "0.0-2029"] ;domina requires this
    ]
  :plugins [
    [lein-ring "0.8.3"]
    [lein-cljsbuild "0.3.3"]]
  :ring {:handler foodbase.routes/app :auto-reload? true}
  :main foodbase.food
  :cljsbuild {
    :builds [{
      :source-paths ["src/cljs"]
      :compiler {
        :output-to "resources/public/js/main.js"
        :optimizations :whitespace
        :pretty-print true}}]
    })

;http://www.myclojureadventure.com/2012/09/intro-to-clojurescript-getting-started.html
;https://github.com/emezeske/lein-cljsbuild
