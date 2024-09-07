package queue

import chisel3._
import chisel3.util._

class CirSimpleQueue(numEntries: Int, bitWidth: Int) 
    extends SimpleQueue(numEntries, bitWidth) {
    require(numEntries > 1)
    require(isPow2(numEntries))
    
    val entries = Reg(Vec(numEntries, UInt(bitWidth.W)))
    val enqIdx = RegInit(0.U(log2Ceil(numEntries).W))
    val deqIdx = RegInit(0.U(log2Ceil(numEntries).W))

    val empty = enqIdx === deqIdx
    val full = (enqIdx +% 1.U) === deqIdx

    io.enq.ready := !full
    io.deq.valid := !empty
    io.deq.bits := entries(deqIdx)

    when (io.deq.fire) {
        deqIdx := deqIdx +% 1.U
    }
    when (io.enq.fire) {
        entries(enqIdx) := io.enq.bits
        enqIdx := enqIdx +% 1.U
    }
}