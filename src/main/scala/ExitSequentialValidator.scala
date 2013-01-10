object ExitSequentialValidator extends BSTValidator {
  /**
    For throwing when checking validity.
   */
  final object InvalidBSTException extends Exception

  /**
    @param t binary tree
    @param ordering
    @return whether t is a valid binary search tree according to ordering

    Go down subtrees all the way, sequentially, but escape with exception
    when violation found.
   */
  def isValid[A](t: Tree[A])
                (implicit ordering: Ordering[A]): Boolean = {
    try {
      checkValidOrThrow(t)
      true
    } catch {
      case InvalidBSTException => false
    }
  }

  /**
    Throw exception instead of returning false when tree is invalid.

    All recursion removed, to avoid stack overflow. Use a stack of nodes.
   */
  private def checkValidOrThrow[A](t: Tree[A])
                                  (implicit ordering: Ordering[A]) {
    checkValidOrThrowWithStack(t, Nil)
  }

  @annotation.tailrec
  private def checkValidOrThrowWithStack[A](t: Tree[A],
                                    stack: List[Node[A]])
        (implicit ordering: Ordering[A]) {
    t match {
      case NilTree => stack match {
        case Nil => ()
        case Node(left, v, right)::rest => {
          if (!(TreeUtils.treeLess(left, v) &&
                TreeUtils.lessTree(v, right))) {
            throw InvalidBSTException
          }
          checkValidOrThrowWithStack(right, rest)
        }
      }
      case node@Node(left, _, _) =>
	checkValidOrThrowWithStack(left, node::stack)
    }
  }
}
