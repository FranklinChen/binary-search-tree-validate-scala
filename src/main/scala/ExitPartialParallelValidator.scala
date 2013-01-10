object ExitPartialParallelValidator extends BSTValidator {
  // TODO This is arbitrary, and should be tuned.
  val cutoffDepth = 2

  /**
    @param t binary tree
    @param ordering
    @return whether t is a valid binary search tree according to ordering

    Go down subtrees in parallel.

    If a failure is found in the left subtree, work on the right
    should be abandoned immediately.
   */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean = isValidAtDepth(t, 0)

  private def isValidAtDepth[A](t: Tree[A], depth: Int)
                       (implicit ordering: Ordering[A]): Boolean = {
    try {
      checkValidOrThrow(t, depth)
      true
    } catch {
      case InvalidBSTException => false
    }
  }

  def checkValidOrThrow[A](t: Tree[A], depth: Int)
       (implicit ordering: Ordering[A]) {
    if (depth > cutoffDepth) {
      ExitSequentialValidator.checkValidOrThrow(t)
    }
    else {
      t match {
        case NilTree => ()
        case Node(left, v, right) => {
          if (!(TreeUtils.treeLess(left, v) &&
		TreeUtils.lessTree(v, right))) {
            throw InvalidBSTException
          }

          List(left, right).par.foreach(checkValidOrThrow(_, depth+1))
	}
      }
    }
  }
}
