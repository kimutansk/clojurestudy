;; Clojure 1.5.1
IOFactory
reader.crypto.CryptoFilter
=> (def vault (->CryptoVault "vault-file" "keystore" "toomanysecrets"))
CompilerException java.lang.RuntimeException: Unable to resolve symbol: ->CryptoVault in this context, compiling:(NO_SOURCE_PATH:1:12) 
=> (def vault (->CryptoFilter "vault-file" "keystore" "toomanysecrets"))
CompilerException java.lang.RuntimeException: Unable to resolve symbol: ->CryptoFilter in this context, compiling:(NO_SOURCE_PATH:1:12) 
=> (def vault (->reader.crypto.CryptoFilter "vault-file" "keystore" "toomanysecrets"))
CompilerException java.lang.ClassNotFoundException: ->reader.crypto.CryptoFilter, compiling:(NO_SOURCE_PATH:1:12) 
=> (def vault (CryptoFilter. "vault-file" "keystore" "toomanysecrets"))
CompilerException java.lang.IllegalArgumentException: Unable to resolve classname: CryptoFilter, compiling:(NO_SOURCE_PATH:1:12) 
=> (use 'reader.crypto.CryptoFilter)
FileNotFoundException Could not locate reader/crypto/CryptoFilter__init.class or reader/crypto/CryptoFilter.clj on classpath:   clojure.lang.RT.load (RT.java:443)
=> (use 'reader.crypto)
nil
=> (def vault (CryptoFilter. "vault-file" "keystore" "toomanysecrets"))
CompilerException java.lang.IllegalArgumentException: Unable to resolve classname: CryptoFilter, compiling:(NO_SOURCE_PATH:1:12) 
=> (find Crypto*)
CompilerException java.lang.RuntimeException: Unable to resolve symbol: Crypto* in this context, compiling:(NO_SOURCE_PATH:1:1) 
=> (find CryptoFilter)
CompilerException java.lang.RuntimeException: Unable to resolve symbol: CryptoFilter in this context, compiling:(NO_SOURCE_PATH:1:1) 
=> (in-ns 'reader.crypto)
#<Namespace reader.crypto>
=> (def vault (CryptoFilter. "vault-file" "keystore" "toomanysecrets"))
#'reader.crypto/vault
=> (init-filter vault)
"reader.crypto.CryptoFilter@543ef658"
=> (init-filter vault "Test")
CompilerException java.lang.IllegalArgumentException: No single method: init_filter of interface: reader.crypto.Filter found for function: init-filter of protocol: Filter, compiling:(NO_SOURCE_PATH:1:1) 
=> (init-filter "Test")
IllegalArgumentException No implementation of method: :init-filter of protocol: #'reader.crypto/Filter found for class: java.lang.String  clojure.core/-cache-protocol-fn (core_deftype.clj:541)
=> (str vault)
"reader.crypto.CryptoFilter@543ef658"
=> (.makeReader vault)
IllegalArgumentException No implementation of method: :makeReader of protocol: #'reader.io/IOFactory found for class: java.lang.String  clojure.core/-cache-protocol-fn (core_deftype.clj:541)
=> (load-file "reader/crypto.clj")
FileNotFoundException reader\crypto.clj (指定されたパスが見つかりません。)  java.io.FileInputStream.open (FileInputStream.java:-2)
=> (load-file "src/reader/crypto.clj")
reader.crypto.CryptoFilter
=> (in-ns 'user)
#<Namespace user>
=> (def vault (reader.crypto/CryptoFilter. "vault-file" "keystore" "toomanysecrets"))
CompilerException java.lang.IllegalArgumentException: Unable to resolve classname: CryptoFilter, compiling:(NO_SOURCE_PATH:1:12) 
=> (def vault (reader.crypto/CryptoFilter "vault-file" "keystore" "toomanysecrets"))
CompilerException java.lang.RuntimeException: No such var: reader.crypto/CryptoFilter, compiling:(NO_SOURCE_PATH:1:12) 
=> (use reader.crypto/CryptoFilter as CryptoFilter)
CompilerException java.lang.RuntimeException: No such var: reader.crypto/CryptoFilter, compiling:(NO_SOURCE_PATH:1:1) 
