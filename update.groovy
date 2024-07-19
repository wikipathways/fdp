date2 = "2024-07-10"
date = date2.replaceAll("-", "")

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

datasetFolder = new File("dataset/${date}")
if (!datasetFolder.exists()) datasetFolder.mkdir()

// RDF file
rdfFolder = new File("dataset/${date}/rdf")
if (!rdfFolder.exists()) rdfFolder.mkdir()
rdfFile = new File("dataset/${date}/rdf/index.ttl")
rdfFile.text = prefixTemplate + "\n" + rdfFile

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
