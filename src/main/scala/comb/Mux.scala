package comb

import chisel3._
import chisel3.util._

class Mux(bitWidth: Int) extends Module {
    val io = IO(new Bundle{ 
        val sel = Input(Bool())
        val inputA = Input(UInt(bitWidth.W))
        val inputB = Input(UInt(bitWidth.W))
        val output = Output(UInt(bitWidth.W))
    })
    when(io.sel) {
        io.output := io.inputA
    }.otherwise {
        io.output := io.inputB
    }
}