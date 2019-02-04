
import ichigo.TwitterStreamer._
import twitter4j.FilterQuery

object main {

  def main(args: Array[String]): Unit = {

    val twitterStream = getStream
    twitterStream.addListener(new TwitterListener( data => print(data)))

    val KEYWORDS = Array("Orange", "ORANGE", "orange","Green", "green", "GREEN", "PURPLE", "Purple", "purple", "YELLOW",
      "Yellow", "yellow", "Red", "RED", "red", "BLUE", "Blue", "blue", "Violet", "VIOLET", "violet")

    twitterStream.filter(new FilterQuery().track(KEYWORDS)) // does filtering for keywords

  }
}
