package decoup

import chisel3._
import chisel3.util._

class ValidReceiver(w: Int) extends Module {
    val io = IO(new Bundle {
        val in = Flipped(Valid(UInt(w.W)))
    })
    when (io.in.valid) {
        printf("Received %d\n", io.in.bits)
    }
}