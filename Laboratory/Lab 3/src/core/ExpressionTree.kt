package core

import java.util.ArrayDeque
import kotlin.math.pow

@Suppress("UNCHECKED_CAST") // like # type: ignore in python
class ExpressionTree<T> {
    // create class Node with Generic Types
    class Node<T>(val value: T) {
        var left: Node<T>? = null
        var right: Node<T>? = null
    }

    // build expression tree method (Generic Type)
    fun build(postfix: List<T>): Node<T>? {
        val stack = ArrayDeque<Node<T>>()
        for (t in postfix) { // loop every token (characters, any be tokens)
            // change t to string and check it is digits
            if (t.toString().all { it.isDigit() }) stack.push(Node(t))
            else { // if not (must be operators)
                val operator = Node(t) // create node for operators
                operator.right = stack.pop() // pop first numbers in the stack and put it to right side of operators
                operator.left = stack.pop() // pop second numbers in the stack and put it to left side of operators

                stack.push(operator) // push operators node back to stack (should be (n_1) <- [op] -> [n_2])
            }
        }

        return stack.pop() // pop operator node from stack
    }
}

fun inOrder(expressionNode: ExpressionTree.Node<String>?) {
    if (expressionNode == null) return
    inOrder(expressionNode.left)
    print("${expressionNode.value} ")
    inOrder(expressionNode.right)
}
fun preOrder(expressionNode: ExpressionTree.Node<String>?) {
    if (expressionNode == null) return
    print("${expressionNode.value} ")
    preOrder(expressionNode.left)
    preOrder(expressionNode.right)
}
fun postOrder(expressionNode: ExpressionTree.Node<String>?) {
    if (expressionNode == null) return
    postOrder(expressionNode.left)
    postOrder(expressionNode.right)
    print("${expressionNode.value} ")
}

fun evaluation(expressionNode: ExpressionTree.Node<String>?): Double {
    val stack = ArrayDeque<Double>()

    // same as postfixOrder
    val postfix = mutableListOf<String>()
    fun traverse(current: ExpressionTree.Node<String>?) {
        if (current == null) return
        traverse(current.left)
        traverse(current.right)
        postfix.add(current.value)

    }
    traverse(expressionNode)

    for (t in postfix) { // loop every token (character/string) of postfix List
        // if that token is digit
        if (t.all { it.isDigit() }) stack.push(t.toDouble())
        else {
            val rightOperand = stack.pop()
            val leftOperand = stack.pop()

            val r = when (t) {
                "+" -> leftOperand + rightOperand
                "-" -> leftOperand - rightOperand
                "*" -> leftOperand * rightOperand
                "/" -> leftOperand / rightOperand
                "^" -> leftOperand.pow(rightOperand)
                else -> throw IllegalArgumentException("Unknown operator: $t")
            }
            stack.push(r) // push back the result
        }
    }
    return stack.pop() // pop it result
}