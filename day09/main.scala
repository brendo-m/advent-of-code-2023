//> using file ../utils.scala

package day09

import scala.annotation.tailrec

@main
def solve() =
  val input = utils.readInput("day09/input.txt")
  println(s"Part 1: ${part1(input)}")
  println(s"Part 2: ${part2(input)}")

def part1(input: List[String]): Long =
  val histories = input.map(_.split("\\s+").map(_.toInt).toList)

  histories
    .map: history =>
      generateDiffs(history).map(_.last.toLong).sum
    .sum

def part2(input: List[String]): Long =
  val histories = input.map(_.split("\\s+").map(_.toInt).toList)

  histories
    .map: history =>
      generateDiffs(history)
        .foldLeft(0L): (acc, diff) =>
          diff.head - acc
    .sum

// returns them reversed and skips the all 0's history
private def generateDiffs(history: List[Int]): List[List[Int]] =
  @tailrec
  def inner(acc: List[List[Int]]): List[List[Int]] =
    val diffs = acc.head.sliding(2).map { case List(l, r) => r - l }.toList
    if diffs.exists(_ != 0) then inner(diffs :: acc)
    else acc

  inner(List(history))
