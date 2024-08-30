package collection

import chisel3._
import chiseltest._
import chisel3.util._

/* 1024 entries, 32bit data
 If write and read are occur at the same time,
    read has to get the newest data by write.
 This, we forward the data.
*/
class FwdMem extends Module {
    val io = IO(new Bundle {
        val rdAddr = Input(UInt(10.W))
        val wrAddr = Input(UInt(10.W))
        val wrEna = Input(Bool())
        val wrData = Input(UInt(32.W))
        val rdData = Output(UInt(32.W))
    })

    val mem = SyncReadMem(1024, UInt(32.W))

    val memData = mem.read(io.rdAddr)
    io.rdData := memData

    when(io.wrEna){
        mem.write(io.wrAddr, io.wrData)
        io.rdData := Mux(io.rdAddr === io.wrAddr, io.wrData, memData)
    }
    // val wrDataReg = RegNext(io.wrData)
    // val doForwardReg = RegNext(io.wrAddr === io.rdAddr && io.wrEna)
    // val memData = mem.read(io.rdAddr)
    // when(io.wrEna) {
    //     mem.write(io.wrAddr , io.wrData)
    // }
    // io.rdData := Mux(doForwardReg , wrDataReg , memData)
}