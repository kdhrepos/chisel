import chisel3._
import chiseltest._
import chisel3.util._
import org.scalatest.flatspec.AnyFlatSpec

import collection._

class RegFileTest extends AnyFlatSpec with ChiselScalatestTester {
    "RegFileTest" should "pass" in {
        test(new RegFile()){ regFile =>
            // write
            regFile.io.w0en.poke(true.B)
            regFile.io.w0addr.poke(0)
            regFile.io.w0data.poke(1)
            regFile.clock.step()

            regFile.io.r0addr.poke(0)
            regFile.io.r1addr.poke(1)
            // regFile.clock.step()

            regFile.io.r0out.expect(1)
            regFile.io.r1out.expect(0)
            // regFile.clock.step()
        }
    }
}