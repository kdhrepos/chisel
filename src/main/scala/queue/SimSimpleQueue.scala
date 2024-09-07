package queue

import chisel3._
import chisel3.util._

// Simultaneous Enq/Deq when full
class SimSimpleQueue(numEntries: Int, bitWidth: Int) 
    extends SimpleQueue(numEntries, bitWidth) {
    require(numEntries > 1)
    require(isPow2(numEntries))

    val entries = Reg(Vec(numEntries, UInt(bitWidth.W)))
    val enqIdx = RegInit(0.U(log2Ceil(numEntries).W))
    val deqIdx = RegInit(0.U(log2Ceil(numEntries).W))
    val maybeFull = RegInit(false.B)

    val empty = enqIdx === deqIdx && !maybeFull
    val full = enqIdx === deqIdx && maybeFull

    io.enq.ready := !full || io.deq.ready // simulataneous enqueue & dequeue is possible
                                          // even if queue is full
    io.deq.valid := !empty
    io.deq.bits := entries(deqIdx)

    when (io.deq.fire) {
        deqIdx := deqIdx +% 1.U
        when (enqIdx =/= deqIdx) {
            maybeFull := false.B
        }
    }

    when (io.enq.fire) {
        entries(enqIdx) := io.enq.bits
        enqIdx := enqIdx +% 1.U
        when ((enqIdx +% 1.U) === deqIdx) {
            maybeFull := true.B
        }
    }
}