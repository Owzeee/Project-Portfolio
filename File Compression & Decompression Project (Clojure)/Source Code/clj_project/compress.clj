(ns compress
  (:require [clojure.string :as str])
  (:require [clojure.java.io :as io]))

(defn initializeFreqMap []
  (let [content (slurp "frequency.txt")
        lines (str/split content #"\n")
        words-phrases (mapcat #(str/split % #"\s+") lines)
        unique-words (distinct words-phrases)]
    (into {} (map-indexed (fn [idx word]
                            [word idx]) unique-words))))

(def frequency-map (initializeFreqMap))

(defn isValidWord [s]
  (re-matches #"[a-zA-Z]+'?[a-zA-Z]*" s))

(defn compressText [text]
  (let [tokens (re-seq #"[a-zA-Z]+'?[a-zA-Z]*|\d+|[.,!?;:@\[\]{}()\-]" text)
        compressed (map (fn [token]
                          (cond
                            (re-matches #"\d+" token) (str "@" token "@")
                            (isValidWord token) (str (get frequency-map (str/lower-case token) token))
                            :else token))
                        tokens)]
    (str/join " " compressed)))

(defn initializeReverseFreqMap []
  (into {} (map (fn [[k v]] [v k]) frequency-map)))

(def reverse-frequency-map (initializeReverseFreqMap))

(defn capitalizeAsRequired [token prev-token idx]
  (if (or (zero? idx)
          (and prev-token (some #{\. \? \!} [(last prev-token)])))
    (str (str/upper-case (subs token 0 1)) (subs token 1))
    token))

(defn applyTextFormatting [tokens]
  (let [formatted-tokens (map-indexed
                          (fn [idx token]
                            (cond
                              (re-matches #"\[|\(" token) (if (> idx 0) (str " " token) token)
                              (re-matches #"\]|\)|,|\.|\?|!" token) token
                              (re-matches #"\-" token) (str " " token " ")
                              (re-matches #"\@|\$" token) token
                              :else (capitalizeAsRequired token (if (> idx 0) (nth tokens (dec idx)) nil) idx))) tokens)]
    (str/replace (str/replace (str/join " " formatted-tokens)
                              (re-pattern "\\s+([,.)\\]!?])") "$1")
                 (re-pattern "(\\(|\\[|\\@)\\s+") "$1")))

(defn decompressText [compressed-text]
  (let [tokens (str/split compressed-text #"\s+")
        decompressed (map (fn [token]
                            (if (re-matches #"\d+" token)
                              (get reverse-frequency-map (Integer/parseInt token) token)
                              (if (re-matches #"@(\d+)@" token)
                                (re-find #"\d+" token)
                                token)))
                          tokens)]
    (applyTextFormatting decompressed)))

