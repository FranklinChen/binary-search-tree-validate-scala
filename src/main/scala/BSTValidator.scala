trait BSTValidator {
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean
}
