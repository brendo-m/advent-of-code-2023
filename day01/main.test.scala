//> using test.dep org.scalameta::munit::1.0.0-M3
//> using file ../utils.scala

package day01

class Day01Test extends munit.FunSuite:
  test("part1 sample") {
    assertEquals(part1(utils.readInput("day01/sample1.txt")), 142)
  }

  test("part2 sample") {
    assertEquals(part2(utils.readInput("day01/sample2.txt")), 281)
  }
