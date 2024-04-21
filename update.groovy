date2 = "2024-02-10"
date = date2.replaceAll("-", "")

prefixTemplate = new File("prefixes.template").text
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
  gpmlTemplate + "\n" + gmtTemplate

// Catalog file
catalogFile = new File("catalog/index.ttl")
catalogFile.text = prefixTemplate + "\n" + catalogTemplate + "\n" +
  datasetTemplate + "\n" + gpmlTemplate + "\n" + gmtTemplate

// FDP file
fdpFile = new File("index.ttl")
fdpFile.text = prefixTemplate + "\n" + fdpTemplate + "\n" +
  catalogTemplate + "\n" +
  datasetTemplate + "\n" + gpmlTemplate + "\n" + gmtTemplate
