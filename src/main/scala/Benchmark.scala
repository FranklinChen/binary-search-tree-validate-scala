import com.google.caliper.Param

class Benchmark extends SimpleScalaBenchmark {
  /**
   Size of test tree to create.

   Watch out for running out of heap space or stack space, at 10000000
   */
  @Param(Array("10",
               "100",
               "1000",
               "10000",
               "100000",
               "1000000"
             ))
  val size: Int = 0

  /**
   Use a valid or invalid test tree.
   */
  @Param
  val valid: Boolean = false

  /**
    How to skew the test tree.
   */
  @Param(Array("balanced", "left", "right"))
  val skew: String = "balanced"

  /**
   The test tree.
   */
  var tree: Tree[Int] = NilTree

  /**
   Which validator to run.

   Avoid running the ones that stack overflow.
   TODO Use Java enumeration? Or better Caliper injection.
   */
  @Param(Array("simple sequential",
               "early exit sequential",
               "cutoff parallel",
	       "early exit cutoff parallel"
	     ))
  val validator: String = ""
  
  var theValidator: BSTValidator = SimpleSequentialValidator

  /**
   For Caliper.
   */
  override def setUp() {
    tree = toTree(size, valid, skew)
    theValidator = toValidator(validator)
  }
  
  def toTree(size: Int, valid: Boolean, skew: String) =
    (valid, skew) match {
      case (true, "balanced") => TreeSamples.validBalancedTree(size)
      case (true, "left") => TreeSamples.validLeftSkewedTree(size)
      case (true, "right") => TreeSamples.validRightSkewedTree(size)
      case (false, "balanced") => TreeSamples.invalidBalancedTree(size)
      case (false, "left") => TreeSamples.invalidLeftSkewedTree(size)
      case (false, "right") => TreeSamples.invalidRightSkewedTree(size)
  }

  def toValidator(v: String) = v match {
    case "simple sequential" => SimpleSequentialValidator
    case "early exit sequential" => ExitSequentialValidator
    case "cutoff parallel" => PartialParallelValidator
    case "early exit cutoff parallel" => ExitPartialParallelValidator
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

