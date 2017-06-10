(ns trading-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.middleware.json :refer [wrap-json-body]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [com.walmartlabs.lacinia :refer [execute]]
            [trading-api.schema :refer [user-schema]]))

(defn graphql-handler
  "execute query extracted from the request body"
  [{body :body}]
  (if-let [query (get body "payload")]
    (let [result (execute user-schema query nil nil)]
      (json/write-str result))
    "Invalid arguments"))

(defroutes app-routes
  (GET "/" [] "Post JSON to /graphql plez")
  (POST "/graphql" request (graphql-handler request))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults api-defaults)
      wrap-json-body))
