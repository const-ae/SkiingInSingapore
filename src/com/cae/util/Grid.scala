package com.cae.util

import scala.io.Source

/**
 * Created by constantin on 24.08.15.
 */
class Grid(val width: Int, val height: Int, data: List[List[Int]]) {

  def this(){
    this(10, 10, List.fill(10, 10)((Math.random() * 10).toInt))
  }

  def this(width: Int, height: Int) {
    this(width, height, List.fill(width, height)((Math.random() * 10).toInt))
  }

  def get(x: Int, y: Int) = data(y)(x)


  override def toString: String = {
    data.map(_.mkString("\t")).mkString("\n")
  }

  def north(x: Int, y: Int): Option[Int] = if(y-1 >= 0) Some(get(x, y-1)) else None
  def east(x: Int, y: Int): Option[Int] = if(x+1 < width) Some(get(x+1, y)) else None
  def south(x: Int, y: Int): Option[Int] = if(y+1 < height) Some(get(x, y+1)) else None
  def west(x: Int, y: Int): Option[Int] = if(x-1 >= 0) Some(get(x-1, y)) else None

}

object Grid {

  def apply(terrain: String): Grid = {
    terrain.split("\n").filter(!_.trim.isEmpty).toList match{
      case header :: tail  =>
        val shape = header.split("\\s+")
        val width = shape(0).toInt
        val height = shape(1).toInt
        // Convert the rest to List of List of Int
        val data = tail.map{_.split("\\s+").toList.map(_.toInt)}
        if(data.length != height) throw new IllegalArgumentException(s"The Terrain String was badly formated\n" +
          s"the provided height ($height) did not match the actual number of rows (${data.length})")
        if(! data.forall(l => l.length == width)) throw new IllegalArgumentException(s"The Terrain String was badly formated\n" +
          s"the provided width ($width) did not match the actual width in all rows")

        new Grid(width, height, data)
      case e => throw new IllegalArgumentException("String did not conform to the expected format: " + e)
    }
  }

  def loadFile(location: String): Grid = {
    val terrain = Source.fromFile(location).mkString
    apply(terrain)
  }

  def loadURL(location: String): Grid = {
    val terrain = Source.fromURL(location).mkString
    apply(terrain)
  }

}
