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

import nl.biopet.tools.addgenesandtranscriptstogtf.AddGenesAndTranscriptsToGtf
import nl.biopet.tools.annotatevcfwithbed.AnnotateVcfWithBed
import nl.biopet.tools.bamstats.BamStats
import nl.biopet.tools.basecounter.BaseCounter
import nl.biopet.tools.bastygeneratefasta.BastyGenerateFasta
import nl.biopet.tools.correctrefalleles.CorrectRefAlleles
import nl.biopet.tools.countalleles.CountAlleles
import nl.biopet.tools.digenicsearch.DigenicSearch
import nl.biopet.tools.downloadncbiassembly.DownloadNcbiAssembly
import nl.biopet.tools.downsampleregions.DownsampleRegions
import nl.biopet.tools.extractadaptersfastqc.ExtractAdaptersFastqc
import nl.biopet.tools.extractalignedfastq.ExtractAlignedFastq
import nl.biopet.tools.extracttagsfromgtf.ExtractTagsFromGtf
import nl.biopet.tools.fastqsplitter.FastqSplitter
import nl.biopet.tools.fastqsync.FastqSync
import nl.biopet.tools.findoverlapmatch.FindOverlapMatch
import nl.biopet.tools.gtftorefflat.GtftoRefflat
import nl.biopet.tools.gvcftobed.GvcfToBed
import nl.biopet.tools.krakenreporttojson.KrakenReportToJson
import nl.biopet.tools.mergeotumaps.MergeOtuMaps
import nl.biopet.tools.mergesv.MergeSv
import nl.biopet.tools.mpileuptovcf.MpileupToVcf
import nl.biopet.tools.multicoverage.MultiCoverage
import nl.biopet.tools.ncbireporttocontigmap.NcbiReportToContigMap
import nl.biopet.tools.pipelinestatus.PipelineStatus
import nl.biopet.tools.rebuildcontigmap.RebuildContigMap
import nl.biopet.tools.refflatsstats.RefflatStats
import nl.biopet.tools.replacecontigsgtffile.ReplaceContigsGtfFile
import nl.biopet.tools.replacecontigsvcffile.ReplaceContigsVcfFile
import nl.biopet.tools.sampleconfig.SampleConfig
import nl.biopet.tools.sampletsvtoconfig.SamplesTsvToConfig
import nl.biopet.tools.seqstat.SeqStat
import nl.biopet.tools.snptesttovcf.SnptestToVcf
import nl.biopet.tools.squishbed.SquishBed
import nl.biopet.tools.validateannotation.ValidateAnnotation
import nl.biopet.tools.validatefastq.ValidateFastq
import nl.biopet.tools.validatevcf.ValidateVcf
import nl.biopet.tools.vcffilter.VcfFilter
import nl.biopet.tools.vcfstats.VcfStats
import nl.biopet.tools.vcftotsv.VcfToTsv
import nl.biopet.tools.vcfwithvcf.VcfWithVcf
import nl.biopet.tools.vepnormalizer.VepNormalizer
import nl.biopet.tools.wipereads.WipeReads
import nl.biopet.tools.xcnvtobed.XcnvToBed
import nl.biopet.utils.tool.ToolCommand
import nl.biopet.utils.Documentation.htmlTable
import nl.biopet.utils.io.resourceToFile

import scala.io.Source

object Executable extends ToolCommand[Args] {

  override def urlToolName: String = "toolspackage"

  def main(args: Array[String]): Unit = {
    val cmdArgs = cmdArrayToArgs(args)

    cmdArgs.toolName.map(_.toLowerCase) match {
      case Some(toolName) =>
        allTools.find(_.toolName.toLowerCase == toolName) match {
          case Some(tool) => tool.main(cmdArgs.toolArgs)
          case _ =>
            logger.error(s"Tool '$toolName' not found")
            printToolList()
        }
      case _ => printToolList()
    }
  }

  def printToolList(): Unit = {
    argsParser.usage.split("\n").foreach(logger.info)
    logger.info("")
    printToolList("Vcf tools", vcfTools)
    printToolList("Bam tools", bamTools)
    printToolList("Fastq tools", fastqTools)
    printToolList("Annotation tools", annotationTools)
    printToolList("Other tools", otherTools)
  }

  def printToolList(title: String, tools: List[ToolCommand[_]]): Unit = {
    logger.info(s"** $title **")
    tools
      .map(_.toolName)
      .grouped(6)
      .map(x => x.mkString(", "))
      .foreach(logger.info)
    logger.info("")
  }

  def allTools: List[ToolCommand[_]] =
    fastqTools ::: bamTools ::: vcfTools ::: annotationTools ::: otherTools

  def vcfTools: List[ToolCommand[_]] = List(
    VcfStats,
    VcfFilter,
    VcfToTsv,
    VcfWithVcf,
    VepNormalizer,
    ValidateVcf,
    SnptestToVcf,
    MergeSv,
    MpileupToVcf,
    ReplaceContigsVcfFile,
    GvcfToBed,
    FindOverlapMatch,
    DigenicSearch,
    CountAlleles,
    CorrectRefAlleles,
    AnnotateVcfWithBed
  )

  def bamTools: List[ToolCommand[_]] = List(
    WipeReads,
    MultiCoverage,
    ExtractAlignedFastq,
    DownsampleRegions,
    BamStats
  )

  def fastqTools: List[ToolCommand[_]] = List(
    ValidateFastq,
    SeqStat,
    FastqSync,
    FastqSplitter,
    ExtractAdaptersFastqc
  )

  def annotationTools: List[ToolCommand[_]] = List(
    ValidateAnnotation,
    BaseCounter,
    GtftoRefflat,
    ReplaceContigsGtfFile,
    RefflatStats,
    ExtractTagsFromGtf,
    AddGenesAndTranscriptsToGtf
  )

  def otherTools: List[ToolCommand[_]] = List(
    XcnvToBed,
    SquishBed,
    SampleConfig,
    SamplesTsvToConfig,
    RebuildContigMap,
    PipelineStatus,
    NcbiReportToContigMap,
    MergeOtuMaps,
    KrakenReportToJson,
    DownloadNcbiAssembly,
    BastyGenerateFasta
  )

  val versions: Map[ToolCommand[_], Option[String]] = {
    val source =
      getClass.getResourceAsStream("/biopet_toolspackage_libraries.tsv")
    val versionMap = Source
      .fromInputStream(source)
      .getLines()
      .map { line =>
        val values = line.split("\t")
        require(values.size == 2)
        values(0).toLowerCase -> values(1)
      }
      .toMap
    allTools.map(x => x -> versionMap.get(x.toolName.toLowerCase)).toMap
  }

  def argsParser = new ArgsParser(this)

  def emptyArgs: Args = Args()

  def descriptionText: String =
    """
      |This package does combine all biopet tools into a single jar for easy access.
      |All tools can still be used as separated jars if required, this is just to make it easier for you as user.
    """.stripMargin

  def manualText: String = {

    val rows = toolHtmlColumns("Vcf tools", vcfTools) :::
      toolHtmlColumns("Bam tools", bamTools) :::
      toolHtmlColumns("Fatq tools", fastqTools) :::
      toolHtmlColumns("Annotation tools", annotationTools) :::
      toolHtmlColumns("Other tools", otherTools)

    s"""
       |
       |${htmlTable(List("Tool name", "Version", "Documentation"), rows)}
       |
    """.stripMargin
  }

  def toolHtmlColumns(title: String,
                      tools: List[ToolCommand[_]]): List[List[String]] = {
    List(s"<b>$title</b>", "", "") :: tools.map { t =>
      val version = versions(t).getOrElse("Unknown")
      List(
        t.toolName,
        version,
        s"""<a href="https://biopet.github.io/${t.urlToolName}/$version">Release</a> / <a href="https://biopet.github.io/${t.urlToolName}/develop">Develop</a>"""
      )
    } ::: List(List("", "", ""))
  }

  def exampleText: String =
    s"""
      |Get a list of tools:
      |${example()}
      |
      |Normal tools:
      |${example("<tool_name>", "<arguments to tool>")}
      |
      |Spark tools:
      |${sparkExample("<tool_name>", "<arguments to tool>")}
    """.stripMargin
}
