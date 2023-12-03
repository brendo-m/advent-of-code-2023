//> using file ../utils.scala

package day02

case class Draw(red: Int, green: Int, blue: Int)

object Draw:
  // example: 3 blue, 4 red, 1 green
  def fromString(s: String): Draw =
    val draw = Draw(0, 0, 0)
    s.split(",")
      .toList
      .foldLeft(draw): (acc, countAndColor) =>
        val Array(count, color) = countAndColor.trim.split(" ")
        color match
          case "red"   => acc.copy(red = count.toInt)
          case "green" => acc.copy(green = count.toInt)
          case "blue"  => acc.copy(blue = count.toInt)

case class Game(id: Int, draws: List[Draw])

object Game:
  // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
  def fromString(s: String): Game =
    val Array(id, draws) = s.split(":")
    Game(id.substring("Game ".length).toInt, draws.split(";").toList.map(Draw.fromString))

  extension (g: Game)
    def maxRedDraw: Int   = g.draws.maxBy(_.red).red
    def maxGreenDraw: Int = g.draws.maxBy(_.green).green
    def maxBlueDraw: Int  = g.draws.maxBy(_.blue).blue

@main
def solve() =
  val input = utils.readInput("day02/input.txt")
  println(s"Part 1: ${part1(input)}")
  println(s"Part 2: ${part2(input)}")

def part1(input: List[String]): Int =
  val games = input.map(Game.fromString)

  val totalRed   = 12
  val totalGreen = 13
  val totalBlue  = 14

  games
    .foldLeft(0): (acc, g) =>
      if g.maxRedDraw <= totalRed && g.maxGreenDraw <= totalGreen && g.maxBlueDraw <= totalBlue then acc + g.id
      else acc

def part2(input: List[String]): Int =
  val games = input.map(Game.fromString)

  games
    .foldLeft(0): (acc, g) =>
      acc + g.maxRedDraw * g.maxBlueDraw * g.maxGreenDraw
