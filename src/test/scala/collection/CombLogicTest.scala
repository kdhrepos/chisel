package collectiontest

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

import collection._

// using for loop for test
class CombLogicTest extends AnyFlatSpec
    with ChiselScalatestTester {
    "CombLogicTest" should "pass" in {
        test(new CombLogic) { dut =>
            for (a <- Seq(true, false)) {
                for (b <- Seq(true, false)) {
                    for (c <- Seq(true, false)) {
                        dut.io.a.poke(a.B)
                        dut.io.b.poke(b.B)
                        dut.io.c.poke(c.B)
                        println(s"Results: $a, $b, $c \n")
                        val expected = (a && b) || !c
                        dut.io.out.expect(expected.B)
             }
        }
    }
}
    }
}