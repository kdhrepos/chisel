package arbittest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import arbit._

class PriEncodeOHTest extends AnyFlatSpec with ChiselScalatestTester {
    "PriEncodeOHTest" should "pass" in {
        test(new PriEncodeOH(3)) { c =>
            for (i <- 0 until 8) {
                c.io.in.poke(i.U)
                c.clock.step()
            }
        }
    }
}