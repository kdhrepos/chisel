package arbittest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import arbit._

class XBarTest extends AnyFlatSpec with ChiselScalatestTester {
    val numIns = 4
    val numOuts = 2
    "XBar" should "pass" in {
        test(new XBar(numIns,numOuts,8)) { c =>
            for (ip <- 0 until numIns) {
                c.io.in(ip).valid.poke(true.B)
                c.io.in(ip).bits.data.poke(ip.U)
                c.io.in(ip).bits.addr.poke((ip % numOuts).U)
            }
            for (op <- 0 until numOuts) {
                c.io.out(op).ready.poke(true.B)
            }
            for (cycle <- 0 until 4) {
                c.clock.step()
            }
        }
    }
}