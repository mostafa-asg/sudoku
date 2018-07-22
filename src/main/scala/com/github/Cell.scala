package com.github

case class Cell(position: Position,
                value: Option[Int] = None,
                candidates: Set[Int] = Set(1,2,3,4,5,6,7,8,9)) {

  def setValue(value: Int): Cell = {
    copy(value = Some(value)).removeCandidate(value)
  }

  def removeCandidate(value: Int): Cell = {
    copy(candidates = candidates.diff(Set(value)))
  }

  lazy val dependsOn: Set[Position] = {
    val zoneCellPositions = Zone.getCellPositions(myZoneId)

    (allHorizontalCellPositions union
    allVerticalCellPositions union
    zoneCellPositions) diff Set(position)
  }

  private lazy val myZoneId = Zone.getZoneId(position)

  private lazy val allVerticalCellPositions: Set[Position] = {
    List.fill(9)("garbage").zipWithIndex.map {
      case (_,index) => Position(index, position.col)
    }.toSet
  }

  private lazy val allHorizontalCellPositions: Set[Position] = {
    List.fill(9)("garbage").zipWithIndex.map {
      case (_,index) => Position(position.row,index)
    }.toSet
  }

}
