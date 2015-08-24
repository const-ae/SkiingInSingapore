package com.cae.test

import com.cae.algo.FindLongestPathAlgorithm
import com.cae.util.Grid

/**
 * Created by constantin on 24.08.15.
 */
object Experiment extends App {

  println("Hello Ski Resort")

  println("Here is the Terrain")

//  val terrain: String =
//    """
//      |4 4
//      |10 11 12 1
//      |9 16 13 2
//      |8 15 14 3
//      |7 6 5 4
//    """.stripMargin
//
//  val grid = Grid(terrain)

  val grid = Grid.loadFile("data/map.txt")

  println(grid)

  val algo = new FindLongestPathAlgorithm(grid)

  val start = System.currentTimeMillis()
  val path = algo.findBestPath()
  println(path)
  println(s"The length of the path is ${path.path.length} and the altitude  is ${path.altitude(grid)}}")
  
  val end = System.currentTimeMillis()
  println(s"Finished in ${(end - start)/1000.0} seconds")

}
