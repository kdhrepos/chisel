package queue

import chisel3._
import chisel3.util._

class SimpleQueue(numEntries: Int, bitWidth: Int) extends Module {
    val io = IO(new Bundle{
        val enq = Flipped(Decoupled(UInt(bitWidth.W)))
        val deq = Decoupled(UInt(bitWidth.W))
    })
}

class SimpleQueueLogic(bitWidth: Int) extends SimpleQueue(1, bitWidth) {
    val entry = Reg(UInt(bitWidth.W))
    val full = RegInit(false.B)
    io.enq.ready := !full || io.deq.fire
    io.deq.valid := full
    io.deq.bits := entry
    when (io.deq.fire) {
        full := false.B
    }
    when (io.enq.fire) {
        entry := io.enq.bits
        full := true.B
    }
}