//> using test.dep org.scalameta::munit::1.0.0-M3
//> using file ../utils.scala

package day09

class Day09Test extends munit.FunSuite:
  test("part1 sample") {
    assertEquals(part1(utils.readInput("day09/sample.txt")), 114L)
  }

  test("part2 sample") {
    assertEquals(part2(utils.readInput("day09/sample.txt")), 2L)
  }
