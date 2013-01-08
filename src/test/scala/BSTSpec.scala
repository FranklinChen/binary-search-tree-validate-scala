import org.specs2.mutable._

// TODO Use ScalaCheck
class BSTSpec extends Specification {
  "Example tree" should {
    val validator = ParallelValidator

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
}
