//// Sequential without exit

class DoublyRecursiveSimpleSequentialValidatorSpec extends BSTSpec {
  val validator = DoublyRecursiveSimpleSequentialValidator

  val size = 10
  //val size = 5000
}

class SinglyRecursiveSimpleSequentialValidatorSpec extends BSTSpec {
  val validator = SinglyRecursiveSimpleSequentialValidator

  val size = 10
  //val size = 1000
}

// The best
class SimpleSequentialValidatorSpec extends BSTSpec {
  val validator = SimpleSequentialValidator

  val size = 10
  //val size = 5000
}

//// Sequential with exit

class SinglyRecursiveExitSequentialValidatorSpec extends BSTSpec {
  val validator = SinglyRecursiveExitSequentialValidator

  val size = 10
  //val size = 1000
}

// The best
class ExitSequentialValidatorSpec extends BSTSpec {
  val validator = ExitSequentialValidator

  val size = 10
  //val size = 5000
}

//// Parallel

class SimpleParallelValidatorSpec extends BSTSpec {
  val validator = SimpleParallelValidator

  // Make tiny
  val size = 10
  //val size = 100
}

class PartialParallelValidatorSpec extends BSTSpec {
  val validator = PartialParallelValidator

  val size = 10
  //val size = 5000
}

class ExitPartialParallelValidatorSpec extends BSTSpec {
  val validator = ExitPartialParallelValidator

  val size = 10
  //val size = 5000
}
