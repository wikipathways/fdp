import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

calendar = Calendar.getInstance();
year = calendar.get(Calendar.YEAR)
month = calendar.get(Calendar.MONTH) + 1 // January = 0
day = calendar.get(Calendar.DAY_OF_MONTH)
if (day < 10) month += -1
if (month < 10) month = "0" + month
date2 = "${year}-${month}-10"
date = date2.replaceAll("-", "")
localTime = LocalDateTime.now()
isotime = localTime.atZone(ZoneId.systemDefault()).format(
  DateTimeFormatter.ISO_INSTANT
)

prefixTemplate = new File("prefixes.template").text
rdfTemplate = new File("rdf.template").text
  .replaceAll("%%DATE%%", date)
gmtTemplate = new File("gmt.template").text
  .replaceAll("%%DATE%%", date)
gpmlTemplate = new File("gpml.template").text
  .replaceAll("%%DATE%%", date)
datasetTemplate = new File("dataset.template").text
  .replaceAll("%%DATE%%", date)
catalogTemplate = new File("catalog.template").text
  .replaceAll("%%DATE%%", date).replaceAll("%%DATE2%%", date2)
fdpTemplate = new File("fdp.template").text
  .replaceAll("%%DATE%%", date).replaceAll("%%DATE2%%", date2)
  .replaceAll("%%ISOTIME%%", isotime)

datasetFolder = new File("dataset/${date}")
if (!datasetFolder.exists()) datasetFolder.mkdir()

// RDF file
rdfFolder = new File("dataset/${date}/rdf")
if (!rdfFolder.exists()) rdfFolder.mkdir()
rdfFile = new File("dataset/${date}/rdf/index.ttl")
rdfFile.text = prefixTemplate + "\n" + rdfTemplate

// GMT file
gmtFolder = new File("dataset/${date}/gmt")
if (!gmtFolder.exists()) gmtFolder.mkdir()
gmtFile = new File("dataset/${date}/gmt/index.ttl")
gmtFile.text = prefixTemplate + "\n" + gmtTemplate

// GPML file
gpmlFolder = new File("dataset/${date}/gpml")
if (!gpmlFolder.exists()) gpmlFolder.mkdir()
gpmlFile = new File("dataset/${date}/gpml/index.ttl")
gpmlFile.text = prefixTemplate + "\n" + gpmlTemplate

// Dataset file
datasetFile = new File("dataset/${date}/index.ttl")
datasetFile.text = prefixTemplate + "\n" + datasetTemplate + "\n" +
  rdfTemplate + "\n" + gpmlTemplate + "\n" + gmtTemplate

// Catalog file
catalogFile = new File("catalog/index.ttl")
catalogFile.text = prefixTemplate + "\n" + catalogTemplate + "\n" +
  datasetTemplate + "\n" + rdfTemplate + "\n" + gpmlTemplate + "\n" + gmtTemplate

// FDP file
fdpFile = new File("index.ttl")
fdpFile.text = prefixTemplate + "\n" + fdpTemplate + "\n" +
  catalogTemplate + "\n" + datasetTemplate + "\n" +
  rdfTemplate + "\n" + gpmlTemplate + "\n" + gmtTemplate
