package decouptest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import decoup._

class ValidReceiverTest extends AnyFlatSpec with ChiselScalatestTester { 
    "ValidReceiverTest" should "pass" in {
        test(new ValidReceiver(4)) { validReceiver => 
            for (cycle <- 0 until 8) {
                validReceiver.io.in.bits.poke(cycle.U)
                println(s"cycle: $cycle")
                validReceiver.io.in.valid.poke((cycle % 2 == 0).B)
                validReceiver.clock.step()
            }
        }
    }
}