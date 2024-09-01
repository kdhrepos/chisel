package arbit

import chisel3._
import chisel3.util._

class PriEncodeOH(n: Int) extends Module {
    val io = IO(new Bundle {
        val in  = Input(UInt(n.W))
        val out = Output(UInt())
    })
    require (n > 0)

    def withGates(idx: Int, expr: UInt): UInt = {
        if (idx < (n-1)) Cat(withGates(idx+1, ~io.in(idx) & expr), io.in(idx) & expr)
        else io.in(idx) & expr
    }

    def withMuxes(idx: Int): UInt = {
        if(idx < n) Mux(io.in(idx), (1 << idx).U, withMuxes(idx+1))
        else 0.U
    }

    // io.out := withGates(0, 1.U)
    io.out := withMuxes(0)
    printf("%b -> %b\n", io.in, io.out)
}