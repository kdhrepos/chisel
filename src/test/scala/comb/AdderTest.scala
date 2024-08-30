import chisel3._
import chiseltest._
import comb._
import org.scalatest.flatspec.AnyFlatSpec

// n-bit adder test
class AdderTest extends AnyFlatSpec
    with ChiselScalatestTester {
    "Adder" should "pass" in {
        test(new Adder(4)) {
            adder =>
            adder.io.inputA.poke(1)
            adder.io.inputB.poke(2)
            adder.io.output.expect(3)
        }
    }
}