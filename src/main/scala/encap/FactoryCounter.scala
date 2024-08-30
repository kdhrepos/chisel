package encap

import chisel3._
import chisel3.util._

class FactoryCounter(maxVal: Int) extends Module {
    val io = IO(new Bundle {
        val en = Input(Bool())
        val out = Output(UInt())
    })
    val count = RegInit(0.U(log2Ceil(maxVal + 1).W))

    when(io.en) {
        when (count < maxVal.U) {
            count := count + 1.U
        } .otherwise {
            count := 0.U
        }
    }

    io.out := count
}

// object FactoryCounter {
//     def apply(maxVal: Int) = new FactoryCounter(maxVal)
// }