import chisel3._
import chiseltest._
import chisel3.util._
import org.scalatest.flatspec.AnyFlatSpec

import collection._

class FwdMemTest extends AnyFlatSpec with ChiselScalatestTester {
    "FwdMemTest" should "pass" in {
        test(new FwdMem()){ memRegFile =>
            memRegFile.io.rdAddr.poke(1)
            memRegFile.io.rdData.expect(0)
            memRegFile.clock.step()

            // write
            memRegFile.io.wrEna.poke(true.B)
            memRegFile.io.wrAddr.poke(1.U)
            memRegFile.io.wrData.poke(1.U)
            
            // read at the same as wrAddr -> forward
            memRegFile.io.rdAddr.poke(1)
            memRegFile.io.rdData.expect(1)
            memRegFile.clock.step()
        }
    }
}