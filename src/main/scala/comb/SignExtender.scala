import chisel3._
import chisel3.util._

class SignExtender(inputWidth: Int, outputWidth : Int) extends Module {
    val io = IO(new Bundle {
        val in= Input(UInt(inputWidth.W))
        val out = Output(UInt(outputWidth.W))
    })

    assert(inputWidth > 0, "inputWidth should be positive value")
    assert(inputWidth < outputWidth, "outputWidth should have wider width than input width")

    val signBit = io.in(inputWidth - 1)
    val signExtension = Fill(outputWidth - inputWidth, signBit)
    io.out := Cat(signExtension, io.in)
}