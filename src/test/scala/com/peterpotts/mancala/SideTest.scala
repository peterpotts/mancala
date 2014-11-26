package com.peterpotts.mancala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class SideTest extends WordSpec with Matchers {
  val side = Side(IndexedSeq(1, 2, 3, 4, 5, 6), 7)

  "A side" should {
    "reap" in {
      side.play(Reap(Bin(1))) should equal(2 -> Side(IndexedSeq(1, 0, 3, 4, 5, 6), 7))
    }

    "sow bin" in {
      side.play(Sow(Bin(3))) should equal(-1 -> Side(IndexedSeq(1, 2, 3, 5, 5, 6), 7))
    }

    "sow mancala" in {
      side.play(Sow(Mancala())) should equal(-1 -> Side(IndexedSeq(1, 2, 3, 4, 5, 6), 8))
    }
  }
}
