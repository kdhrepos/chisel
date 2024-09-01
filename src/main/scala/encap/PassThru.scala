// package encap

// import chisel3._
// import chisel3.util._

// class HandShake(w: Int) extends Bundle {
//     val ready = Input(Bool())
//     val data = Output(UInt(w.W))
// }

// class PassThru(w: Int) extends Module {
//     val io = IO(new Bundle {
//         val in = Flipped(HandShake(w))
//         val out = new Handshake(w)
//     })
//     io.in <> io.out
// }