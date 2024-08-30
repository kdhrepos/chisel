package collectiontest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import collection._

// delayed shift register
class DelayNCyclesTest extends AnyFlatSpec with ChiselScalatestTester {
    "DelayNCycleTest" should "pass" in {
        test(new DelayNCycles(4)) { delayNCycles =>
            delayNCycles.io.in.poke(true.B)
            delayNCycles.io.out.expect(false.B)
            delayNCycles.clock.step()

            delayNCycles.clock.step()
            delayNCycles.clock.step()
            delayNCycles.clock.step()

            delayNCycles.io.out.expect(true.B)
        }
    }
}