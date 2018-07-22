package com.github.render
import com.github.Sudoku

object SimpleSudokuRenderer extends SudokuRenderer {
  def renderSudoku(sudoku: Sudoku): Unit = {

    val cellsIterator = sudoku.cells.iterator

    for (row <- 1 to 9) {
      for (col <- 1 to 9) {
        val cell = cellsIterator.next()
        cell.value match {
          case Some(value) => print(value)
          case _ => print("-")
        }
        print("\t")
      }
      println()
    }

  }
}
