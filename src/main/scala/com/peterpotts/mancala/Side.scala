package com.peterpotts.mancala

case class Side(bins: IndexedSeq[Int], mancala: Int) {
  require(bins.size == 6, "A side has six bins")
  require(bins.forall(_ >= 0), "A bin contains seeds")
  require(mancala >= 0, "A mancala contains seeds")

  def play(farm: Farm): (Int, Side) = farm match {
    case Reap(Bin(binId)) => bins(binId) -> copy(bins = bins.updated(binId, 0))
    case Sow(Bin(binId)) => -1 -> copy(bins = bins.updated(binId, bins(binId) + 1))
    case Sow(Mancala()) => -1 -> copy(mancala = mancala + 1)
  }
}

object Side {
  val initial = Side(IndexedSeq.fill(6)(4), 0)
}