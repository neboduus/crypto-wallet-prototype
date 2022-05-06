package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.Transaction.transactionReads

case class TransactionsBatch(transactions: Seq[Transaction], totalRows: Long)

object TransactionsBatch {
    implicit val transactionsBatchReads: Reads[TransactionsBatch] = (
        (JsPath \ "data").read[Seq[Transaction]] and
        (JsPath \ "context" \ "total_rows").read[Long]
    )(TransactionsBatch.apply _)
}