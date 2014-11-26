package com.peterpotts

import scala.language.implicitConversions

package object mancala {
  type IO[A] = () => A

  implicit def unit[A](a: => A): IO[A] = () => a

  implicit class RichIO[A](io: IO[A]) {
    def flatMap[B](f: A => IO[B]): IO[B] = () => f(io())()

    def map[B](f: A => B): IO[B] = () => f(io())
  }

  def getLine: IO[String] = scala.io.StdIn.readLine()

  def put(line: String): IO[Unit] = Console.print(line)

  def putLine(line: String): IO[Unit] = Console.println(line)
}
