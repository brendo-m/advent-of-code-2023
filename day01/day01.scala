package day01

@main
def solve() =
  val input = readInput("day01/input.txt")
  println(s"Part 1: ${part1(input)}")
  println(s"Part 2: ${part2(input)}")

def part1(input: List[String]): Int =
  solve(input) { line =>
    line.headOption.flatMap(x => if x.isDigit then Some(x.asDigit) else None)
  }

def part2(input: List[String]): Int =
  val digitMap = Map(
    "one"   -> 1,
    "two"   -> 2,
    "three" -> 3,
    "four"  -> 4,
    "five"  -> 5,
    "six"   -> 6,
    "seven" -> 7,
    "eight" -> 8,
    "nine"  -> 9
  )

  solve(input) { line =>
    line.headOption.flatMap:
      case x if x.isDigit => Some(x.asDigit)
      case _ =>
        digitMap.collectFirst:
          case (word, digit) if line.startsWith(word) => digit
  }

def solve(lines: List[String])(f: String => Option[Int]): Int =
  lines
    .foldLeft(0): (acc, line) =>
      val digits = line.tails.flatMap(f).toList
      acc + digits.head * 10 + digits.last

def readInput(fileName: String): List[String] =
  scala.util.Using(scala.io.Source.fromFile(fileName))(_.getLines.toList).get // just throw if we can't read
