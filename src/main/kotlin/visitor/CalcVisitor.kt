package visitor

import tokenizer.*
import java.util.*

class CalcVisitor : BaseVisitor<Int> {
    private val stack = ArrayDeque<Int>()

    private fun binOp(op: (Int, Int) -> Int) {
        if (stack.size < 2) {
            error("Binary operation has to have at least 2 tokens.")
        }
        val r = stack.pop()
        val l = stack.pop()
        stack.push(op(l, r))
    }

    override fun visit(token: NumberToken) {
        stack.push(token.value)
    }

    override fun visit(token: BraceToken) {}

    override fun visit(token: OperationToken) {
        when (token) {
            ADD -> binOp(Int::plus)
            SUB -> binOp(Int::minus)
            MUL -> binOp(Int::times)
            DIV -> binOp(Int::div)
        }
    }

    override fun visitAll(tokens: List<Token>): Int {
        ParserVisitor().visitAll(tokens).forEach { t -> t.accept(this) }
        if (stack.size == 1) {
            return stack.pop()
        } else {
            error("Incorrect tokens sequence!")
        }
    }
}