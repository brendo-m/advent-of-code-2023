//> using test.dep org.scalameta::munit::1.0.0-M3
//> using file ../utils.scala

package day03

class Day03Test extends munit.FunSuite:
  test("part1 sample") {
    assertEquals(part1(utils.readInput("day03/sample.txt")), 4361)
  }

  test("part2 sample") {
    assertEquals(part2(utils.readInput("day03/sample.txt")), 467835)
  }
