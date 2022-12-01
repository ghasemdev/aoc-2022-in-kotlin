import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var max = Int.MIN_VALUE
        var temp = 0
        input.forEachIndexed { index, calorie ->
            if (calorie.isNotEmpty()) {
                temp += calorie.toInt()
            } else {
                max = max(max, temp)
                temp = 0
            }
            if (index == input.size - 1) {
                max = max(max, temp)
                temp = 0
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        val totalCalories = mutableListOf<Int>()
        var temp = 0
        input.forEachIndexed { index, calorie ->
            if (calorie.isNotEmpty()) {
                temp += calorie.toInt()
            } else {
                totalCalories.add(temp)
                temp = 0
            }
            if (index == input.size - 1) {
                totalCalories.add(temp)
                temp = 0
            }
        }
        return totalCalories.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24_000)
    check(part2(testInput) == 45_000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
