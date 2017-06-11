(ns trading-api.db)

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defn mock-account
  []
  {:id (uuid)
   :name "My account"
   :default-currency "USD"
   :assets []
   :orders []})

(defn mock-user
  []
  {:id (uuid)
   :name "lpan"
   :accounts [(mock-account)]})

(defn get-user
  [ctx args value]
  (mock-user))

(defn get-account
  [ctx args value]
  mock-account)

(defn get-accounts
  [ctx args value]
  (:accounts value))

(defn get-assets
  [ctx args value]
  (:assets value))

(defn get-orders
  [ctx args value]
  (:orders value))
