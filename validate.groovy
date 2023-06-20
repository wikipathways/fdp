@Grab(group='io.github.egonw.bacting', module='managers-rdf', version='0.3.2')

import org.apache.jena.shex.ShexStatus

workspaceRoot = ".."
root = "wikipathways-fdp"
rdf = new net.bioclipse.managers.RDFManager(workspaceRoot);

clazz = args[3]
file = args[2]
shape = args[0]
shapeRes = args[1]

store = rdf.createInMemoryStore(true);
store = rdf.importFile(store, "/${root}/${file}", "TURTLE")
report = rdf.validateAllOfType(
  store,
  "/${root}/shapes/${shape}",
  shapeRes,
  clazz
)

println "{"
println "  \"target\": \"${clazz}\","
println "  \"conformant\": ["
report.forEachReport { reportEntry ->
  status = reportEntry.status
  reason = reportEntry.reason
  focusNode = reportEntry.focus

  switch (status) {
    case ShexStatus.conformant :
      println "    {\n      \"class\":  \"${focusNode}\",\n      \"status\": \"${status}\"\n    },"
  }
}
println "    {}"
println "  ],"
println "  \"nonconformant\": ["
report.forEachReport { reportEntry ->
  status = reportEntry.status
  reason = reportEntry.reason
  focusNode = reportEntry.focus

  switch (status) {
    case ShexStatus.nonconformant :
      println "    {\n      \"class\":  \"${focusNode}\",\n      \"status\": \"${status}\",\n      \"reason\": \"${reason}\"\n    },"
  }
}
println "    {}"
println "  ]"
println "}"
