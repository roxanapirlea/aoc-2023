import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (i in input.indices) {
            var number = ""
            var start = -1
            var end: Int
            for (j in input[i].indices) {
                if (input[i][j].isDigit()) {
                    number = number.plus(input[i][j])
                    if (start == -1) start = j
                    if (j == input[i].lastIndex) {
                        var isValid = start > 0 && input[i][start - 1] != '.'

                        for (k in max(start - 1, 0)..input[i].lastIndex) {
                            if (
                                (i > 0 && input[i - 1][k] != '.' && !input[i - 1][k].isDigit()) ||
                                (i < input.size - 1 && input[i + 1][k] != '.' && !input[i + 1][k].isDigit())
                            )
                                isValid = true
                        }
                        if (isValid && number.isNotEmpty()) sum += number.toInt()

                        start = -1
                        number = ""
                    }
                } else {
                    end = j - 1
                    var isValid = (start > 0 && input[i][start - 1] != '.') || (end < input.size - 1 && input[i][end + 1] != '.')

                    for (k in max(start - 1, 0)..min(end + 1, input[i].lastIndex)) {
                        if (
                            (i > 0 && input[i - 1][k] != '.' && !input[i - 1][k].isDigit()) ||
                            (i < input.size - 1 && input[i + 1][k] != '.' && !input[i + 1][k].isDigit())
                        )
                            isValid = true
                    }
                    if (isValid && number.isNotEmpty()) sum += number.toInt()

                    start = -1
                    number = ""
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        val possibleGears = mutableListOf<Pair<Pair<Int,Int>, Int>>()
        for (i in input.indices) {
            var number = ""
            var start = -1
            var end: Int
            for (j in input[i].indices) {
                if (input[i][j].isDigit()) {
                    number = number.plus(input[i][j])
                    if (start == -1) start = j
                    if (j == input[i].lastIndex) {
                        if (start > 0 && input[i][start - 1] == '*')
                            possibleGears.add(Pair(i, start -1) to number.toInt())

                        for (k in max(start - 1, 0)..input[i].lastIndex) {
                            if (i > 0 && input[i - 1][k] == '*')
                                possibleGears.add(Pair(i - 1, k) to number.toInt())
                            if (i < input.size - 1 && input[i + 1][k] == '*')
                                possibleGears.add(Pair(i + 1, k) to number.toInt())
                        }

                        start = -1
                        number = ""
                    }
                } else {
                    end = j - 1
                    if (start > 0 && input[i][start - 1] == '*' && number.isNotEmpty())
                        possibleGears.add(Pair(i, start -1) to number.toInt())
                    if (end < input[i].lastIndex && input[i][end + 1] == '*' && number.isNotEmpty())
                        possibleGears.add(Pair(i, end + 1) to number.toInt())

                    for (k in max(start - 1, 0)..min(end + 1, input[i].lastIndex)) {
                        if (i > 0 && input[i - 1][k] == '*' && number.isNotEmpty())
                            possibleGears.add(Pair(i - 1, k) to number.toInt())
                        if (i < input.size - 1 && input[i + 1][k] == '*' && number.isNotEmpty())
                            possibleGears.add(Pair(i + 1, k) to number.toInt())
                    }

                    start = -1
                    number = ""
                }
            }
        }
        return possibleGears
            .groupBy { it.first }
            .mapValues { (_, v) -> v.map { it.second.toLong() } }
            .filter { (_, v) -> v.size == 2 }
            .mapValues { (_, v) -> v.reduce { acc, i -> acc * i } }
            .values
            .reduce { acc, l -> acc + l }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835L)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}