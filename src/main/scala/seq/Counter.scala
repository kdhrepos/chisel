import chisel3._
import chisel3.util._

class Counter(maxValue: Int) extends Module {
    val io = IO(new Bundle {
        val enable = Input(Bool())
        val out = Output(UInt())
    })

    val count = RegInit(0.U(log2Ceil(maxValue+1).W))
    
    when(io.enable) {
        count := count + 1.U
    }

    when(count >= maxValue.U) {
        count := 0.U
    }

    io.out := count
}