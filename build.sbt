name := "binary-search-tree-validate-scala"

organization := "com.franklinchen"

version := "1.0.0"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "com.google.caliper" % "caliper" % "0.5-rc1",
  "com.google.code.java-allocation-instrumenter" % "java-allocation-instrumenter" % "2.0",
  "com.google.code.gson" % "gson" % "2.2.2",
  "org.specs2" %% "specs2" % "1.13" % "test")

fork in run := true

// we need to add the runtime classpath as a "-cp" argument to the `javaOptions in run`, otherwise caliper
// will not see the right classpath and die with a ConfigurationException
javaOptions in run <++= (fullClasspath in Runtime) map { cp => Seq("-cp", sbt.Build.data(cp).mkString(":")) }

// Prevent running out of heap space
//javaOptions in run += "-Xmx3000m"

//javaOptions in test += "-Xmx3000m"
