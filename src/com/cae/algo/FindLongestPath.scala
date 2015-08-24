package com.cae.algo

import com.cae.util.Grid
import scala.collection._

/**
 * Created by constantin on 24.08.15.
 */
class FindLongestPathAlgorithm(grid: Grid) {


  def findBestPath(): Path = {
    ???
  }

  def followPath(startX: Int, startY: Int): List[Path] = {

    val listOfPath = mutable.Buffer.empty[Path]

    def followPathRec(x: Int, y: Int, p: Path): Unit = {
      val altitude = grid.get(x,y)
      val allWays =  Map('North -> grid.north(x,y),'East  -> grid.east(x,y),'South -> grid.south(x,y),'West -> grid.west(x,y))
      val possibleWays = allWays.toList.filter(_._2 match {
        case Some(newAlt) => newAlt < altitude
        case None => false
      })

      possibleWays.foreach(_._1 match {
        case 'North => followPathRec(x, y-1, p.extend(x,y-1))
        case 'East => followPathRec(x+1, y, p.extend(x+1,y))
        case 'South => followPathRec(x, y+1, p.extend(x,y+1))
        case 'West => followPathRec(x-1, y, p.extend(x-1,y))
      })
      listOfPath.append(p)
    }
    followPathRec(startX, startY, Path((startX, startY) :: Nil))
    listOfPath.toList
  }


}

case class Path(path: List[(Int, Int)]){
  def extend(x: Int, y: Int) = Path((x,y) :: path)
}



