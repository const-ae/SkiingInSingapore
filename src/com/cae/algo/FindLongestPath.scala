package com.cae.algo

import java.util.concurrent.ConcurrentLinkedQueue

import com.cae.util.Grid
import scala.collection._

/**
 * Created by constantin on 24.08.15.
 */
class FindLongestPathAlgorithm(grid: Grid) {


  def findBestPath(): Path = {
    val allPaths = for{
      x <- 0 until grid.width
      y <- 0 until grid.height
    } yield followPath(x, y)

    val pathsSortedByLength = allPaths.flatten.sortBy(p => p.path.length)
    val maxLength = pathsSortedByLength.last.path.length
    val pathsSortedByAltitude = pathsSortedByLength.reverse.takeWhile(_.path.length == maxLength).sortBy(_.altitude(grid))
    pathsSortedByAltitude.head
  }

  def followPath(startX: Int, startY: Int): List[Path] = {

    val listOfPaths = new ConcurrentLinkedQueue[Path]()
    def followPathRec(x: Int, y: Int, p: Path): Unit = {
      val altitude = grid.get(x,y)
      val allWays =  Map('North -> grid.north(x,y),'East  -> grid.east(x,y),'South -> grid.south(x,y),'West -> grid.west(x,y))
      val possibleWays = allWays.toList.filter(_._2 match {
        case Some(newAlt) => newAlt < altitude
        case None => false
      })

      possibleWays.par.foreach(_._1 match {
        case 'North => followPathRec(x, y-1, p.extend(x,y-1))
        case 'East => followPathRec(x+1, y, p.extend(x+1,y))
        case 'South => followPathRec(x, y+1, p.extend(x,y+1))
        case 'West => followPathRec(x-1, y, p.extend(x-1,y))
      })
      listOfPaths.add(p)
    }
    followPathRec(startX, startY, Path((startX, startY) :: Nil))
    import scala.collection.JavaConversions._
    listOfPaths.toList
  }


}

case class Path(path: List[(Int, Int)]){
  def extend(x: Int, y: Int) = Path((x,y) :: path)
  def altitude(grid: Grid) = {
    val first = path.head
    val last = path.last
    grid.get(first._1, first._2) - grid.get(last._1, last._2)
  }
}



