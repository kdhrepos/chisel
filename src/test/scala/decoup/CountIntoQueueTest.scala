package decouptest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import decoup._

class CountIntoQueueTest extends AnyFlatSpec with ChiselScalatestTester {
    "CountIntoQueueTest" should "pass" in {
        test(new CountIntoQueue(4, 3, pipe=false, flow=false)) { tester =>
            tester.io.en.poke(true.B)
            tester.io.out.ready.poke(false.B)
            for (cycle <- 0 until 4) { // Fill up queue
                println(s"f count:${tester.io.count.peek()} out:${tester.io.out.bits.peek()} v:${tester.io.out.valid.peek()}")
                tester.clock.step()
            }
            println()
            tester.io.en.poke(false.B)
            tester.io.out.ready.poke(true.B)
            for (cycle <- 0 until 4) { // Drain the queue
                println(s"d count:${tester.io.count.peek()} out:${tester.io.out.bits.peek()} v:${tester.io.out.valid.peek()}")
                tester.clock.step()
            }
            println()
            tester.io.en.poke(true.B)
            for (cycle <- 0 until 4) { // Simultaneous
                println(s"s count:${tester.io.count.peek()} out:${tester.io.out.bits.peek()} v:${tester.io.out.valid.peek()}")
                tester.clock.step()
            }
        }
    }
}