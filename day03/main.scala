//> using file ../utils.scala

package day03

case class Cell(row: Int, col: Int):
  def neighbors: Seq[Cell] =
    for
      r <- row - 1 to row + 1
      c <- col - 1 to col + 1
      if !(r == row && c == col)
    yield Cell(r, c)

case class Symbol(cell: Cell, value: Char)

case class Number(startCell: Cell, value: Int):
  def cells: Seq[Cell] =
    val magnitude = value.toString.length
    (startCell.col to startCell.col + magnitude - 1).map(Cell(startCell.row, _))

// set of symbols and a map of cells belonging to a number to that number
case class Schematic(symbols: Set[Symbol], numbers: Map[Cell, Number])

object Schematic:
  def fromRows(rows: List[String]): Schematic =
    val (symbols, numbers) = rows.zipWithIndex.foldLeft((Set.empty[Symbol], Map.empty[Cell, Number])):
      case ((symbols, numbers), (row, idx)) =>
        val (newSymbols, newNumbers) = parseRow(idx, row.zipWithIndex.toList)
        (symbols ++ newSymbols, numbers ++ newNumbers)

    Schematic(symbols, numbers)

  def parseRow(
      rowIdx: Int,
      row: List[(Char, Int)],
      symbols: Set[Symbol] = Set.empty,
      numbers: Map[Cell, Number] = Map.empty
  ): (Set[Symbol], Map[Cell, Number]) =
    row match
      case Nil              => (symbols, numbers)
      case ('.', _) :: tail => parseRow(rowIdx, tail, symbols, numbers)
      case (h, idx) :: tail if h.isDigit =>
        val digits    = row.map(_._1).takeWhile(_.isDigit)
        val number    = Number(Cell(rowIdx, idx), digits.mkString.toInt)
        val remainder = row.drop(digits.length)
        parseRow(rowIdx, remainder, symbols, numbers ++ number.cells.map(_ -> number).toMap)
      case (h, idx) :: tail =>
        parseRow(rowIdx, tail, symbols + Symbol(Cell(rowIdx, idx), h), numbers)

@main
def solve() =
  val input = utils.readInput("day03/input.txt")
  println(s"Part 1: ${part1(input)}")
  println(s"Part 2: ${part2(input)}")

def part1(input: List[String]): Int =
  val schematic = Schematic.fromRows(input)

  schematic.symbols
    .flatMap: sym =>
      sym.cell.neighbors.collect:
        case n if schematic.numbers.contains(n) => schematic.numbers(n)
    .foldLeft(0): (acc, number) =>
      acc + number.value

def part2(input: List[String]): Int =
  val schematic = Schematic.fromRows(input)

  schematic.symbols.foldLeft(0): (acc, sym) =>
    val gearRatio = if sym.value == '*' then
      val numbers = sym.cell.neighbors
        .collect:
          case n if schematic.numbers.contains(n) => schematic.numbers(n)
        .distinct
      if numbers.size == 2 then numbers.head.value * numbers.last.value
      else 0
    else 0
    acc + gearRatio
