package collection

import chisel3._
import chisel3.util._

// 2 read, 1 write
class RegFile() extends Module {
    val io = IO(new Bundle {
        val r0addr = Input(UInt(5.W)) // 5bit for 2^5 = 32 registers
        val r1addr = Input(UInt(5.W))
        val w0addr = Input(UInt(5.W))
        val w0en =   Input(Bool())
        val w0data = Input(UInt(64.W))
        val r0out =  Output(UInt(64.W))
        val r1out =  Output(UInt(64.W))
    })    

    val regs = Reg(Vec(32, UInt(64.W)))

    io.r0out := regs(io.r0addr)
    io.r1out := regs(io.r1addr)

    when(io.w0en) {
        regs(io.w0addr) := io.w0data
    }

}