package visitor

import org.junit.jupiter.api.Test
import tokenizer.Tokenizer
import kotlin.test.assertEquals

class CalcVisitorTest {
    private fun calcTest(src: String, expected: Int) = assertEquals(
        expected,
        CalcVisitor().visitAll(
            Tokenizer(src).tokenize()
        )
    )

    @Test
    fun number() = calcTest("228", 228)

    @Test
    fun add() = calcTest("2 + 2", 4)

    @Test
    fun sub() = calcTest("5 - 3", 2)

    @Test
    fun mul() = calcTest("1 * 1 * 10", 10)

    @Test
    fun div() = calcTest("2 / 2 / 1", 1)

    @Test
    fun bigTest() = calcTest("13 + 5 * 2 - 3 / 3 + (10 * 2) / 10 - 10 * 10", -76)

    @Test
    fun exprFromTask() = calcTest(
        "(23 + 10) * 5 - 3 * (32 + 5) * (10 - 4 *  5) + 8 / 2", 1279
    )
}