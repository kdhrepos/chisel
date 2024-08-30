package collectiontest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import seq._

class TickTest extends AnyFlatSpec
    with ChiselScalatestTester {
    "Tick" should "pass" in {
        test(new Tick(3)) {
            tick =>
            tick.io.out.expect(0)
            tick.clock.step()

            tick.io.out.expect(0)
            tick.clock.step()

            tick.io.out.expect(0)
            tick.clock.step()

            tick.io.out.expect(1)
            tick.clock.step()

            tick.io.out.expect(1)
            tick.clock.step()
        }
    }
}