package com.peterpotts.mancala

sealed trait Farm

case class Reap(bin: Bin) extends Farm

case class Sow(cup: Cup) extends Farm
