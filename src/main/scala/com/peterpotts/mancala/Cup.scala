package com.peterpotts.mancala

sealed trait Cup

case class Bin(id: Int) extends Cup

case class Mancala() extends Cup