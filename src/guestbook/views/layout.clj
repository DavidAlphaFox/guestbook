(ns guestbook.views.layout
  (:require [selmer.parser :as parser]
            [ring.util.response :refer [content-type response]])
  (:import compojure.response.Renderable))

(def template-path "guestbook/views/templates/")

(deftype RenderableTemplate [template params]
  Renderable
  (render [this request]
    (content-type
      (->> (assoc params :servlet-context (:context request))
        (parser/render-file (str template-path template))
        response)
      "text/html; charset=utf-8")))

(defn render [template & [params]]
  (RenderableTemplate. template params))
