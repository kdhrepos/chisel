package encap

import chisel3._
import chisel3.util._

// delayed shift register with recursive function
class DelayNCycles(N: Int) extends Module {
    val io = IO(new Bundle {
        val in = Input(Bool())
        val out = Output(Bool())
    })
    require(N >= 0)

    def helper(N: Int, lastConn: Bool): Bool = {
        if (N == 0) lastConn
        else helper(N - 1, RegNext(lastConn))
    }

    io.out := helper(N, io.in)
}
