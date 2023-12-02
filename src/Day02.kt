fun main() {
    fun part1(input: List<String>): Int {
        val maxCubes = Cubes(12, 13, 14)
        return input.map { line ->
            val (i, sets) = line.mapToSets()
            val validSets = sets.map {
                it.r <= maxCubes.r && it.g <= maxCubes.g && it.b <= maxCubes.b
            }.all { it }
            i to validSets
        }
            .filter { it.second }
            .sumOf { it.first }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val (_, sets) = line.mapToSets()
            val power = sets.maxBy { it.r }.r * sets.maxBy { it.g }.g * sets.maxBy { it.b }.b
            power
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

private fun String.mapToSets(): Pair<Int, List<Cubes>> {
    val (i, sets) = substringAfter("Game ").split(":")
    val cubesSets = sets.split(";").map { gameSet ->
        gameSet.split(",").map {
            Cubes(
                it.trim().substringBefore(" red", "0").toInt(),
                it.trim().substringBefore(" green", "0").toInt(),
                it.trim().substringBefore(" blue", "0").toInt()
            )
        }.reduce { total, newCubes ->
            Cubes(
                total.r + newCubes.r,
                total.g + newCubes.g,
                total.b + newCubes.b,
            )
        }
    }
    return i.toInt() to cubesSets
}

private data class Cubes(
    val r: Int,
    val g: Int,
    val b: Int
)