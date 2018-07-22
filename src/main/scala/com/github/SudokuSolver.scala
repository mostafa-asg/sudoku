package com.github

object SudokuSolver {

  def getNextAction(sudoku: Sudoku): SudokuAction = {

    val cells = sudoku.cells
                .filter(x => x.value.isEmpty)
                .filter(x => x.candidates.nonEmpty)
                .sortWith((c1,c2) => c1.candidates.size <= c2.candidates.size)

    if (cells.isEmpty) {
      // TODO check all constraints
      Solved
    } else {
      val selectedCell = cells.head
      val value = selectedCell.candidates.head
      SetValue(selectedCell.position, value)
    }

  }

}
