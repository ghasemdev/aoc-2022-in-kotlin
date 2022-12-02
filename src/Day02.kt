import RoundAction.*

private enum class Action(private val score: Int) {
    Rock(1), Paper(2), Scissors(3);

    infix fun compare(action: Action): Int = when {
        this == action -> Draw.score
        this == Rock && action == Scissors -> Win.score
        this == Scissors && action == Paper -> Win.score
        this == Paper && action == Rock -> Win.score
        else -> Lose.score
    } + score

    infix fun compare(action: RoundAction): Int = action.score + when (this) {
        Rock -> when (action) {
            Lose -> Scissors.score
            Win -> Paper.score
            Draw -> Rock.score
        }
        Paper -> when (action) {
            Lose -> Rock.score
            Win -> Scissors.score
            Draw -> Paper.score
        }
        Scissors -> when (action) {
            Lose -> Paper.score
            Win -> Rock.score
            Draw -> Scissors.score
        }
    }

    companion object {
        fun from(value: String): Action = when (value) {
            "A", "X" -> Rock
            "B", "Y" -> Paper
            "C", "Z" -> Scissors
            else -> throw IllegalStateException("Unknown action")
        }
    }
}

private enum class RoundAction(val score: Int) {
    Lose(0), Win(6), Draw(3);

    companion object {
        fun from(value: String): RoundAction = when (value) {
            "X" -> Lose
            "Y" -> Draw
            "Z" -> Win
            else -> throw IllegalStateException("Unknown round action")
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            val (player1, player2) = line
                .split(" ")
                .map { Action.from(it) }
            result += player2 compare player1
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            val (player1, action) = run {
                val actions = line.split(" ")
                Action.from(actions.first()) to RoundAction.from(actions.last())
            }

            result += player1 compare action
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
