package collection

import chisel3._
import chisel3.util._

// N read, 1 write
class MemRegFile(nRead: Int) extends Module {
    val io = IO(new Bundle {
        val raddr  = Input(Vec(nRead, UInt(5.W)))
        val w0addr = Input(UInt(5.W))
        val w0en =   Input(Bool())
        val w0data = Input(UInt(64.W))
        val rout = Output(Vec(nRead, UInt(64.W)))
    })

    val regs = Mem(32, UInt(64.W))

    for (i <- 0 until nRead) {
        io.rout(i) := regs(io.raddr(i))
    }

    when(io.w0en){
        regs(io.w0addr) := io.w0data
    }
}