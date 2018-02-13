package nl.biopet.tools

object Package {
  def main(args: Array[String]): Unit = {

  }

  def allTools: List[ToolCommand[_]] = fastqTools ::: bamTools ::: vcfTools ::: restTools

  def vcfTools: List[ToolCommand[_]] = List(

  )

  def bamTools: List[ToolCommand[_]] = List(

  )

  def fastqTools: List[ToolCommand[_]] = List(

  )

  def restTools: List[ToolCommand] = List(

  )
}
