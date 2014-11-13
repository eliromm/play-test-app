name := "hoge"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.fluentd" % "fluent-logger" % "0.3.1"
)     

play.Project.playJavaSettings
