package ichigo

import twitter4j._
import twitter4j.conf.ConfigurationBuilder

object TwitterStreamer {

  val twitterConf = {
    val conf = new ConfigurationBuilder()
      .setOAuthConsumerKey("Your costumer Token ")
      .setOAuthConsumerSecret("Your costumer Token secret")
      .setOAuthAccessToken("Your access token")
      .setOAuthAccessTokenSecret("Your access token secret")
      .build
    conf
  }


  class TwitterListener(call_back: Status => Unit) extends StatusListener { // Create status listener to prevent errors stream from stopping

    def onStatus(data: Status): Unit = call_back(data)

    def onException(ex: Exception): Unit = {
      ex.printStackTrace
    }

    def onScrubGeo(arg0: Long, arg1: Long): Unit = {}
    def onStallWarning(warning: StallWarning): Unit = {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) : Unit ={}
  }


  val getStream = new TwitterStreamFactory(twitterConf).getInstance

}
