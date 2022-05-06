# crypto-wallet-prototype

This project contains a Scala Play application prototype for a Cryptocurrencies Wallet. 

## Run the app

### Dependencies

This project has been built with:

- [Scala](https://www.scala-lang.org/) version 2.12.13
- [SBT (Scala Build Tool)](https://www.scala-sbt.org/) version 1.3.8

Although greater versions may work, is reccommended to install the indicated versions which have been tested.

> Note: this app has been implemented using [Play Framework for Scala](https://www.playframework.com/) version 2.7.3, but you don't need to manually install it because SBT takes care of it.

### Start the server

- clone this repository and access `cointracker-wallet-prototype/cointtracker-wallet` folder
```shell
$ cd /your/location/cointracker-wallet-prototype/cointtracker-wallet
```
- start the server
```shell
$ sbt run
```

- open your browser and access http://localhost:9000/

## Project structure

We list here most important components of this implementation:

- `build.sbt` - contains project dependencies
- `/conf/routes` - server routes
- `/controllers/HomeController` - main controller that handles requests from front end
- `/models` - data structures used (`Address`, `Transaction`, `TransactionBatch`)
- `/views` - templates handling front end
  - `main.scala.html` - layout
  - `index.scala.html` - home page which lists addresses
  - `transactions.scala.html` - page that presents transactions of a specific address  
- `/services/BlockchairService.scala` - service responsible with the interaction with a third-party API named [Blockchair](https://blockchair.com/)
- `/repos/AddressesRepo` - repository that stores and retrieves `Address` from a local store
- `/test` - tests