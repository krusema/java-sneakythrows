# Sneaky Throws Wrapper that works at runtime

This is a little code snippet that converts a `Callable` which may throw a checked Exception into a `Supplier`,
which officially should not be able to throw that Exception.  
This is possible because in Java, checked exceptions are checked only at compile-time, while the JVM accepts any `Throwable` to be thrown
by any method at any time.

This snippet gets around the compile-rule by instantiating its own ClassLoader, that then instantiates a helper class from fumbled bytecode.  

## How to run
```bash
javac src/Main.java src/SneakyThrows.java -d out
# Run
java -cp out Main
```

## How to reproduce modified bytecode
1. Compile SneakyThrowsImpl.java
   1. `javac src/SneakyThrowsImpl.java -d modified`
2. Open modified/SneakyThrowsImpl.class in a bytecode editor such as reJ
3. Find the method `get` and replace the last `areturn` with an `athrow`
4. Save the file, overwriting it
5. Encode the file as base64: `cat modified/SneakyThrowsImpl.class | base64 -w0; echo`
6. Copy-paste the resulting base64 into SneakyThrows.java, replacing the content of the `asB64` field
7. Test if everything works as expected by following the instructions in **How to run** above


## Why
This is mostly a fun experiment, but things like this can come in useful from time to time,
especially when developing advanced libraries/tooling.