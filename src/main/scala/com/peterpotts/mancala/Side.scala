package com.peterpotts.mancala

case class Side(bins: IndexedSeq[Int], mancala: Int) {
  require(bins.size == 6, "A side has six bins")
  require(bins.forall(_ >= 0), "A bin contains seeds")
  require(mancala >= 0, "A mancala contains seeds")

  def reap(bin: Bin): (Int, Side) = bins(bin.id) -> copy(bins = bins.updated(bin.id, 0))

  def sow(cup: Cup): Side = cup match {
    case Bin(binId) => copy(bins = bins.updated(binId, bins(binId) + 1))
    case Mancala() => copy(mancala = mancala + 1)
  }
}

object Side {
  val initial = Side(IndexedSeq.fill(6)(4), 0)
}