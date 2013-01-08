object NaiveValidator extends BSTValidator {
  /**
    Go down subtrees all the way, sequentially.
   */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean = t match {
    case NilTree => true
    case Node(left, v, right) =>
      BST.treeLess(left, v) &&
      BST.lessTree(v, right) &&
      isValid(left) &&
      isValid(right)
  }
}
