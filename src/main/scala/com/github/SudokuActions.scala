package com.github

sealed trait SudokuAction
case class SetValue(position: Position, value: Int) extends SudokuAction
case object Solved extends SudokuAction