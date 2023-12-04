//> using test.dep org.scalameta::munit::1.0.0-M3
//> using file ../utils.scala

package day04

class Day04Test extends munit.FunSuite:
  test("part1 sample") {
    assertEquals(part1(utils.readInput("day04/sample.txt")), 13)
  }

  test("part2 sample") {
    assertEquals(part2(utils.readInput("day04/sample.txt")), 30)
  }
