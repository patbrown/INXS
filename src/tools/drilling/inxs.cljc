(ns tools.drilling.inxs
  (:require [exoscale.interceptor :as ix]
            [tools.drilling.inxs.ixfn :as ixfn]))

(def ixfn ixfn/ixfn)

(def ^:dynamic *all-ns* [])

(defn get-meta []
  (->> #?(:clj (all-ns) :cljs *all-ns*) (mapcat ns-interns) (keep #(-> % second meta))))

(defn ixfns
  "Get the ixfns available."
  []
  (->> (get-meta) (filter :ixfn?)))

(defn ls-ixfn []
          (mapv :ixfn/id (ixfns)))

(defn hydrate-ixfn
          "Hydrate an ixfn from its id."
          [id]
          (let [ixfn (filter #(= id (:ixfn/id %)) (ixfns))]
            (if (empty? ixfn)
              (throw (Exception. (str "No ixfn with id: " id)))
              (-> ixfn first :ixfn/var))))

(defn hydrate-ixchain
  "Hydrate an interceptor chain from a vector of ixfn ids."
  [chain]
  (mapv #(if (keyword? %)
           (hydrate-ixfn %)
           %) chain))

(defn exec
  ([ctx chain]
   (ix/execute ctx (hydrate-ixchain chain)))
  ([return ctx chain]
   (let [result (exec ctx chain)]
     (cond 
       (keyword? return) (return result)
       (set? return) (select-keys result return)
       (vector? return) (select-keys result return)
       :else result))))
