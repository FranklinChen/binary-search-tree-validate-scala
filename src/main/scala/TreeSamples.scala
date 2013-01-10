object TreeSamples {
  /**
   @param n number of values
   @return a balanced binary search tree of 1 to n
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
    if (n <= 0) {
      NilTree
    }
    else {
      Node(validLeftSkewedTree(n-1),
           n,
           NilTree)
    }
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
}
