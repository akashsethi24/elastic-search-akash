name := "Elastic_Search"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.elasticsearch" % "elasticsearch" % "2.2.1"
libraryDependencies += "org.elasticsearch.plugin" % "delete-by-query" % "2.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.2" % "test"