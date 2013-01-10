class DoublyRecursiveSimpleSequentialValidatorSpec extends BSTSpec {
  val validator = DoublyRecursiveSimpleSequentialValidator

  val size = 5000
}

class ExitSequentialValidatorSpec extends BSTSpec {
  val validator = ExitSequentialValidator

  val size = 5000
}

class PartialParallelValidatorSpec extends BSTSpec {
  val validator = PartialParallelValidator

  val size = 5000
}

class SimpleParallelValidatorSpec extends BSTSpec {
  val validator = SimpleParallelValidator

  // TODO very tiny
  val size = 100
}

class SimpleSequentialValidatorSpec extends BSTSpec {
  val validator = SimpleSequentialValidator

  val size = 5000
}

class SinglyRecursiveExitSequentialValidatorSpec extends BSTSpec {
  val validator = SinglyRecursiveExitSequentialValidator

  val size = 1000
}

class SinglyRecursiveSimpleSequentialValidatorSpec extends BSTSpec {
  val validator = SinglyRecursiveSimpleSequentialValidator

  val size = 1000
}
