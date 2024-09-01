package decoup

import chisel3._
import chisel3.util._

class CountWhenReady(maxVal: Int) extends Module {
    val io = IO(new Bundle {
        val en  = Input(Bool())
        val out = Decoupled(UInt())
        /* Decoupled
            fire - Bool that is true if and only if ready & valid
            enq(data) - Sends data and sets valid to true (doesn't check ready)
            noenq - Sets valid to false
            deq/nodeq - Like enq/noenq for receiver 
        */
    })
    val (count, wrap) = Counter(io.out.fire, maxVal)
    when (io.en) {
        io.out.enq(count)
        // io.out.bits := count
        // io.out.valid := true.B
    } .otherwise {
        io.out.noenq()
        // io.out.bits := DontCare
        // io.out.valid := false.B
    }
}