package collectiontest

import chisel3._
import chiseltest._
import chisel3.util._
import org.scalatest.flatspec.AnyFlatSpec

import collection._

class MuxNTest extends AnyFlatSpec with ChiselScalatestTester {
  "MuxN" should "pass" in {
    test(new MuxN(4, 4)) { muxN =>
      for (i <- 0 until 4) {
        muxN.io.in(0).poke(0.U)
        muxN.io.in(1).poke(1.U)
        muxN.io.in(2).poke(2.U)
        muxN.io.in(3).poke(3.U)
        
        muxN.io.sel.poke(i.U)
        muxN.io.out.expect(i.U)

        muxN.clock.step()
      }
    }
  }
}