import chisel3._
import chisel3.util._

class NerdCounter(N: Int) extends Module {
    val io = IO(new Bundle {
        val tick = Output(Bool())
    })

    val MAX = (N - 2).S(8.W)
    val cntReg = RegInit(MAX)
    io.tick := false.B

    cntReg := cntReg - 1.S
    when(cntReg(7)) { // if cntReg is negative, its MSB becomes 1
        cntReg := MAX
        io.tick := true.B
    }
}