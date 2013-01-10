object SimpleSequentialValidator extends BSTValidator {
  /**
    @param t binary tree
    @param ordering
    @return whether t is a valid binary search tree according to ordering

    Go down subtrees all the way, sequentially.

    All recursion removed, to avoid stack overflow. Use a stack of nodes.
  */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean = {
    @inline
    @annotation.tailrec
    def loop(t: Tree[A],
             stack: List[Node[A]]): Boolean = t match {
      case NilTree => stack match {
        case Nil => true
        case Node(left, v, right)::rest =>
          TreeUtils.treeLess(left, v) &&
          TreeUtils.lessTree(v, right) &&
          loop(right, rest)
      }
      case node@Node(left, _, _) => loop(left, node::stack)
    }
    
    loop(t, Nil)
  }
}
