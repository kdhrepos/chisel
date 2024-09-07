package queue

import chisel3._
import chisel3.util._

class OptSimpleQueue(numEntries: Int, bitWidth: Int) extends SimpleQueue(numEntries, bitWidth) {
    require(numEntries > 0)
    require(bitWidth > 0)
    
    val entries = Reg(Vec(numEntries, UInt(bitWidth.W)))
    val fullBits = RegInit(VecInit(Seq.fill(numEntries)(false.B)))
    val emptyBits = fullBits map { !_ }

    io.enq.ready := emptyBits.reduce { _ || _ }
    io.deq.valid := fullBits.head
    io.deq.bits := entries.head
    
    when (io.deq.fire) {
        fullBits.last := false.B
        for (i <- 0 until numEntries - 1) {
            entries(i) := entries(i+1)
            fullBits(i) := fullBits(i+1)
        }
    }

    when (io.enq.fire) {
        val curFreeIdx = PriorityEncoder(emptyBits)
        val writeIdx = Mux(io.deq.fire, curFreeIdx-1.U, curFreeIdx)
        entries(writeIdx) := io.enq.bits
        fullBits(writeIdx) := true.B
    }
}