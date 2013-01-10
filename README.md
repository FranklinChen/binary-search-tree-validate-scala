# Compare sequential and parallel algorithm in Scala

Checking whether a tree is a valid binary search tree: a [code puzzler from DZone](http://java.dzone.com/articles/thursday-code-puzzler-6).

Parallelism comes from simultaneously checking subtrees during recursion.

Benchmarking is done with [Google Caliper](http://code.google.com/p/caliper/) for Java.
