object SinglyRecursiveExitSequentialValidator extends BSTValidator {
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

    Remove some tail recursion.
   */
  private def checkValidOrThrow[A](t: Tree[A])
                                  (implicit ordering: Ordering[A]) {
    @annotation.tailrec
    def loop(t: Tree[A]) {
      t match {
        case NilTree => ()
        case Node(left, v, right) => {
          if (!(TreeUtils.treeLess(left, v) && TreeUtils.lessTree(v, right))) {
            throw InvalidBSTException
          }

          // Cannot remove this recursion.
          checkValidOrThrow(left)
          loop(right)
        }
      }
    }

    loop(t)
  }
}
