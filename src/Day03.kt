fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val part1 = line.substring(0 until line.length / 2)
            val part2 = line.substring(line.length / 2 until line.length)
            val intersect = part1.toSet().intersect(part2.toSet()).first()
            sum += if (intersect.isLowerCase()) {
                intersect.code - 96
            } else {
                intersect.code - 38
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        var from = 0
        var to = 3
        repeat(input.size / 3) {
            val lines = input.subList(from, to)
            val intersect = lines[0].toSet().intersect(lines[1].toSet()).intersect(lines[2].toSet()).first()
            sum += if (intersect.isLowerCase()) {
                intersect.code - 96
            } else {
                intersect.code - 38
            }
            from += 3
            to += 3
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
