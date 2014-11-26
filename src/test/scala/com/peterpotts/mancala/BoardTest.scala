package com.peterpotts.mancala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class BoardTest extends WordSpec with Matchers {
  val side0 = Side(IndexedSeq(1, 2, 3, 4, 5, 6), 7)
  val side1 = Side(IndexedSeq(8, 9, 10, 11, 12, 13), 14)
  val board = Board(IndexedSeq(side0, side1))

  "A board" should {
    "initial" in {
      val expected = " 13 12 11 10 9 8\n14       7\n 1 2 3 4 5 6"
      val actual = board.display.replace("\t", " ")
      actual should equal(expected)
    }

    "move" in {
      val (seeds, side) = side1.play(Reap(Bin(2)))
      val end = Board(IndexedSeq(side0, side))
      board.play(Move(1, Reap(Bin(2)))) should equal(seeds -> end)
    }
  }
}
