package repos

import scala.collection.mutable.ListBuffer
import services.BlockchairService
import models.Address

object AddressesRepo {
    private val blockchairService = new BlockchairService()
    private var addresses: ListBuffer[Address] = ListBuffer(
        Address("3E8ociqZa9mZUSwGdSmAEMAoAxBK3FNDcd"), 
        Address("bc1q0sg9rdst255gtldsmcf8rk0764avqy2h2ksqs5"), 
        Address("bc1qm34lsc65zpw79lxes69zkqmk6ee3ewf0j77s3h"), 
        Address("12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju"))
    
    def list(): List[Address] = addresses.toList

    def add(address: Address): List[Address] = {
        this.addresses += address
        addresses.toList
    }

    def delete(address: String): List[Address] = {
        this.addresses = addresses.filter(_.address != address)
        addresses.toList
    }

    def syncBalances(): List[Address] = {
        this.addresses = addresses.map(address => {
            blockchairService.syncBalance(address)
        })
        addresses.toList
    }
}
