package visitor

import BaseTest
import org.junit.jupiter.api.Test
import tokenizer.Tokenizer
import kotlin.test.assertEquals

class ParserVisitorTest : BaseTest() {

    private fun revNotationTest(src: String, expected: String) {
        val tokenizer = Tokenizer(src)
        assertEquals(
            expected,
            showTokens(
                ParserVisitor().visitAll(tokenizer.tokenize())
            )
        )
    }

    @Test
    fun addTest() = revNotationTest("12 + 21", "NUMBER(12) NUMBER(21) ADD")

    @Test
    fun complexTest() = revNotationTest(
        "2 * 3 - 8 / 2 + 15",
        "NUMBER(2) NUMBER(3) MUL NUMBER(8) NUMBER(2) DIV SUB NUMBER(15) ADD"
    )

    @Test
    fun bracesTest() = revNotationTest(
        "(2 + 2) * 4",
        "NUMBER(2) NUMBER(2) ADD NUMBER(4) MUL"
    )
}