package arbit

import chisel3._
import chisel3.util._

class UtilArb(numPorts: Int, w: Int) extends Module {
    val io = IO(new Bundle{
        val req = Flipped(Vec(numPorts, Decoupled(UInt(w.W))))
        val out = Decoupled(UInt(w.W))
    })
    require (numPorts > 0)
    val arb = Module(new LockingRRArbiter(UInt(w.W), numPorts, 2))
    for (p <- 0 until numPorts) {
        arb.io.in(p) <> io.req(p)
    }

    io.out <> arb.io.out
    printf("req: ")

    for (p <- numPorts-1 to 0 by -1) {
        printf("%b", arb.io.in(p).valid)
    }
    printf(" winner: %d (v: %b)\n", arb.io.out.bits, arb.io.out.valid)
}