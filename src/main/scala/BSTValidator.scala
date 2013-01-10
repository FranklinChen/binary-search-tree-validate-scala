trait BSTValidator {
  /**
    @param t binary tree
    @param ordering
    @return whether t is a valid binary search tree according to ordering
   */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean
}
