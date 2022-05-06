package services

import sttp.client3._
import play.api.libs.json._
import models.{Address, TransactionsBatch}
import models.TransactionsBatch.transactionsBatchReads

class BlockchairService {
    private val baseUrl = "https://api.blockchair.com"
    private val backend = HttpURLConnectionBackend()
    private val transactionsBatchSize = 50

    def syncBalance(address: Address): Address = {
        val balanceUri = uri"$baseUrl/bitcoin/addresses/balances/?addresses=${address.address}"
        val request = basicRequest.get(balanceUri)
        val response = request.send(backend)
        response.body match {
            case Right(balanceResp) => {
                val balance = parseBalance(balanceResp.toString, address.address)
                Address(address.address, balance)
            }
            case Left(failure) => address
        }
    }

    def parseBalance(balanceJson: String, address: String) = {
        (Json.parse(balanceJson) \ "data" \ address)
            .getOrElse(JsString("-1"))
            .asOpt[Long]
    }

    def getTransactions(address: Address, offset: Long): TransactionsBatch = {
        val transactionsUri = uri"$baseUrl/bitcoin/transactions?limit=${transactionsBatchSize.toString}&offset=${offset.toString}"
        val request = basicRequest.get(transactionsUri)
        val response = request.send(backend)
        response.body match {
            case Right(transactionsResp) => parseTransactions(transactionsResp.toString)
            case Left(_) => TransactionsBatch(List(), 0)
        }
    }

    def parseTransactions(transactionsJson: String): TransactionsBatch = {
        Json.parse(transactionsJson).validate[TransactionsBatch] match {
            case JsSuccess(transactionsBatch, _) => transactionsBatch
            case JsError(_) => TransactionsBatch(List(), 0)
        }
    }
}