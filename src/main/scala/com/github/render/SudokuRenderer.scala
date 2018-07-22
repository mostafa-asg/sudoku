package com.github.render

import com.github.Sudoku

trait SudokuRenderer {
  def renderSudoku(sudoku: Sudoku): Unit
}
