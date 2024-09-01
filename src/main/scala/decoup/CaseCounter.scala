package decoup

import chisel3._
import chisel3.util._

case class CounterParams(limit: Int, start: Int = 0) {
    val width = log2Ceil(limit + 1)
}

class CaseCounter(cp: CounterParams) extends Module {
    val io = IO(new Bundle {
        val en = Input(Bool())
        val out = Output(UInt(cp.width.W))
    })

    val count = RegInit(cp.start.U(cp.width.W))
    when (io.en) {
        when (count < cp.limit.U) {
            count := count + 1.U
        } .otherwise {
            count := cp.start.U
        }
    }
    io.out := count
}