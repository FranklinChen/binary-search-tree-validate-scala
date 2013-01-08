object ParallelValidator extends BSTValidator {
  /**
    Go down subtrees in parallel.

    If a failure is found in the left subtree, work on the right
    should be abandoned immediately.
   */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean = t match {
    case NilTree => true
    case Node(left, v, right) =>
      BST.treeLess(left, v) &&
      BST.lessTree(v, right) &&
      List(left, right).par.forall(isValid)
  }
}
