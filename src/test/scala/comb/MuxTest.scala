import chisel3._
import chiseltest._
import comb._
import org.scalatest.flatspec.AnyFlatSpec

class MuxTest extends AnyFlatSpec 
    with ChiselScalatestTester {
    "Mux" should "pass" in {
        test(new Mux(8)) { mux => 
            mux.io.sel.poke(true.B)
            mux.io.inputA.poke(1)
            mux.io.inputB.poke(2)
            mux.io.output.expect(1)

            mux.io.sel.poke(false.B)
            mux.io.inputA.poke(1)
            mux.io.inputB.poke(2)
            mux.io.output.expect(2)
        }
    }
}