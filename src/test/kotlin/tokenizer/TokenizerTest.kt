package tokenizer

import BaseTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TokenizerTest : BaseTest() {

    private fun showTest(src: String, expected: String) {
        val tokenizer = Tokenizer(src)
        assertEquals(expected, showTokens(tokenizer.tokenize()))
    }

    @Test
    fun add() = showTest("12 + 21", "NUMBER(12) ADD NUMBER(21)")

    @Test
    fun mul() = showTest("2 * 2", "NUMBER(2) MUL NUMBER(2)")

    @Test
    fun brace() = showTest(
        "(5 - 3) * 2",
        "LEFT NUMBER(5) SUB NUMBER(3) RIGHT MUL NUMBER(2)"
    )

    @Test
    fun complexTest() = showTest(
        "(10 / 2 + 2 + 1 / 1 - 5) * (2 - 1)",
    "LEFT NUMBER(10) DIV NUMBER(2) ADD NUMBER(2) ADD NUMBER(1) DIV NUMBER(1) SUB NUMBER(5) RIGHT MUL" +
            " LEFT NUMBER(2) SUB NUMBER(1) RIGHT"
    )
}