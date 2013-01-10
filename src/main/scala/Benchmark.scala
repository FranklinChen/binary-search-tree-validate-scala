import com.google.caliper.Param

class Benchmark extends SimpleScalaBenchmark {
  /**
   Size of test tree to create.
   */
  @Param(Array("10",
               "100",
               "1000",
               "10000",
               "100000",
               "1000000",
               "10000000"))
  val size: Int = 0

  /**
   Use a valid or invalid test tree.
   */
  @Param
  val valid: Boolean = false

  /**
   Use a left skewed or balanced test tree.
   TODO also measure right skewed.
   */
  @Param
  val leftSkewed: Boolean = false

  /**
   The test tree.
   */
  var tree: Tree[Int] = NilTree

  /**
   Which validator to run.
   TODO Use Java enumeration? Or better Caliper injection.
   */
  @Param(Array("simple sequential",
               "simple parallel",
               "early exit sequential",
               "cutoff parallel"))
  val validator: String = "";
  
  var theValidator: BSTValidator = SimpleSequentialValidator;

  /**
   For Caliper.
   */
  override def setUp() {
    tree = toTree(size, valid, leftSkewed)
    theValidator = toValidator(validator)
  }
  
  def toTree(size: Int, valid: Boolean, leftSkewed: Boolean) =
    (valid, leftSkewed) match {
      case (true, false) => TreeSamples.validBalancedTree(size)
      case (true, true) => TreeSamples.validLeftSkewedTree(size)
      case (false, false) => TreeSamples.invalidBalancedTree(size)
      case (false, true) => TreeSamples.invalidLeftSkewedTree(size)
  }

  def toValidator(v: String) = v match {
    case "simple sequential" => SimpleSequentialValidator
    case "simple parallel" => SimpleParallelValidator
    case "early exit sequential" => ExitSequentialValidator
    case "cutoff parallel" => PartialParallelValidator
  }

  /**
   Do the work.
   */
  def timeBinarySearchTreeValidation(reps: Int) = {
    repeat(reps) {
      val result = theValidator.isValid(tree)
      if (result) 1 else 0
    }
  }
}

