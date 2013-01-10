sealed trait Tree[+A]

final case object NilTree extends Tree[Nothing]

/**
  left is var only because of needing mutation to create left skewed
  tree without stack overflow.
  */
case class Node[A](var left: Tree[A],
                   v: A,
                   right: Tree[A]) extends Tree[A]
