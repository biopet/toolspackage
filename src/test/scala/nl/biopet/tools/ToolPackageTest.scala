/*
 * Copyright (c) 2018 Biopet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nl.biopet.tools

import nl.biopet.utils.test.tools.ToolTest
import nl.biopet.utils.tool.ToolCommand
import org.testng.annotations.{DataProvider, Test}

import scala.io.Source

class ToolPackageTest extends ToolTest[Args] {
  def toolCommand: Executable.type = Executable
  @Test
  def testNoArgs(): Unit = {
    Executable.main(Array())
  }

  @Test
  def testUnique(): Unit = {
    assert(Executable.allTools
             .map(_.toolName)
             .distinct
             .lengthCompare(Executable.allTools.length) == 0,
           "Duplicate tools detected")
  }

  @DataProvider(name = "tools")
  def toolsProvider(): Array[Array[String]] = {
    Executable.allTools.map(t => Array(t.toolName)).toArray
  }

  @Test(dataProvider = "tools")
  def testEmptyVersions(toolName: String): Unit = {
    val tool = Executable.allTools
      .find(_.toolName == toolName)
      .getOrElse(throw new IllegalStateException("This is not possible"))
    assert(Executable.versions(tool).isDefined,
           s"Tool $toolName is missing version")
  }

  @Test(dataProvider = "tools")
  def testTestReleaseDocs(toolName: String): Unit = {
    val tool = Executable.allTools
      .find(_.toolName == toolName)
      .getOrElse(throw new IllegalStateException("This is not possible"))
    val url =
      s"https://biopet.github.io/${tool.urlToolName}/${Executable.versions(tool).getOrElse("Unknown")}/index.html"
    Source.fromURL(url) // this return an exception when a 404 error is returned
  }

  @Test(dataProvider = "tools")
  def testTestDevelopDocs(toolName: String): Unit = {
    val tool = Executable.allTools
      .find(_.toolName == toolName)
      .getOrElse(throw new IllegalStateException("This is not possible"))
    val url = s"https://biopet.github.io/${tool.urlToolName}/develop/index.html"
    Source.fromURL(url) // this return an exception when a 404 error is returned
  }
}
