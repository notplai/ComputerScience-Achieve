import core.ExpressionTree
import core.evaluation
import core.inOrder
import core.postOrder
import core.preOrder
import precedence
import java.util.ArrayDeque

// Operator precedence map
val precedence = mapOf(
    "+" to 1,
    "-" to 1,
    "*" to 2,
    "/" to 2,
    "^" to 3
)

// Shunting-yard algorithm
fun in2Post(s: String): List<String> {
    val postfix = mutableListOf<String>()
    val operators = ArrayDeque<String>()

    // Tokenize numbers and operators/parentheses
    // d+ is digits matches
    // | is OR
    // [] are list matches +, - * / ()
    // - must have \- because normal '-' is to in regex
    val tokens = Regex("""\d+|[+\-*/()^]""").findAll(s).map { it.value }.toList()

    for (t in tokens) {
        when {
            // token is digits
            t.all { it.isDigit() } -> postfix.add(t)

            // token is ()
            t == "(" -> operators.push(t)
            t == ")" -> {
                while (operators.isNotEmpty() && operators.peek() != "(") postfix.add(operators.pop())
                if (operators.isNotEmpty() && operators.peek() == "(") operators.pop() // Discard '('
            }

            // token is operator
            t in precedence -> {
                while (
                    operators.isNotEmpty() &&
                    operators.peek() != "(" && // it redundant cause it should haven't "("
                    (precedence[operators.peek()] ?: 0) >= (precedence[t] ?: 0)
                ) postfix.add(operators.pop())

                operators.push(t)
            }
        }
    }
    // all operators in stack must throws in postfix before return
    while (operators.isNotEmpty()) {
        val op = operators.pop()
        if (op != "(") postfix.add(op)
    }

    return postfix
}

fun main() {
    // just loop every file in "example/" directory
    val dir = "example"
    for (file in java.io.File(dir).listFiles() ?: emptyArray()) {
        if (file.isFile) file.forEachLine {
            val content = it.trim()
            if (content.isNotEmpty()) {
                val expressionNode = ExpressionTree<String>().build(in2Post(content))

                preOrder(expressionNode) // cannot use to calculate
                println()
                inOrder(expressionNode)
                println()
                postOrder(expressionNode)
                println()
                println("Evaluation: ${evaluation(expressionNode)}")
            }
        }
    }
}