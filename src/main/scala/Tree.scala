sealed trait Tree[+A]

final case object NilTree extends Tree[Nothing]

case class Node[A](left: Tree[A],
                   v: A,
                   right: Tree[A]) extends Tree[A]
