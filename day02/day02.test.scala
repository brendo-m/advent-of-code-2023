//> using test.dep org.scalameta::munit::1.0.0-M3
//> using file ../utils.scala

package day02

class Day02Test extends munit.FunSuite:
  test("part1 sample") {
    assertEquals(part1(utils.readInput("day02/sample.txt")), 8)
  }

  test("part2 sample") {
    assertEquals(part2(utils.readInput("day02/sample.txt")), 2286)
  }
