(ns tools.drilling.inxs.ixfn)

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


