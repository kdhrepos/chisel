import chisel3._
import chiseltest._
import chisel3.util._
import org.scalatest.flatspec.AnyFlatSpec

class MemRegFileTest extends AnyFlatSpec with ChiselScalatestTester {
    "MemRegFileTest" should "pass" in {
        test(new MemRegFile(4)){ memRegFile =>
            // write
            for(i <- 0 until 4) {
                memRegFile.io.w0en.poke(true.B)
                memRegFile.io.w0addr.poke(i.U)
                memRegFile.io.w0data.poke(i.U)
                memRegFile.clock.step()
            }

            memRegFile.io.raddr(1).poke(1)
            // memRegFile.io.r1addr.poke(1)
            // memRegFile.clock.step()

            memRegFile.io.rout(1).expect(1)
            // memRegFile.io.r1out.expect(0)
            // memRegFile.clock.step()
        }
    }
}