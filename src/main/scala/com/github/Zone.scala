package com.github

import scala.collection.mutable
import scala.collection.immutable

object Zone {

  private lazy val zoneToCellPositions: immutable.Map[ZoneId,immutable.Set[Position]] = {

    val result = mutable.HashMap[ZoneId,immutable.Set[Position]]()
    var id = 0

    for (zoneRow <- 0 to 2) {
      for (zoneCol <- 0 to 2) {
        val positions = mutable.Set[Position]()

        for (row <- 0 to 2) {
          for (col <- 0 to 2) {
            positions += Position( zoneRow * 3 + row , zoneCol * 3 + col )
          }
        }
        result.update(ZoneId(id) , positions.toSet)
        id = id + 1
      }
    }

    result.toMap
  }

  private lazy val positionToZoneId: immutable.Map[Position,ZoneId] = {

    val result = mutable.HashMap[Position,ZoneId]()
    var id = 0

    for (zoneRow <- 0 to 2) {
      for (zoneCol <- 0 to 2) {

        for (row <- 0 to 2) {
          for (col <- 0 to 2) {
            result.update(Position( zoneRow * 3 + row , zoneCol * 3 + col ),ZoneId(id))
          }
        }
        id = id + 1
      }
    }

    result.toMap
  }

  def getCellPositions(zoneId: ZoneId): Set[Position] = zoneToCellPositions(zoneId)

  def getZoneId(position: Position): ZoneId = positionToZoneId(position)

}
