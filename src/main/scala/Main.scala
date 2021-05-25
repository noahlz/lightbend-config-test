import com.typesafe.config.{ Config, ConfigFactory }

object Main extends App {
  import TestCases._

  println("starting up...")
  println("reading config...")

  val config = ConfigFactory.load()
  testRelative(config)
  testAbsolute(config)

  println("Done.")
}

object TestCases {
  def printResult(name: String, disabled: Boolean) {
    println(s"""|
                |******************************
                |Testing: $name
                |
                |app is disabled? $disabled
                |
                |******************************
                |""".stripMargin)
  }

  def testRelative(config: Config) {
    val component = config.getConfig("app.component")
    val disabled = component.getBoolean("disabled")
    printResult("relative", disabled)
  }

  def testAbsolute(config: Config) {
    val disabled = config.getBoolean("app.component.disabled")
    printResult("absolute", disabled)
  }
}
