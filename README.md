# WikiPathways FDP

Repository with the content of the WikiPathways FAIR Data Point.

It pings the EJP-RD FAIR Data Points Index.

## ShEx Validation

The following commands can be used to validate the FDP against
the matching shape expression:

```shell
groovy validate.groovy resource.shex http://fairdatapoint.org/ResourceShape \
                       index.html    http://purl.org/fdp/fdp-o#FairDataPoint
groovy validate.groovy catalog.shex  http://fairdatapoint.org/CatalogShape \
                       index.html    http://www.w3.org/ns/dcat#Catalog
groovy validate.groovy catalog.shex  http://fairdatapoint.org/CatalogShape \
                       catalog/index.html http://www.w3.org/ns/dcat#Catalog
```

## Funding

This project has received support from EJP-RD, funded the European Union’s Horizon 2020
research and innovation programme under grant agreement N°825575.
