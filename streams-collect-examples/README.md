# Mutable Reduction
The standard terminal operations for doing _mutable_ reduction are the `collect()` and `toArray()` methods.
The **Supplier** starts the stream off, by creating a mutable container object that is then operated on by the various operation stages.
In `collect()` operations, the elements that are created by the various stages, 
are incorporated into the final state by updating the state of a mutable container object.

## Stream#collect() methods
```java
<R> R collect(Supplier<R> supplier,
BiConsumer<R,? super T> accumulator,
BiConsumer<R,R> combiner)
```
This method reduces stream element of type T to a mutable result container of type R.

**supplier**: this function creates a a new result container. In sequential execution it's called only once, whereas, for parallel execution, it may be called multiple times to get a new instance for different parallel threads.

**accumulator**: an associative function to incorporate the current element to the result object (the result object is created in supplier function)

**combiner**: in parallel execution this function combines the results received from different threads. This must be associative function.
