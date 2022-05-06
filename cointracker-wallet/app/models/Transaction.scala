package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class Transaction(hash: String, inputUsd: Double, outputUsd: Double)

object Transaction {
    implicit val transactionReads: Reads[Transaction] = (
        (JsPath \ "hash").read[String] and
        (JsPath \ "input_total_usd").read[Double] and
        (JsPath \ "output_total_usd").read[Double]
    )(Transaction.apply _)
}