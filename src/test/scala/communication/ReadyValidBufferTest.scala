// package communicationtest

// import chisel3._
// import chiseltest._
// import communication._
// import org.scalatest.flatspec.AnyFlatSpec

// class ReadyValidBufferTest extends AnyFlatSpec
//     with ChiselScalatestTester {
//     "ReadyValidBufferTest" should "pass" in {
//         test(new ReadyValidBuffer()) {
//             dut =>
//             dut.io.ready.poke(true.B)
//             dut.io.bits.poke(10.U)
//             dut.io.clock.step()

//             dut.io.out.valid.expect(true.B)
//         }
//     }
// }