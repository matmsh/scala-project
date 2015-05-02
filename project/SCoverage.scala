import scoverage.ScoverageSbtPlugin.ScoverageKeys._

object SCoverage {

  val settings = Seq(
    coverageMinimum       := 50,
    coverageFailOnMinimum := false,
    coverageHighlighting  := true
  )
}
