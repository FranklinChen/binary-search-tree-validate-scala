import org.specs2.mutable._

// TODO Use ScalaCheck
class BSTSpec extends Specification {
  // TODO parameterize
  val validator = PartialParallelValidator

  "Small trees" should {
    "be reported as a BST" in {
      val t1 = Node(TreeUtils.leaf(1),
                    2,
                    TreeUtils.leaf(3))
      validator.isValid(t1) must beTrue
    }

    "be reported as not a BST" in {
      val t1 = Node(Node(NilTree, 4, NilTree),
                    2,
                    Node(NilTree, 3, NilTree))
      validator.isValid(t1) must beFalse
    }
  }

  "Sample trees" should {
    "be correct when valid and balanced" in {
      val t1 = Node(Node(TreeUtils.leaf(1),
			 2,
			 TreeUtils.leaf(3)),
		    4,
		    Node(TreeUtils.leaf(5),
			 6,
			 TreeUtils.leaf(7)))
      val t2 = TreeSamples.validBalancedTree(7)
      t1 must_== t2 
    }

    "be invalid when balanced" in {
      val t1 = Node(Node(TreeUtils.leaf(1),
			 2,
			 TreeUtils.leaf(3)),
		    4,
		    Node(TreeUtils.leaf(5),
			 6,
			 TreeUtils.leaf(0)))
      val t2 = TreeSamples.invalidBalancedTree(7)
      t1 must_== t2 
    }

    "be valid and left skewed" in {
      val t1 = Node(Node(Node(Node(NilTree,
                                   1,
                                   NilTree),
                              2,
                              NilTree),
                         3,
                         NilTree),
                    4,
                    NilTree)
                    
      val t2 = TreeSamples.validLeftSkewedTree(4)
      t1 must_== t2 
    }

    "be invalid and left skewed" in {
      val t1 = Node(Node(Node(Node(NilTree,
                                   1,
                                   NilTree),
                              2,
                              NilTree),
                         3,
                         NilTree),
                    0,
                    NilTree)
                    
      val t2 = TreeSamples.invalidLeftSkewedTree(4)
      t1 must_== t2 
    }
  }

  "Huge tree" should {
    // Watch out for running out of heap space
    // TODO 10000000 runs out
    val size = 1000000

   "be reported as a BST" in {
     val t1 = TreeSamples.validBalancedTree(size)
     validator.isValid(t1) must beTrue
   }
  }
}
