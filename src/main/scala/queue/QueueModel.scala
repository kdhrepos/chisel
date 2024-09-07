package queue

import chisel3._
import chisel3.util._

class QueueModel(numEntries: Int, pipe: Boolean=true) {
    val mq = scala.collection.mutable.Queue[Int]()

    var deqReady = false  // set externally
    def deqValid() = mq.nonEmpty
    // be sure to call attemptDeq before attemptEnq within a cycle
    def attemptDeq() = if (deqReady && deqValid) Some(mq.dequeue()) else None
    
    def enqReady() = mq.size < numEntries-1 || 
                    (mq.size == numEntries-1 && !deqReady) ||
                    (mq.size == numEntries-1 && deqReady && pipe)
    def attemptEnq(elem: Int): Unit = if (enqReady()) mq += elem    // implies enqValid
}

def simCycle(qm: QueueModel, c: SimpleQueue, enqValid: Boolean, deqReady: Boolean, enqData: Int=0) {
    qm.deqReady = deqReady
    c.io.deq.ready.poke(qm.deqReady.B)
    c.io.deq.valid.expect(qm.deqValid.B)
    val deqResult = qm.attemptDeq()
    if (deqResult.isDefined)
        c.io.deq.bits.expect(deqResult.get.U)
    c.io.enq.ready.expect(qm.enqReady.B)
    c.io.enq.valid.poke(enqValid.B)
    c.io.enq.bits.poke(enqData.U)
    if (enqValid)
        qm.attemptEnq(enqData)
    c.clock.step()
    println(qm.mq)
}