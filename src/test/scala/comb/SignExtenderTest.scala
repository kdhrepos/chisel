import chisel3._
import chiseltest._
import comb._
import org.scalatest.flatspec.AnyFlatSpec

class SignExtenderTest extends AnyFlatSpec
    with ChiselScalatestTester {
    "SignExtender" should "pass" in {
        test(new SignExtender(4, 8)) {
            signExtender =>
            signExtender.io.in.poke("b0011".U)
            signExtender.io.out.expect("b00000011".U)
        }
    }
}