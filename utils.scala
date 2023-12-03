package utils

def readInput(fileName: String): List[String] =
  scala.util.Using(scala.io.Source.fromFile(fileName))(_.getLines.toList).get // just throw if we can't read
