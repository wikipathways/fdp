@Grab(group='io.github.egonw.bacting', module='managers-rdf', version='0.3.2')

import org.apache.jena.shex.ShexStatus

workspaceRoot = ".."
root = "wikipathways-fdp"
rdf = new net.bioclipse.managers.RDFManager(workspaceRoot);

type = args[3]
clazz = "http://purl.org/fdp/fdp-o#" + type
file = args[2]
shape = args[0]
shapeRes = args[1]

store = rdf.createInMemoryStore(true);
store = rdf.importFile(store, "/${root}/${file}", "TURTLE")
report = rdf.validateAllOfType(
  store,
  "/${root}/${shape}.shex",
  "http://fairdatapoint.org/${shapeRes}",
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
