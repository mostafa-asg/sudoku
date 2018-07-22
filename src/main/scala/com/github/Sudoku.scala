package com.github

import scala.collection.mutable

case class Sudoku(cells: List[Cell]) {

  def solve(): Sudoku = {
    def solve(sudoku: Sudoku):Sudoku = {
      SudokuSolver.getNextAction(sudoku) match {
        case SetValue(position, value) => solve(sudoku.setValue(position, value))
        case Solved => sudoku
      }
    }

    solve(this)
  }

  def setValue(position: Position, value: Int): Sudoku = {
    val targetCell = cells.find(x => x.position.equals(position)).get

    // if we can set a value
    if (targetCell.candidates.contains(value)) {

      val updatedCells = cells.map {
        case cell if cell.position.equals(position) => cell.setValue(value)
        case cell if targetCell.dependsOn.contains(cell.position) => cell.removeCandidate(value)
        case otherCells => otherCells
      }

      copy(cells = updatedCells)
    } else this
  }

  lazy val isSolved: Boolean = {
    val cond1 = cells.forall(cell => cell.value.isDefined)

    val booleans = cells.map { cell =>
      val dependentCell = cells.filter(x => cell.dependsOn.contains(x.position))
      dependentCell.forall(x => !x.value.equals(cell.value))
    }

    cond1 && booleans.forall(x => x == true)
  }

}

object Sudoku {

  def empty: Sudoku = Sudoku(cells = getEmptyCells)

  private lazy val getEmptyCells: List[Cell] = {
    val result = new mutable.ListBuffer[Cell]

    for(row <- 0 to 8) {
      for(col <- 0 to 8) {
        result += Cell(position = Position(row,col))
      }
    }

    result.toList
  }
}

