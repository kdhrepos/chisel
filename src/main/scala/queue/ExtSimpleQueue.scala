package queue

import chisel3._
import chisel3.util._

class ExtSimpleQueue(numEntries: Int, bitWidth: Int) extends SimpleQueue(numEntries, bitWidth) {
    require(numEntries > 0)
    require(bitWidth > 0)
    val entries = Seq.fill(numEntries)(Reg(UInt(bitWidth.W)))
    val fullBits = Seq.fill(numEntries)(RegInit(false.B))
    val shiftDown = io.deq.fire || !fullBits.head

    io.enq.ready := !fullBits.last || shiftDown
    io.deq.valid := fullBits.head
    io.deq.bits := entries.head

    when(shiftDown) {
        for(i <- 0 until numEntries-1) {
            entries(i) := entries(i+1)
            fullBits(i) := fullBits(i+1)
        }
        fullBits.last := false.B
    }

    when(io.enq.fire) {
        entries.last := io.enq.bits
        fullBits.last := true.B
    }
}