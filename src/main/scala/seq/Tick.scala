package seq

import chisel3._
import chisel3.util._

// Use tick for slow count, not clock
class Tick(N: Int) extends Module {
    val io = IO(new Bundle {
        val out = Output(UInt(4.W))
    })

    val tickCntReg = RegInit(0.U(32.W))
    val tick = tickCntReg === (N-1).U  
    tickCntReg := tickCntReg + 1.U

    val lowHzCntReg = RegInit(0.U(4.W))

    printf("Current tick is %d\n", tickCntReg)
    when(tick){
        tickCntReg := 0.U
        lowHzCntReg := lowHzCntReg + 1.U
    }

    io.out := lowHzCntReg
}