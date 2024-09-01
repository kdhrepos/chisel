// package decoup

// import chisel3._
// import chisel3.util._


// class MakeValid(w: Int) extends Module {
//     val io = IO(new Bundle {
//         val en = Input(Bool())
//         val in = Input(UInt(w.W))
//         val out = Output(UInt(w.W))
//     })
//     io.out.valid := io.en
//     io.out.bits := io.in
// }