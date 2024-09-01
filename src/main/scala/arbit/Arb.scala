// package arbit

// import chisel3._
// import chisel3.util._

// class Arb(numPorts: Int, w: Int) extends Module {
//     val io = IO(new Bundle{
//         val req = Flipped(Vec(numPorts, Decoupled(UInt(w.W))))
//         val out = Decoupled(UInt(w.W))
//     })
//     require (numPorts > 0)
//     val inValids = Wire(Vec(numPorts, Bool()))
//     val inBits = Wire(Vec(numPorts, UInt(w.W)))
//     for (p <- 0 until numPorts) {
//         io.req(p).ready := false.B
//         inValids(p) := io.req(p).valid
//         inBits(p) := io.req(p).bits
//     }
//     val chosenOH = PriorityEncoderOH(inValids)
//     io.out.valid := inValids.asUInt.orR
//     io.out.bits := Mux1H(chosenOH, inBits)
//     val chosen := OHToUInt(chosenOH)
//     when (io.out.fire) { 
//         io.req(chosen).ready := true.B
//     }
// }