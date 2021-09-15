package guice.modules

import models.Order
import com.google.inject.name.Names
import com.google.inject.{AbstractModule, Provides}
import play.api.{Configuration, Environment}
import guice.services.{
  BusinessOrderValidationService,
  EnterpriseOrderValidationService,
  OrderPipelineProcessor,
  OrderService,
  OrderValidationService
}

import javax.inject.Singleton

class OrderModule(environment: Environment, configuration: Configuration)
  extends AbstractModule {

  override def configure(): Unit = {

    bind(classOf[OrderValidationService])
      .annotatedWith(Names.named("Business"))
      .toInstance(new BusinessOrderValidationService)

    bind(classOf[OrderValidationService])
      .annotatedWith(Names.named("Enterprise"))
      .toInstance(new EnterpriseOrderValidationService)

    bind(classOf[OrderService])
      .in(classOf[Singleton])

  }

  @Provides
  def orderPipelineProcessors(): Seq[OrderPipelineProcessor] =
    Seq(
      (order: Order) => println("Processor 1 processed"),
      (order: Order) => println("Processor 2 processed"),
      (order: Order) => println("Processor 3 processed"),
      (order: Order) => println("Processor 4 processed")
    )
}
