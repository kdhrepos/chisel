package fptest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import fp._

class XBarTest extends AnyFlatSpec with ChiselScalatestTester {
    "XBarTest" should "pass" in {
        var numIns = 4
        var numOuts = 2
        test(new XBar(numIns, numOuts, 8)) { dut =>
            for (ip <- 0 until numIns) {
                dut.io.in(ip).valid.poke(true.B)
                dut.io.in(ip).bits.data.poke(ip.U)
                dut.io.in(ip).bits.addr.poke((ip % numOuts).U)
            }
            for (op <- 0 until numOuts) {
                dut.io.out(op).ready.poke(true.B)
            }
            for (cycle <- 0 until 4) {
                dut.clock.step(0)
            }
        }
    }
}