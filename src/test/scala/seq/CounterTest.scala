package collectiontest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import seq._

class CounterTest extends AnyFlatSpec
    with ChiselScalatestTester {
    "Counter" should "pass" in {
        test(new Counter(16)) {
            counter =>
            counter.io.enable.poke(true.B)
            counter.io.out.expect(0)
            counter.clock.step()

            counter.io.enable.poke(true.B)
            counter.io.out.expect(1)
            counter.clock.step()

            counter.io.enable.poke(false.B)
            counter.io.out.expect(2)
            counter.clock.step()

            counter.io.enable.poke(true.B)
            counter.io.out.expect(2)
            counter.clock.step()

            counter.io.enable.poke(true.B)
            counter.io.out.expect(3)
            counter.clock.step()
        }
    }
}