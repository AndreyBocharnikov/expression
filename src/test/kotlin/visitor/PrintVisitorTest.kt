package visitor

import BaseTest
import org.junit.jupiter.api.Test
import tokenizer.Tokenizer
import java.io.StringWriter
import kotlin.test.assertEquals

class PrintVisitorTest : BaseTest() {
    private fun printTest(src: String, expected: String) {
        val tokenizer = Tokenizer(src)
        val sw = StringWriter()
        PrintVisitor(sw).visitAll(tokenizer.tokenize())
        assertEquals(expected, sw.toString().trim())
    }

    @Test
    fun number() = printTest("1488", "NUMBER(1488)")

    @Test
    fun add() = printTest("15 + 13", "NUMBER(15) NUMBER(13) ADD")

    @Test
    fun complexExpression() = printTest(
        "(10 / 5 - 1 + 3 * 3) + 2 / 1",
        "NUMBER(10) NUMBER(5) DIV NUMBER(1) SUB NUMBER(3) NUMBER(3) MUL ADD NUMBER(2) NUMBER(1) DIV ADD"
    )

    @Test
    fun orderExpression() = printTest(
        "5 - (4 * 2 + 1) * 2 - 3 / 1",
        "NUMBER(5) NUMBER(4) NUMBER(2) MUL NUMBER(1) ADD NUMBER(2) MUL SUB NUMBER(3) NUMBER(1) DIV SUB"
    )
}