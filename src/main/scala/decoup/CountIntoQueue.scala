package decoup

import chisel3._
import chisel3.util._

class CountIntoQueue(maxVal: Int, numEntries: Int, pipe: Boolean, flow: Boolean) 
    extends Module {
    val io = IO(new Bundle {
        val en  = Input(Bool())
        val out = Decoupled(UInt())
        val count = Output(UInt())
    })

    val q = Module(new Queue(UInt(), numEntries, pipe=pipe, flow=flow))
    val (count, wrap) = Counter(q.io.enq.fire, maxVal)
    
    q.io.enq.valid := io.en
    q.io.enq.bits := count
    io.out <> q.io.deq
    io.count := count // for visibility
}