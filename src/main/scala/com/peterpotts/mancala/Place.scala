package com.peterpotts.mancala

case class Place(playerId: Int, sideId: Int, cup: Cup) {
  def next: Place = cup match {
    case Bin(binId) if binId < 5 => copy(cup = Bin(binId + 1))
    case Bin(binId) => if (playerId == sideId) copy(cup = Mancala()) else copy(sideId = 1 - sideId, cup = Bin(0))
    case Mancala() => copy(sideId = 1 - sideId, cup = Bin(0))
  }
}