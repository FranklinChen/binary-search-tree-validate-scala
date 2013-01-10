object BST {
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

  /**
   @param n number of values
   @return a balanced binary search tree of 1 to n
  */
  def sampleTree(n: Int): Tree[Int] = sampleTreeByRange(1, n)

  def sampleTreeByRange(i: Int, j: Int): Tree[Int] = {
    if (i > j) {
      NilTree
    }
    else {
      val v = i + (j-i+1)/2
      Node(sampleTreeByRange(i, v-1),
	   v,
	   sampleTreeByRange(v+1, j))
    }
  }

  def sampleInvalidTree(n: Int): Tree[Int] = {
    def sampleInvalidTreeByRange(i: Int): Tree[Int] = {
      if (i > n) {
	NilTree
      }
      else {
	val v = i + (n-i+1)/2
	Node(sampleTreeByRange(i, v-1),
	     if (v == n) 0 else v,
	     sampleInvalidTreeByRange(v+1))
      }
    }

    sampleInvalidTreeByRange(1)
  }
}
