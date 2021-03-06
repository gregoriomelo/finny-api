(defproject finny-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.2"]
                 [ring/ring-defaults "0.2.3"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-jetty-adapter "1.5.1"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [cheshire "5.7.0"]
                 [postgresql "9.3-1102.jdbc41"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.25"]
                 [honeysql "0.8.2"]
                 [migratus "0.8.33"]
                 [clj-time "0.13.0"]
                 [clj-http "3.4.1"]
                 [log4j/log4j "1.2.17" :exclusions  [javax.mail/mail
                                                     javax.jms/jms
                                                     com.sun.jmdk/jmxtools
                                                     com.sun.jmx/jmxri]]]
  :plugins [[lein-ring "0.11.0"]
            [lein-midje "3.2.1"]
            [migratus-lein "0.4.4"]]
  :migratus {:store :database
             :migration-dir "migrations"
             :db (or (System/getenv "DATABASE_URL") "postgresql://localhost:5432/finny")}
  :ring {:handler finny-api.core.handler/app}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.0"]
                                  [midje "1.8.3" :exclusions [org.clojure/clojure]]] }}
  :test-paths ["test/unit-tests" "test/integration-tests" "test/acceptance-tests"]
  :aliases {"acceptance-tests"  ["midje" ":filters" "at"]
            "integration-tests" ["midje" ":filters" "it"]
            "unit-tests"        ["midje" ":config" "unit-tests.config"]
            "test"              ["midje"]
            "tests"             ["midje"]})
