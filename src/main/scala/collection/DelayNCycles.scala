package collection

import chisel3._
import chisel3.util._

class DelayNCycles(N: Int) extends Module {
    val io = IO(new Bundle {
        val in = Input(Bool())
        val out = Output(Bool())
    })
    require(N > 0)

    val regs = Seq.fill(N)(Reg(Bool()))
    regs(0) := io.in

    for(i <- 1 until N) {
        regs(i) := regs(i - 1)
    }

    io.out := regs(N - 1)
}
