package com.peterpotts.mancala

object Application {
  def main(args: Array[String]): Unit = {
    val board = Board.initial
    println(board.display)

    board.reapAndSow(0, 4)
  }
}
