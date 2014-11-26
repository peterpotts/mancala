package com.peterpotts.mancala

case class Board(sides: IndexedSeq[Side]) {
  require(sides.size == 2, "A board has two sides")

  def play(move: Move): (Int, Board) = {
    val (seeds, side) = sides(move.sideId).play(move.farm)
    seeds -> copy(sides = sides.updated(move.sideId, side))
  }

  //  def next(playerId: Int, sideId: Int, binId)

  def reapAndSow(playerId: Int, binId: Int): Board = {
    val reap = Move(playerId, Reap(Bin(binId)))
    val (reapedSeeds, reapedBoard) = play(reap)
    val places = Iterator.iterate(Place(playerId, playerId, Bin(binId)))(_.next).drop(1).take(reapedSeeds).toIndexedSeq
    val sows = places.map(place => Move(place.sideId, Sow(place.cup)))

    val reapedAndSowedBoard = sows.foldLeft(reapedBoard) {
      case (board, sow) => board.play(sow)._2
    }
    println(reapedAndSowedBoard.display)
    reapedAndSowedBoard
  }

  def display: String = List(
    sides(1).bins.reverse.mkString("\t", "\t", ""),
    sides.map(_.mancala).reverse.mkString("", "\t" * 7, ""),
    sides(0).bins.mkString("\t", "\t", "")).mkString("\n")
}

object Board {
  val initial = Board(IndexedSeq.fill(2)(Side.initial))
}