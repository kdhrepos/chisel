package decouptest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import decoup._

class AccumulatorInstMod(width: Int) extends Module {
    val io = IO(new Bundle {
        val data  = Input(UInt(width.W))
        val rst   = Input(Bool())
        val count = Output(UInt(width.W))
    })
    io.count := Accumulator(width, io.data, io.rst)
}

class AccumulatorTest extends AnyFlatSpec with ChiselScalatestTester {
    "AccumulatorTest" should "pass" in {
        test(new AccumulatorInstMod(4)) { dut =>
            dut.io.data.poke(5.U)
            dut.io.rst.poke(false.B)
            dut.io.count.expect(0.U)
            dut.clock.step()
            
            dut.io.data.poke(6.U)
            dut.io.rst.poke(false.B)
            dut.io.count.expect(5.U)
            dut.clock.step()
            
            dut.io.data.poke(7.U)
            dut.io.rst.poke(false.B)
            dut.io.count.expect(11.U)
            dut.clock.step()
            
            dut.io.data.poke(0.U)
            dut.io.rst.poke(true.B)
            dut.io.count.expect(2.U)
            dut.clock.step()
            
            dut.io.count.expect(0.U)
        }
    }
}