(ns hospital.core
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))

(let [hospital-do-joao (h.model/novo-hospital)]
  (pprint hospital-do-joao))
