package com.peterpotts.mancala

import scala.io.StdIn

case class Board(sides: IndexedSeq[Side]) {
  require(sides.size == 2, "A board has two sides")

  def reap(sideId: Int, bin: Bin): (Int, Board) = {
    val (seeds, side) = sides(sideId).reap(bin)
    seeds -> copy(sides = sides.updated(sideId, side))
  }

  def sow(sideId: Int, cup: Cup): Board = {
    val side = sides(sideId).sow(cup)
    copy(sides = sides.updated(sideId, side))
  }

  //  def next(playerId: Int, sideId: Int, binId)

  def readBin(playerId: Int): IO[Bin] = for {
    _ <- putLine(display)
    _ <- put(s"Player $playerId : Reap bin ? ")
    line <- getLine
  } yield Bin(line.toInt)

  def reapAndSow(playerId: Int): IO[Board] = readBin(playerId).flatMap(bin => reapAndSow(playerId, bin))

  def reapAndSow(playerId: Int, bin: Bin): IO[Board] = {
    val (reapedSeeds, reapedBoard) = reap(playerId, bin)
    val places = Iterator.iterate(Place(playerId, playerId, bin))(_.next).drop(1).take(reapedSeeds).toIndexedSeq

    val sowedBoard = places.foldLeft(reapedBoard) {
      case (board, place) => board.sow(place.sideId, place.cup)
    }

    val lastPlace = places.last

    lastPlace.cup match {
      case bin@Bin(id) =>
        if (sowedBoard.sides(lastPlace.sideId).bins(id) > 1)
          reapAndSow(playerId, bin)
        else
          sowedBoard
      case Mancala() =>
        reapAndSow(playerId)
    }
  }

//  def reapAndSow(playerId: Int, binId: Int): Board = {
//    val reap = Move(playerId, Reap(Bin(binId)))
//    val (reapedSeeds, reapedBoard) = play(reap)
//    val places = Iterator.iterate(Place(playerId, playerId, Bin(binId)))(_.next).drop(1).take(reapedSeeds).toIndexedSeq
//    val sows = places.map(place => Move(place.sideId, Sow(place.cup)))
//
//    val reapedAndSowedBoard = sows.foldLeft(reapedBoard) {
//      case (board, sow) => board.play(sow)._2
//    }
//    println(reapedAndSowedBoard.display)
//    reapedAndSowedBoard
//  }

  def display: String = List(
    sides(1).bins.reverse.mkString("\t", "\t", ""),
    sides.map(_.mancala).reverse.mkString("", "\t" * 7, ""),
    sides(0).bins.mkString("\t", "\t", "")).mkString("\n")
}

object Board {
  val initial = Board(IndexedSeq.fill(2)(Side.initial))
}