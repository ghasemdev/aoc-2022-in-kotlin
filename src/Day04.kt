private infix fun ClosedRange<Int>.contains(other: ClosedRange<Int>) =
    start >= other.start && endInclusive <= other.endInclusive

private infix fun ClosedRange<Int>.overlap(other: ClosedRange<Int>) =
    start in other || endInclusive in other

fun main() {
    fun counter(input: List<String>, kFunction2: (ClosedRange<Int>, ClosedRange<Int>) -> Boolean): Int {
        var counter = 0
        for (line in input) {
            val (part1, part2) = line.split(",")
            val range1 = part1.split("-").map { it.toInt() }.run { IntRange(first(), last()) }
            val range2 = part2.split("-").map { it.toInt() }.run { IntRange(first(), last()) }
            if ((kFunction2(range1, range2)) || (kFunction2(range2, range1))) {
                counter++
            }
        }
        return counter
    }

    fun part1(input: List<String>): Int = counter(input, ClosedRange<Int>::contains)

    fun part2(input: List<String>): Int = counter(input, ClosedRange<Int>::overlap)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
