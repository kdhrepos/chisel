import chisel3._
import chisel3.util._

class MuxN(n: Int, w: Int) extends Module {
    val io = IO(new Bundle {
        val in  = Input(Vec(n, UInt(w.W)))
        val sel = Input(UInt(log2Ceil(n).W))
        val out = Output(UInt(w.W))
    })
    io.out := io.in(io.sel)
}