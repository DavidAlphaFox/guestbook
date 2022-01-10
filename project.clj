(defproject guestbook "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[com.h2database/h2 "1.4.192"]
                 [compojure "1.6.2"]
                 [conman "0.6.2"]
                 [cprop "0.1.9"]
                 [funcool/struct "1.0.0"]
                 [luminus-immutant "0.2.5"]
                 [luminus-migrations "0.7.1"]
                 [luminus-nrepl "0.1.4"]
                 [markdown-clj "0.9.90"]
                 [metosin/ring-http-response "0.8.0"]
                 [mount "0.1.16"]
                 [org.clojure/clojure "1.10.3"]
                 [org.clojure/tools.cli "1.0.206"]
                 [org.clojure/tools.logging "1.2.4"]
                 [org.webjars.bower/tether "1.3.7"]
                 [org.webjars/bootstrap "4.0.0-alpha.3"]
                 [org.webjars/font-awesome "4.6.3"]
                 [org.webjars/jquery "3.1.1"]
                 [org.webjars/webjars-locator-jboss-vfs "0.1.0"]
                 [ring-middleware-format "0.7.4"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-defaults "0.3.3"]
                 [selmer "1.12.49"]]

  :min-lein-version "2.0.0"

  :source-paths ["src/clj"]
  :resource-paths ["resources"]
  :target-path "target/%s/"
  :main guestbook.core
  :migratus {:store :database :db ~(get (System/getenv) "DATABASE_URL")}

  :plugins [[migratus-lein "0.7.3"]
            [lein-immutant "2.1.0"]]

  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "guestbook.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-server" "-Dconf=dev-config.edn"]
                  :dependencies [[prone "1.1.2"]
                                 [ring/ring-mock "0.3.0"]
                                 [ring/ring-devel "1.5.0"]
                                 [pjstadig/humane-test-output "0.8.1"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.14.0"]]

                  :source-paths ["env/dev/clj" "test/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-server" "-Dconf=test-config.edn"]
                  :resource-paths ["env/dev/resources" "env/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})
