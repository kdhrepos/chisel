// package decoup

// import chisel3._
// import chisel3.util._

// object DecoupledAccumulator {
//     def apply(width: Int, data: DecoupledIO[UInt], rst: Bool, coolDown: Int) = {
//         require(coolDown > 0)
//         val m = new DecoupledAccumulator(width, data, rst, coolDown)
//         m.count
//     }
// }

// class DecoupledAccumulator(width: Int, data: DecoupledIO[UInt], rst: Bool, coolDown: Int) {
//     val count = RegInit(0.U(width.W))
//     val coolDownCount = RegInit(0.U(width.W))
    
//     when(coolDownCount =/= coolDown.U) {
//         coolDownCount := coolDownCount + 1.U
//         data.ready := false.B
//     }.otherwise {
//         when (data.valid) {
//             count := count + data.bits
//         }
//         when(rst) {
//             count := 0.U
//             coolDownCount := 0.U
//         }
//         coolDownCount := 0.U
//         data.ready := true.B
//     }
// }