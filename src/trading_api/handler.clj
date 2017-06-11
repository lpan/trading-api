(ns trading-api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.util.response :refer [response]]
            [com.walmartlabs.lacinia :refer [execute]]
            [trading-api.schema :refer [user-schema]]))

(defn graphql-handler
  "execute query extracted from the request body"
  [{body :body}]
  (if-let [query (get body "payload")]
    (response (execute user-schema query nil nil))
    "Invalid arguments"))

(defroutes app-routes
  (GET "/" [] "Post JSON to /graphql plez")
  (POST "/graphql" request (graphql-handler request))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      wrap-json-response
      wrap-json-body
      (wrap-defaults api-defaults))
