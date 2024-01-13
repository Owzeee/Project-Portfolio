(ns menu
  (:require [clojure.string :as str])
  (:require [clojure.java.io :as io])
  (:require [compress :as comp]))

(defn showMenu
  [] 
  (println "\n\n*** Compression Menu ***")
  (println "------------------\n")
  (println "1. Display list of files")
  (println "2. Display file contents")
  (println "3. Compress a file")
  (println "4. Uncompress a file")
  (println "5. Exit")
  (do
    (print "\nEnter an option? ")
    (flush)
    (read-line)))


(defn option1 []
  (println "Files in the current directory:\n")
  (doseq [f (file-seq (io/file "."))]
    (when (.isFile f)
      (println (.getName f)))))

(defn option2
  []
  (print "\nPlease enter a file name => ")
  (flush)
  (let [file-name (read-line)
        file (io/file file-name)]
    (if (.exists file)
      (println "\n" (slurp file))
      (println "File does not exist"))))

(defn option3 []
  (print "\nPlease enter a file name to compress => ")
  (flush)
  (let [file-name (read-line)
        file (io/file file-name)]
    (if (.exists file)
      (let [file-content (slurp file)
            compressed-content (comp/compressText file-content)]
        (spit (str file-name ".ct") compressed-content)
        (println "File compressed successfully: " (str file-name ".ct")))
      (println "Oops: specified file does not exist"))))

(defn option4 []
  (print "\nPlease enter a file name to decompress => ")
  (flush)
  (let [file-name (read-line)
        file (io/file file-name)]
    (if (.exists file)
      (let [compressed-content (slurp file)
            decompressed-content (comp/decompressText compressed-content)]
        (println "Decompressed content:\n" decompressed-content))
      (println "File does not exist"))))

(defn processOption
  [option]
  (if (= option "1")
    (option1)
    (if (= option "2")
      (option2)
      (if (= option "3")
        (option3)
        (if (= option "4")
          (option4)
          (println "Invalid Option, please try again"))))))

(defn menu
  [] 
  (let [option (str/trim (showMenu))]
    (if (= option "5")
      (println "\nGood Bye\n")
      (do
        (processOption option)
        (recur)))))


(menu)