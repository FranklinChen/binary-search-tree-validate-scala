object TreeUtils {
  /**
   @return whether the value at tree t is less than v1
   */
  def treeLess[A](t: Tree[A], v1: A)
                 (implicit ordering: Ordering[A]) = t match {
    case NilTree => true
    case Node(_, v0, _) => ordering.lt(v0, v1)
  }

  /**
   @return whether v0 is less than the value at tree t
   */
  def lessTree[A](v0: A, t: Tree[A])
                 (implicit ordering: Ordering[A]) = t match {
    case NilTree => true
    case Node(_, v1, _) => ordering.lt(v0, v1)
  }

  /**
   @param v value
   @return a leaf
   */
  def leaf[A](v: A) = Node(NilTree, v, NilTree)
}
