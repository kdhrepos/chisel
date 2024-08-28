import chisel3._
import chisel3.util._

class Adder(bitWidth: Int) extends Module {
    val io = IO(new Bundle{ 
        val inputA = Input(UInt(bitWidth.W))
        val inputB = Input(UInt(bitWidth.W))
        val output = Output(UInt(bitWidth.W))
    })
    io.output := io.inputA + io.inputB
}