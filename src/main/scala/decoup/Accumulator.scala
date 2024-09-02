package decoup

import chisel3._
import chisel3.util._

object Accumulator {
    def apply(width: Int, data: UInt, rst: Bool) = {
        val m = new Accumulator(width, data, rst)
        m.count
    }
}

class Accumulator(width: Int, data: UInt, rst: Bool) {
    val count = RegInit(0.U(width.W))
    
    count := count + data
    
    when(rst) {
        count := 0.U
    }
}