package arbittest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import arbit._

class UtilArbTest extends AnyFlatSpec with ChiselScalatestTester {
    val numPorts = 4
    "UtilArb" should "pass" in {
        test(new UtilArb(numPorts, 8)) { c =>
            c.io.out.ready.poke(true.B)
            for (cycle <- 0 until 5) {
                for (p <- 0 until numPorts) {
                    c.io.req(p).bits.poke(p.U)
                    c.io.req(p).valid.poke((p >= cycle).B)
                }
                c.clock.step()
            }
        }
    }
}