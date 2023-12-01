fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            line.first { it.isDigit() }.digitToInt() * 10 + line.last { it.isDigit() }.digitToInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val first = Digit.entries
                .map { digit -> line.indexOf(digit.representation) to digit.intVal }
                .filter { it.first >= 0 }
                .minBy { it.first }
            val last =  Digit.entries
                .map { digit -> line.lastIndexOf(digit.representation) to digit.intVal }
                .maxBy { it.first }
            first.second * 10 + last.second
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInput2 = readInput("Day01_test_part2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private enum class Digit(val representation: String, val intVal: Int) {
    ZeroL("zero", 0),
    ZeroD("0", 0),
    OneL("one", 1),
    OneD("1", 1),
    TwoL("two", 2),
    TwoD("2", 2),
    ThreeL("three", 3),
    ThreeD("3", 3),
    FourL("four", 4),
    FourD("4", 4),
    FiveL("five", 5),
    FiveD("5", 5),
    SixL("six", 6),
    SixD("6", 6),
    SevenL("seven", 7),
    SevenD("7", 7),
    EightL("eight", 8),
    EightD("8", 8),
    NineL("nine", 9),
    NineD("9", 9),
}
