object SimpleSequentialValidator extends BSTValidator {
  /**
    Go down subtrees all the way, sequentially.

    Watch out for stack overflow.
   */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean = t match {
    case NilTree => true
    case Node(left, v, right) =>
      TreeUtils.treeLess(left, v) &&
      TreeUtils.lessTree(v, right) &&
      isValid(left) &&
      isValid(right)
  }
}
