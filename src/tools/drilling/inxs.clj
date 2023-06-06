(ns tools.drilling.inxs
  (:require [exoscale.interceptor :as ix]))

(defmacro ixfn [sym body]
  (let [ixfn-sym# (symbol (str "ixfn-" sym))
        sym-as-kw# (keyword (str sym))
        b# (if (map? body)
             body
             {:enter body})]
    `(do
       (def ~sym (if (map? ~body)
                   (:enter ~body)
                   ~body))
       (def ~(with-meta ixfn-sym# {:ixfn? true
                                   :ixfn/var `(var ~ixfn-sym#)
                                   :ixfn/id sym-as-kw#})
         ~(merge {:name sym-as-kw#}
                 b#)))))

(defn get-meta []
      (->> (all-ns) (mapcat ns-interns) (keep #(-> % second meta))))

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
