import org.specs2.mutable._

// TODO Use ScalaCheck
class BSTSpec extends Specification {
  // TODO
  val validator = ParallelValidator

  "Small trees" should {
    "be reported as a BST" in {
      val t1 = Node(Node(NilTree, 1, NilTree),
                    2,
                    Node(NilTree, 3, NilTree))
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
    "be correct" in {
      val t1 = Node(Node(Node(NilTree, 1, NilTree),
			 2,
			 Node(NilTree, 3, NilTree)),
		    4,
		    Node(Node(NilTree, 5, NilTree),
			 6,
			 Node(NilTree, 7, NilTree)))
      val t2 = BST.sampleTree(7)
      t1 must_== t2 
    }

    "be wrong at end" in {
      val t1 = Node(Node(Node(NilTree, 1, NilTree),
			 2,
			 Node(NilTree, 3, NilTree)),
		    4,
		    Node(Node(NilTree, 5, NilTree),
			 6,
			 Node(NilTree, 0, NilTree)))
      val t2 = BST.sampleInvalidTree(7)
      t1 must_== t2 
    }
  }

  "Huge tree" should {
    // Add an extra 0 and run out of Java heap space
    val size = 1000000

    "be reported as a BST" in {
      val t1 = BST.sampleTree(size)
      validator.isValid(t1) must beTrue
    }

    "be invalid when bottom right invalid" in {
      val t1 = BST.sampleInvalidTree(size)
      validator.isValid(t1) must beFalse
    }
  }
}
