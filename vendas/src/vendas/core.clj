(ns vendas.core
  (:require [vendas.db :as v.db]))

(println (v.db/todos-os-pedidos))

(println (group-by :usuario(v.db/todos-os-pedidos)))

(println (group-by :usuario (v.db/todos-os-pedidos)))

(defn minha-funcao-de-agrupamento
  [elemento]
  ;;(println "elemento" elemento)
  (:usuario elemento))

(println (group-by minha-funcao-de-agrupamento (v.db/todos-os-pedidos)))

(defn conta-total-por-usuario
  [[usuario pedidos]]
  {:usuario-id usuario
   :total-de-pedidos (count pedidos)})

(->> (v.db/todos-os-pedidos)
     (group-by :usuario)
     (map conta-total-por-usuario)
     println)

(println "PEDIDOS")

(defn total-do-item
  [[_ detalhes]]
  (* (get detalhes :quantidade 0) (get detalhes :preco-unitario 0)))

(defn total-do-pedido
  [pedido]
  (->> pedido
       (map total-do-item)
       (reduce +)))

(defn total-dos-pedidos
  [pedidos]
  (->> pedidos
       (map :itens)
       (map total-do-pedido)
       (reduce +)))

(defn quantia-de-pedidos-e-gasto-total-por-usuario
  [[usuario pedidos]]
  {:usuario-id       usuario
   :total-de-pedidos (count pedidos)
   :preco-total      (total-dos-pedidos pedidos)})


(->> (v.db/todos-os-pedidos)
     (group-by :usuario)
     (map quantia-de-pedidos-e-gasto-total-por-usuario)
     println)


