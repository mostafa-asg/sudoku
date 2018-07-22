package com.github

import cats.data.State
import com.github.render.SimpleSudokuRenderer

object Main {

  def main(args: Array[String]): Unit = {

    SimpleSudokuRenderer.renderSudoku( Sudoku.empty.solve() )

  }

}
