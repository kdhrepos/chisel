import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import seq._

class NerdCounterTest extends AnyFlatSpec
    with ChiselScalatestTester {
    "NerdCounterTest" should "pass" in {
        test(new NerdCounter(2)) {
            nerdCounter =>
            nerdCounter.io.tick.expect(0)
            nerdCounter.clock.step()

            nerdCounter.io.tick.expect(1)
            nerdCounter.clock.step()
        }
    }
}