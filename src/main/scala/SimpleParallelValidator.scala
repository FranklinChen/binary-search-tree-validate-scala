object SimpleParallelValidator extends BSTValidator {
  /**
    @param t binary tree
    @param ordering
    @return whether t is a valid binary search tree according to ordering

    Go down subtrees in parallel.

    If a failure is found in the left subtree, work on the right
    should be abandoned immediately.

    Horribly inefficient, because of all the threads spawned and
    unnecessary allocations of temporarily parallel collections, etc.

    @warn Can result in stack overflow.
   */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean = t match {
    case NilTree => true
    case Node(left, v, right) =>
      TreeUtils.treeLess(left, v) &&
      TreeUtils.lessTree(v, right) &&
      List(left, right).par.forall(isValid)
  }
}
