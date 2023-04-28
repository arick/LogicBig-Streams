# Immtable Reduction
The standard terminal operation for doing `immutable reduction` is the `reduce()` method.
There are several standard _helper_ reduction methods: `min()`, `max()`, `count()`, `sum()`, `average()`, `summaryStatistics()`.
In `reduce()` operations, each stage creates a new immutable value, that incorporates the previous values, and replaces the previous results object.
