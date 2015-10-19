/**
 * Copyright 2015 Yahoo Inc. Licensed under the Apache License, Version 2.0
 * See accompanying LICENSE file.
 */

package controllers

import javax.inject.{Singleton, _}
import features.ApplicationFeatures
import play.api.mvc._

/**
 * @author hiral
 */
@Singleton
class Application extends Controller {

  import play.api.libs.concurrent.Execution.Implicits.defaultContext

  private[this] val kafkaManager = KafkaManagerContext.getKafkaManager

  private[this] implicit val af: ApplicationFeatures = ApplicationFeatures.features

  def index = Action.async {
    kafkaManager.getClusterList.map { errorOrClusterList =>
      Ok(views.html.index(errorOrClusterList))
    }
  }
}
