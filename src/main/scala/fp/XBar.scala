package fp

import chisel3._
import chisel3.util._

class Message(numOuts: Int, length: Int) extends Bundle {
    val addr = UInt(log2Ceil(numOuts).W)
    val data = UInt(length.W)
}

class XBarIO(numIns: Int, numOuts: Int, length: Int) extends Bundle {
    val in  = Vec(numIns, Flipped(Decoupled(new Message(numOuts, length))))
    val out = Vec(numOuts, Decoupled(new Message(numOuts, length)))
}

class XBar(numIns: Int, numOuts: Int, length: Int) extends Module {
    val io = IO(new XBarIO(numIns, numOuts, length))
    val arbs = Seq.fill(numOuts)(Module(new RRArbiter(new Message(numOuts, length), numIns)))

    io.in.zipWithIndex.foreach{ case (in, ip) => 
        in.ready := arbs.map{ _.io.in(ip).ready }.reduce{ _ || _ }
    }

    io.out.zip(arbs).zipWithIndex.foreach{ case ((inOut, arbOut), op) => 
        arbOut.io.in.zip(io.in).foreach{ case (arbIn, ioIn) => 
            arbIn.bits <> ioIn.bits
            arbIn.valid := ioIn.valid && (ioIn.bits.addr === op.U)
        }
        inOut <> arbOut.io.out
    }

    io.out.zipWithIndex.foreach{
        case (outP, op) => printf(" %d -> %d (%b)", outP.bits.data, op.U, outP.valid)
    }
}