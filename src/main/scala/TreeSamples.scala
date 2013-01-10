/**
  To generate test trees.
 */
object TreeSamples {
  /**
   @param n number of values
   @return a balanced binary search tree of 1 to n

   Doubly recursive.
  */
  def validBalancedTree(n: Int): Tree[Int] =
    validBalancedTreeByRange(1, n)

  private def validBalancedTreeByRange(i: Int, j: Int): Tree[Int] = {
    if (i > j) {
      NilTree
    }
    else {
      val v = i + (j-i+1)/2
      Node(validBalancedTreeByRange(i, v-1),
	   v,
	   validBalancedTreeByRange(v+1, j))
    }
  }

  /**
   @param n number of values
   @return an invalid balanced tree of 1 to n-1, and 0 at bottom right
  */
  def invalidBalancedTree(n: Int): Tree[Int] = {
    require(n > 1)

    def invalidBalancedTreeByRange(i: Int): Tree[Int] = {
      if (i > n) {
	NilTree
      }
      else {
	val v = i + (n-i+1)/2
	Node(validBalancedTreeByRange(i, v-1),
	     if (v == n) 0 else v,
	     invalidBalancedTreeByRange(v+1))
      }
    }

    invalidBalancedTreeByRange(1)
  }

  /**
   @param n number of values
   @return a left skewed binary search tree of 1 to n
  */
  def validLeftSkewedTree(n: Int): Tree[Int] = {
    @annotation.tailrec
    def loop(i: Int, acc: Tree[Int]): Tree[Int] = {
      if (i > n) {
        acc
      }
      else {
        loop(i+1, Node(acc, i, NilTree))
      }
    }

    loop(1, NilTree)
  }

  /**
   @param n number of values
   @return an invalid left skewed tree of 1 to n-1, and 0 at bottom right
  */
  def invalidLeftSkewedTree(n: Int): Tree[Int] = {
    require(n > 1)

    Node(validLeftSkewedTree(n-1),
         0,
         NilTree)
  }

  /**
   @param n number of values
   @return a right skewed binary search tree of 1 to n
  */
  def validRightSkewedTree(n: Int): Tree[Int] = {
    @annotation.tailrec
    def loop(i: Int, acc: Tree[Int]): Tree[Int] = {
      if (i > n) {
        acc
      }
      else {
        loop(i+1, Node(NilTree, n-i+1, acc))
      }
    }

    loop(1, NilTree)
  }

  /**
   @param n number of values
   @return an invalid right skewed tree of 1 to n-1, and 0 at bottom right
  */
  def invalidRightSkewedTree(n: Int): Tree[Int] = {
    require(n > 1)

    @annotation.tailrec
    def loop(i: Int, acc: Tree[Int]): Tree[Int] = {
      if (i > n) {
        acc
      }
      else {
	val v = if (i == 1) 0 else n-i+1
        loop(i+1, Node(NilTree, v, acc))
      }
    }

    loop(1, NilTree)
  }
}
