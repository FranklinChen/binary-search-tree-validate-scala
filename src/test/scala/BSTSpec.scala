import org.specs2.mutable._

// TODO Use ScalaCheck
trait BSTSpec extends Specification {
  /**
     The validator to use.
  */
  val validator: BSTValidator

  /**
     How big a huge tree to test.
  */
  val size: Int

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

    "be valid and right skewed" in {
      val t1 = Node(NilTree,
		    1,
		    Node(NilTree,
			 2,
			 Node(NilTree,
			      3,
			      Node(NilTree,
				   4,
				   NilTree))))
                    
      val t2 = TreeSamples.validRightSkewedTree(4)
      t1 must_== t2 
    }

    "be invalid and right skewed" in {
      val t1 = Node(NilTree,
		    1,
		    Node(NilTree,
			 2,
			 Node(NilTree,
			      3,
			      Node(NilTree,
				   0,
				   NilTree))))
                    
      val t2 = TreeSamples.invalidRightSkewedTree(4)
      t1 must_== t2 
    }
  }

  "Huge trees" should {
    "be reported as a valid left skewed BST without running out of stack" in {
      val t1 = TreeSamples.validLeftSkewedTree(size)
      validator.isValid(t1) must beTrue
    }

    "be reported as a valid balanced BST without running out of heap" in {
      val t1 = TreeSamples.validBalancedTree(size)
      validator.isValid(t1) must beTrue
    }
  }
}
