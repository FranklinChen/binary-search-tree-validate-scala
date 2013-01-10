object ParallelValidator extends BSTValidator {
  val cutoffDepth = 2

  /**
    Go down subtrees in parallel.

    If a failure is found in the left subtree, work on the right
    should be abandoned immediately.
   */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean = isValidAtDepth(t, 0)

  def isValidAtDepth[A](t: Tree[A], depth: Int)
                       (implicit ordering: Ordering[A]): Boolean = t match {
    case NilTree => true
    case Node(left, v, right) =>
      if (depth > cutoffDepth) {
        NaiveValidator.isValid(t)
      }
      else {
        BST.treeLess(left, v) &&
        BST.lessTree(v, right) &&
        List(left, right).par.forall(isValidAtDepth(_, depth+1))
      }
  }
}
