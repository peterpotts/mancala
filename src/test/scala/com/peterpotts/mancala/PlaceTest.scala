package com.peterpotts.mancala

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class PlaceTest extends WordSpec with Matchers {

  "A place" should {
    "bin to bin" in {
      Place(0, 1, Bin(2)).next should equal(Place(0, 1, Bin(3)))
    }

    "bin to mancala" in {
      Place(1, 1, Bin(5)).next should equal(Place(1, 1, Mancala()))
    }

    "bin skip mancala to bin" in {
      Place(0, 1, Bin(5)).next should equal(Place(0, 0, Bin(0)))
    }

    "mancala to bin" in {
      Place(0, 0, Mancala()).next should equal(Place(0, 1, Bin(0)))
    }
  }
}
