package decouptest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import decoup._

class CaseCounterTest extends AnyFlatSpec with ChiselScalatestTester {
    "CaseCounterTest" should "pass" in {
        test(new CaseCounter(new CounterParams(15))) { caseCounter =>
            caseCounter.io.en.poke(true.B)
            caseCounter.io.out.expect(0)

            for(i <- 0 until 15) {
                // caseCounter.io.en.poke(true.B)
                caseCounter.clock.step()
            }

            caseCounter.io.out.expect(15)
        }
    }
}