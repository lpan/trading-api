(ns trading-api.schema
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [com.walmartlabs.lacinia.util :refer [attach-resolvers]]
            [com.walmartlabs.lacinia.schema :as schema]
            [trading-api.db :as db]))

(def user-schema
  (-> (io/resource "schemas/user.edn")
      slurp
      edn/read-string
      (attach-resolvers {:get-user db/get-user
                         :get-accounts db/get-accounts})
      schema/compile))
