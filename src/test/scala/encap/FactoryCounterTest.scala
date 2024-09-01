package encaptest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import encap._

class FactoryCounterTest extends AnyFlatSpec with ChiselScalatestTester {
    "FactoryCounterTest" should "pass" in {
        test(new FactoryCounter(15)) { factoryCounter =>
            factoryCounter.io.en.poke(true.B)
            factoryCounter.io.out.expect(16)

            for(i <- 16 until 0 by -1) {
                factoryCounter.io.en.poke(true.B)
                factoryCounter.clock.step()
            }

            factoryCounter.io.out.expect(0)
        }
    }
}