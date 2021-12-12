package tokenizer

import visitor.BaseVisitor

sealed class Token {
    abstract fun accept(visitor: BaseVisitor<*>)
}

sealed class ShowableToken(private val view: String): Token() {
    fun show(): String = view
}

class NumberToken(val value: Int): ShowableToken("NUMBER($value)") {
    override fun accept(visitor: BaseVisitor<*>) = visitor.visit(this)
}

sealed class BraceToken(view: String): ShowableToken(view) {
    override fun accept(visitor: BaseVisitor<*>) = visitor.visit(this)
}
object LEFT : BraceToken("LEFT")
object RIGHT : BraceToken("RIGHT")

sealed class OperationToken(view: String): ShowableToken(view) {
    override fun accept(visitor: BaseVisitor<*>) = visitor.visit(this)
}
object ADD : OperationToken("ADD")
object SUB : OperationToken("SUB")
object MUL : OperationToken("MUL")
object DIV : OperationToken("DIV")
