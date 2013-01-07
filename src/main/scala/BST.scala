object BST {
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean = t match {
    case NilTree => true
    case Node(left, v, right) =>
      treeLess(left, v) &&
      lessTree(v, right) &&
      isValid(left) &&
      isValid(right)
  }

  def treeLess[A](t: Tree[A], v1: A)
                 (implicit ordering: Ordering[A]) = t match {
    case NilTree => true
    case Node(_, v0, _) => ordering.lt(v0, v1)
  }

  def lessTree[A](v0: A, t: Tree[A])
                 (implicit ordering: Ordering[A]) = t match {
    case NilTree => true
    case Node(_, v1, _) => ordering.lt(v0, v1)
  }
}
