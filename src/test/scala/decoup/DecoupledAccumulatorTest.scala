// package decouptest

// import chisel3._
// import chisel3.util._
// import chiseltest._
// import org.scalatest.flatspec.AnyFlatSpec

// import decoup._

// class DecoupledAccumulatorInstMod(width: Int, coolDown: Int) extends Module {
//     val io = IO(new Bundle {
//         val data  = Flipped(Decoupled(UInt(width.W)))
//         val rst   = Input(Bool())
//         val count = Output(UInt(width.W))
//     })
//     io.count := DecoupledAccumulator(width, io.data, io.rst, coolDown)
// }

// class DecoupledAccumulatorTest extends AnyFlatSpec with ChiselScalatestTester {
//     "DecoupledAccumulatorTest" should "pass" in {
//         test(new DecoupledAccumulatorInstMod(4, 1)) { dut =>
//             dut.io.data.bits.poke(1.U) // ignored
//             dut.io.data.valid.poke(true.B) 
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(0.U)
//             dut.io.data.ready.expect(false.B)
//             dut.clock.step()

//             dut.io.data.bits.poke(2.U) // accumed
//             dut.io.data.valid.poke(true.B)
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(0.U)
//             dut.io.data.ready.expect(true.B)
//             dut.clock.step()

//             dut.io.data.bits.poke(3.U) // ignored
//             dut.io.data.valid.poke(true.B)
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(2.U)
//             dut.io.data.ready.expect(false.B)
//             dut.clock.step()

//             dut.io.data.bits.poke(4.U) // accumed
//             dut.io.data.valid.poke(true.B)
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(2.U)
//             dut.io.data.ready.expect(true.B)
//             dut.clock.step()

//             dut.io.data.bits.poke(5.U) // ignored since invalid
//             dut.io.data.valid.poke(false.B) // doesn't count
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(6.U)
//             dut.io.data.ready.expect(false.B)
//             dut.clock.step()

//             dut.io.data.bits.poke(6.U) // ignored since invalid
//             dut.io.data.valid.poke(false.B) // doesn't count
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(6.U)
//             dut.io.data.ready.expect(true.B)
//             dut.clock.step()

//             dut.io.data.bits.poke(7.U) // accumed
//             dut.io.data.valid.poke(true.B)
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(6.U)
//             dut.io.data.ready.expect(false.B)
//             dut.clock.step()

//             dut.io.count.expect(13.U)
//             dut.io.rst.poke(true.B) // reset
//             dut.clock.step()
//             dut.io.count.expect(0.U)
//         }
//         test(new DecoupledAccumulatorInstMod(4, 2)) { dut =>
//             dut.io.data.bits.poke(1.U) // ignored
//             dut.io.data.valid.poke(true.B) 
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(0.U)
//             dut.io.data.ready.expect(false.B)
//             dut.clock.step()

//             dut.io.data.bits.poke(2.U) // ignored
//             dut.io.data.valid.poke(true.B)
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(0.U)
//             dut.io.data.ready.expect(false.B)
//             dut.clock.step()

//             dut.io.data.bits.poke(3.U) // accum
//             dut.io.data.valid.poke(true.B)
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(0.U)
//             dut.io.data.ready.expect(true.B)
//             dut.clock.step()

//             dut.io.data.bits.poke(4.U) // ignored
//             dut.io.data.valid.poke(true.B)
//             dut.io.rst.poke(false.B)
//             dut.io.count.expect(3.U)
//             dut.io.data.ready.expect(false.B)
//             dut.clock.step()
//         }
//     }
// }