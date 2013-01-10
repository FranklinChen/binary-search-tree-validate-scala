object ExitSequentialValidator extends BSTValidator {
  /**
    For throwing when checking validity.
   */
  final object InvalidBSTException extends Exception

  /**
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
   */
  private def checkValidOrThrow[A](t: Tree[A])
                                  (implicit ordering: Ordering[A]) {
    t match {
      case NilTree => ()
      case Node(left, v, right) => {
        if (TreeUtils.treeLess(left, v) && TreeUtils.lessTree(v, right)) {
          checkValidOrThrow(left)
          checkValidOrThrow(right)
        }
        else {
          throw InvalidBSTException
        }
      }
    }
  }
}
