package encap

import chisel3._
import chisel3.util._

class PairWithOption(w: Int, hasY: Boolean) extends Bundle {
    val x = Output(UInt(w.W))
    val y: Option[UInt] = if (hasY) Some(Output(UInt(w.W))) else None
}

class OptionBundle(w: Int, a: Int, useY: Boolean) extends Module {
    val io = IO(Output(new PairWithOption(w, useY)))
    io.x := a.U
    if (useY)
        io.y.get := a.U
}