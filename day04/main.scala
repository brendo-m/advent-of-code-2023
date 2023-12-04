//> using file ../utils.scala

package day04

import scala.annotation.tailrec

case class Card(id: Int, winningNumbers: List[Int], presentNumbers: List[Int])

object Card:
  // Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
  def fromString(input: String): Card =
    val Array(cardAndId, winning, present) = input.split("(:|\\|)")
    val Array(_, id)                       = cardAndId.split("\\s+")
    Card(
      id.toInt,
      winning.trim.split("\\s+").map(_.toInt).toList,
      present.trim.split("\\s+").map(_.toInt).toList
    )

@main
def solve() =
  val input = utils.readInput("day04/input.txt")
  println(s"Part 1: ${part1(input)}")
  println(s"Part 2: ${part2(input)}")

def part1(input: List[String]): Int =
  val cards = input.map(Card.fromString)
  cards
    .map: card =>
      val matches = card.winningNumbers
        .intersect(card.presentNumbers)
        .length
      math.pow(2, matches - 1).toInt
    .sum

def part2(input: List[String]): Int =
  val cardLookup = input
    .map(Card.fromString)
    .map(c => c.id -> c)
    .toMap
  play(cardLookup)

def play(cardLookup: Map[Int, Card]): Int =
  @tailrec
  def turn(id: Int, cards: Map[Int, Int]): Map[Int, Int] =
    cardLookup.get(id) match
      case None => cards
      case Some(card) =>
        val matches = card.winningNumbers
          .intersect(card.presentNumbers)
          .length
        val currCards = cards.getOrElse(id, 0)
        val newCards = (1 to matches).foldLeft(cards): (acc, n) =>
          val count = acc.getOrElse(id + n, 0)
          acc.updated(id + n, count + currCards)
        turn(id + 1, newCards)

  val initialCards = cardLookup.keys.map(_ -> 1).toMap
  turn(1, initialCards).values.sum
