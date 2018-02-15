package nl.biopet.tools

import org.testng.annotations.Test

class ToolPackageTest extends ToolTest[Args] {
  def toolCommand: Executable.type = Executable
  @Test
  def testNoArgs(): Unit = {
    Executable.main(Array())
  }
}
