package collection

import chisel3._
import chisel3.util._

class CombLogic extends Module {
    val io = IO(new Bundle {
        val a   = Input(Bool())
        val b   = Input(Bool())
        val c   = Input(Bool())
        val out = Output(Bool())
    })
    io.out := (io.a && io.b) || ~io.c
}