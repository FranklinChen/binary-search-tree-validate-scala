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
                (implicit ordering: Ordering[A]): Boolean = {
    @inline
    @annotation.tailrec
    def loop(t: Tree[A]): Boolean = t match {
      case NilTree => true
      case Node(left, v, right) =>
        TreeUtils.treeLess(left, v) &&
        TreeUtils.lessTree(v, right) &&
        isValid(left) &&
        loop(right)
    }
    
    loop(t)
  }
}
