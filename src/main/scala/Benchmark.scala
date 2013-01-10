import com.google.caliper.Param

class Benchmark extends SimpleScalaBenchmark {
  @Param(Array("10", "100", "1000", "10000", "100000", "100000"))
  val size: Int = 0

  @Param(Array("NaiveValidator", "ParallelValidator"))
  val validator: String = ""
  
  var validatorActual: BSTValidator = NaiveValidator

  @Param(Array("valid", "invalid"))
  var tree: String = ""

  var treeActual: Tree[Int] = NilTree

  override def setUp() {
    treeActual = toTree(size, tree)
    validatorActual = toValidator(validator)
  }
  
  def toTree(size: Int, t: String) = t match {
    case "valid" => BST.sampleTree(size)
    case "invalid" => BST.sampleInvalidTree(size)
  }

  def toValidator(v: String) = v match {
    case "NaiveValidator" => NaiveValidator
    case "ParallelValidator" => ParallelValidator
  }

  def timeTree(reps: Int) = {
    repeat(reps) {
      validatorActual.isValid(treeActual)
      1
    }
  }
}

