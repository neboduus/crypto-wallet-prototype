package controllers

import javax.inject._
import javax.inject.Inject
import play.api._
import play.api.Logger
import play.api.mvc._
import play.api.routing._
import play.api.http.MimeTypes
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Failure}

import models.Address
import services.BlockchairService
import repos.AddressesRepo

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) (implicit ec: ExecutionContext)
  extends BaseController {


  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index.render(AddressesRepo.list()))
  }


  def add(address: String) = Action { implicit request: Request[AnyContent] =>
    println("adding")
    AddressesRepo.add(Address(address))
    Ok(views.html.index.render(AddressesRepo.list()))
  }

  def delete(address: String) = Action { implicit request: Request[AnyContent] =>
    println("deleting")
    AddressesRepo.delete(address)
    Ok(views.html.index.render(AddressesRepo.list()))
  }

  def sync() = Action { implicit request: Request[AnyContent] =>
    println("syncing")
    AddressesRepo.syncBalances()
    Ok(views.html.index.render(AddressesRepo.list()))
  }

  def transactions(address: String, offset: String) = Action { implicit request: Request[AnyContent] =>
    println("getting transactions")
    val blockchairService = new BlockchairService()
    val transactions = blockchairService.getTransactions(Address(address), offset.toLong)
    Ok(views.html.transactions.render(Address(address), transactions, offset.toLong))
  }

  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.HomeController.index,
        routes.javascript.HomeController.add,
        routes.javascript.HomeController.sync,
        routes.javascript.HomeController.delete,
        routes.javascript.HomeController.transactions,
      )
    ).as(MimeTypes.JAVASCRIPT)
  }
}
