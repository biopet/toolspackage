organization := "com.github.biopet"
organizationName := "Biopet"

startYear := Some(2018)

name := "ToolsPackage"
biopetUrlName := "toolspackage"

biopetIsTool := true

mainClass in assembly := Some("nl.biopet.tools.Executable")

developers := List(
  Developer(id = "ffinfo",
            name = "Peter van 't Hof",
            email = "pjrvanthof@gmail.com",
            url = url("https://github.com/ffinfo")))

scalaVersion := "2.11.11"

libraryDependencies += "com.github.biopet" %% "tool-test-utils" % "0.2.1"

libraryDependencies += "com.github.biopet" %% "addgenesandtranscriptstogtf" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "annotatevcfwithbed" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "bamstats" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "basecounter" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "bastygeneratefasta" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "correctrefalleles" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "countalleles" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "digenicsearch" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "downloadncbiassembly" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "downsampleregions" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "extractadaptersfastqc" % "0.2-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "extractalignedfastq" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "extracttagsfromgtf" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "fastqsplitter" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "fastqsync" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "findoverlapmatch" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "gtftorefflat" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "gvcftobed" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "krakenreporttojson" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "mergesv" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "mergeotumaps" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "mpileuptovcf" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "multicoverage" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "ncbireporttocontigmap" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "pipelinestatus" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "rebuildcontigmap" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "refflatstats" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "replacecontigsgtffile" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "replacecontigsvcffile" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "samplestsvtoconfig" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "snptesttovcf" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "seqstat" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "squishbed" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "validateannotation" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "validatefastq" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "validatevcf" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "vcffilter" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "vcfstats" % "1.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "vcftotsv" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "vcfwithvcf" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "vepnormalizer" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "wipereads" % "0.1-SNAPSHOT" changing ()
libraryDependencies += "com.github.biopet" %% "xcnvtobed" % "0.1-SNAPSHOT" changing ()
