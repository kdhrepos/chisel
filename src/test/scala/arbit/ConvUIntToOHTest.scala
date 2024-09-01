package arbittest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import arbit._

class ConvUIntToOHTest extends AnyFlatSpec with ChiselScalatestTester {
    "CombLogicTest" should "pass" in {
        test(new ConvUIntToOH(2)) { c =>
           for (i <- 0 until 4) {
                c.io.in.poke(i.U)
                c.io.out.expect((1 << i).U)
                c.clock.step()
            }
        }
    }
}