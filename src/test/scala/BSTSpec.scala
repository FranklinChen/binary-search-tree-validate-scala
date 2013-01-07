import org.specs2.mutable._

class BSTSpec extends Specification {
  "Example tree" should {
    "be reported as a BST" in {
      val t1 = Node(Node(NilTree, 1, NilTree),
                    2,
                    Node(NilTree, 3, NilTree))
      BST.isValid(t1) must beTrue
    }

    "be reported as not a BST" in {
      val t1 = Node(Node(NilTree, 4, NilTree),
                    2,
                    Node(NilTree, 3, NilTree))
      BST.isValid(t1) must beFalse
    }
  }
}
