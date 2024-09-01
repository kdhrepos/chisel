package decouptest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import decoup._

class CountWhenReadyTest extends AnyFlatSpec with ChiselScalatestTester { 
    "CountWhenReadyTest" should "pass" in {
        test(new CountWhenReady(3)) { countWhenReady =>
            countWhenReady.io.en.poke(true.B)
            for (cycle <- 0 until 7) {
                countWhenReady.io.out.ready.poke((cycle % 2 == 1).B)
                println(s"cycle: $cycle count: ${countWhenReady.io.out.bits.peek()}")
                countWhenReady.clock.step()
            }
        }
    }
}