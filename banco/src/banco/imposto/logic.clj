(ns banco.imposto.logic)

;;Criar função privada
(defn- esta-na-faixa-de-isencao?
  [valor]
  (< valor 1000))

(defn imposto-retido-fonte
  [valor]
  (if (esta-na-faixa-de-isencao? valor)
    0
    (* valor 0.10)
  ))
