object SinglyRecursiveSimpleSequentialValidator extends BSTValidator {
  /**
    @param t binary tree
    @param ordering
    @return whether t is a valid binary search tree according to ordering

    Go down subtrees all the way, sequentially.

    Remove one level of recursion.

    @warn Can result in stack overflow.
   */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean =
    isValidLoop(t)

  @annotation.tailrec
  private def isValidLoop[A](t: Tree[A])
       (implicit ordering: Ordering[A]): Boolean = t match {
    case NilTree => true
    case Node(left, v, right) =>
      TreeUtils.treeLess(left, v) &&
      TreeUtils.lessTree(v, right) &&
      isValid(left) &&
      isValidLoop(right)
  }
}
