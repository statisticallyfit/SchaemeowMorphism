ThisBuild / organization := "statisticallyfit"
ThisBuild / name := "SchaemeowMorphism"

ThisBuild / version := "0.1"

ThisBuild / scalaVersion := "2.12.17" //"2.13.10" //"2.12.17"

//ThisBuild / useCoursier := false

// Sources:
// https://stackoverflow.com/questions/66228218/intellij-doesnt-recognize-code-in-build-sbt-and-doesnt-compile
// https://medium.com/@supermanue/how-to-publish-a-scala-library-in-github-bfb0fa39c1e4
ThisBuild / githubOwner := "statisticallyfit"
ThisBuild / githubRepository := "SchaemeowMorphism"
// source of this tokensource declaration = https://stackoverflow.com/questions/66228218/intellij-doesnt-recognize-code-in-build-sbt-and-doesnt-compile
/*
ThisBuild / githubTokenSource := TokenSource.Environment("GITHUB_TOKEN")

ThisBuild / scmInfo := Some(
	ScmInfo(url("http://example.com"), "scm:git:https://github.com/alejandrohdezma/sbt-github.git")
)
*/

//crossScalaVersions := Seq("2.11.11", "2.12.17")

//set global / Test /
Test / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.ScalaLibrary
//Test / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.Flat




/*assumedEvictionErrorLevel := Level.Error
//assumedVersionScheme := VersionScheme.EarlySemVer
libraryDependencySchemes += "org.scala-lang.modules" %% "scala-java8-compat" % VersionScheme.Always*/
//libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always

// NOTE: when including docless dependency, the following error occurs (after this code)
// Solution below: https://stackoverflow.com/a/75246146
libraryDependencySchemes ++= Seq(
	"org.typelevel" %% "cats-core" % VersionScheme.Always,
	"io.circe" %% "circe-parser" % VersionScheme.Always,
	"io.circe" %% "circe-parser" % VersionScheme.Always,
	"io.circe" %% "circe-core" % VersionScheme.Always,
	"org.typelevel" %% "cats-kernel" % VersionScheme.Always,
	"org.typelevel" %% "cats-free" % VersionScheme.Always
)
/*
sbt:SchaemeowMorphism> compile
[error] stack trace is suppressed; run last update for the full output
[error] (update) found version conflict(s) in library dependencies; some are suspected to be binary incompatible:
[error]
[error] 	* org.typelevel:cats-core_2.12:2.9.0 (early-semver) is selected over {2.6.1, 2.7.0, 0.9.0}
[error] 	    +- schaemeowmorphism:schaemeowmorphism_2.12:0.1       (depends on 2.9.0)
[error] 	    +- io.circe:circe-core_2.12:0.14.5                    (depends on 2.9.0)
[error] 	    +- org.typelevel:cats-effect-kernel_2.12:3.4.8        (depends on 2.9.0)
[error] 	    +- org.typelevel:cats-free_2.12:2.9.0                 (depends on 2.9.0)
[error] 	    +- io.higherkindness:skeuomorph_2.12:0.2.1            (depends on 2.9.0)
[error] 	    +- org.typelevel:cats_2.12:0.9.0                      (depends on 0.9.0)
[error] 	    +- org.typelevel:cats-laws_2.12:0.9.0                 (depends on 0.9.0)
[error] 	    +- org.typelevel:cats-jvm_2.12:0.9.0                  (depends on 0.9.0)
[error] 	    +- io.higherkindness:droste-core_2.12:0.9.0           (depends on 2.7.0)
[error] 	    +- com.github.andyglow:scala-jsonschema-cats_2.12:0.7.9 (depends on 2.6.1)
[error]
[error] 	* io.circe:circe-parser_2.12:0.14.5 (early-semver) is selected over {0.14.1, 0.7.0}
[error] 	    +- io.higherkindness:skeuomorph_2.12:0.2.1            (depends on 0.14.5)
[error] 	    +- com.timeout:docless_2.12:0.5.0                     (depends on 0.7.0)
[error] 	    +- com.github.andyglow:scala-jsonschema-circe-json_2.12:0.7.9 (depends on 0.14.1)
[error]
[error] 	* io.circe:circe-core_2.12:0.14.5 (early-semver) is selected over {0.7.0, 0.14.1, 0.14.3}
[error] 	    +- io.circe:circe-parser_2.12:0.14.5                  (depends on 0.14.5)
[error] 	    +- io.circe:circe-jawn_2.12:0.14.5                    (depends on 0.14.5)
[error] 	    +- io.higherkindness:skeuomorph_2.12:0.2.1            (depends on 0.14.5)
[error] 	    +- io.circe:circe-yaml_2.12:0.14.2                    (depends on 0.14.3)
[error] 	    +- io.circe:circe-generic_2.12:0.14.1                 (depends on 0.14.1)
[error] 	    +- com.timeout:docless_2.12:0.5.0                     (depends on 0.7.0)
[error] 	    +- com.github.andyglow:scala-jsonschema-circe-json_2.12:0.7.9 (depends on 0.14.1)
[error] 	    +- com.beachape:enumeratum-circe_2.12:1.5.9           (depends on 0.7.0)
[error]
[error] 	* org.typelevel:cats-kernel_2.12:2.9.0 (early-semver) is selected over {0.9.0}
[error] 	    +- schaemeowmorphism:schaemeowmorphism_2.12:0.1       (depends on 2.9.0)
[error] 	    +- org.typelevel:cats-core_2.12:2.9.0                 (depends on 2.9.0)
[error] 	    +- org.typelevel:cats_2.12:0.9.0                      (depends on 0.9.0)
[error] 	    +- org.typelevel:cats-laws_2.12:0.9.0                 (depends on 0.9.0)
[error] 	    +- org.typelevel:cats-kernel-laws_2.12:0.9.0          (depends on 0.9.0)
[error]
[error] 	* org.typelevel:cats-free_2.12:2.9.0 (early-semver) is selected over {2.7.0, 0.9.0}
[error] 	    +- schaemeowmorphism:schaemeowmorphism_2.12:0.1       (depends on 2.9.0)
[error] 	    +- org.typelevel:cats_2.12:0.9.0                      (depends on 0.9.0)
[error] 	    +- io.higherkindness:droste-core_2.12:0.9.0           (depends on 2.7.0)
[error]
[error]
[error] this can be overridden using libraryDependencySchemes or evictionErrorLevel

*/

libraryDependencySchemes ++= Seq(
	"org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
// For below errors when trying to download coursier sbt plugin:
/*
found version conflict(s) in library dependencies; some are suspected to be binary incompatible:
[error]
[error] 	* org.scala-lang.modules:scala-xml_2.12:2.1.0 (early-semver) is selected over {1.3.0, 1.2.0}
[error] 	    +- org.scala-lang:scala-compiler:2.12.17              (depends on 2.1.0)
[error] 	    +- org.scala-sbt:librarymanagement-core_2.12:1.3.4    (depends on 1.2.0)
[error] 	    +- io.get-coursier:coursier-core_2.12:2.0.15          (depends on 1.3.0)

 */


//addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.10")


// PROJECTS


enablePlugins(BuildInfoPlugin)
enablePlugins(SbtGithubPlugin)
//enablePlugins(SbtCoursierPlugin)
/*enablePlugins(SbtDotenv)
enablePlugins(GitHubPackagesPlugin)*/


lazy val skeuomorphExtendedInGit = ProjectRef(uri("https://github.com/statisticallyfit/skeuomorph.git#master"), "skeuomorph")

lazy val skeuomorphExtendedInLocalCoursier = ProjectRef(
	//file("/development/tmp/.coursier"),
	file("/development/tmp/.coursier"),
	"root--coursier"
)


// global is the parent project, which aggregates all the other projects
lazy val global = project
	.in(file("."))
	.settings(
		name := "SchaemeowMorphism",/*
		commonSettings,*/ //mysettings,
	)
	.settings(commonSettings)
	.enablePlugins(BuildInfoPlugin) // TODO how to know what is the name of my declared plugins in the plugins.sbt file?
	/*.enablePlugins(SbtDotenv)
	.enablePlugins(GitHubPackagesPlugin)*/
	.enablePlugins(SbtGithubPlugin)
	.dependsOn(skeuomorphExtendedInLocalCoursier)



// DEPENDENCIES


lazy val allDependencies =
	new {

		// Listing the versions as values
		val versionOfScala = "2.12.17" //"2.13.10" //"2.12.17" //"2.13.10" // TODO how to use the `scalaVersion` variable above?

		val versionOfScalaTest = "3.2.15" //"3.3.0-SNAP2"

		val versionOfScalaCheck = "1.17.0"

		val versionOfScalaCheckCats = "0.3.2"

		val versionOfSpecs2 = "4.19.2" //4.9.4

		//val versionOfDiscipline = "0.11.1"
		//val versionOfDiscipline_core = "1.5.1" //"1.0.2"
		//val versionOfDiscipline_scalatest = "2.2.0" //"1.0.1"
		//val versionOfDiscipline_specs2 = "1.4.0" //"1.1.0"

		//val versionOfSpire = "0.17.0-M1"

		//val versionOfAlgebra =  "2.0.0" //"2.0.1"

		val versionOfSpireKindProjector = "0.9.10"
		val versionOfTypelevelKindProjector = "0.13.2"

		val versionOfCats = "2.9.0" // "2.2.0-M3"
		val versionOfCats_effects = "3.4.8"
		val versionOfCats_macros = "2.1.1"

		//val versionOfKindProjector = "0.13.2" // TODO how to get back to using it?

		val versionOfZIO = "2.0.13"
		val versionOfZIO_streams = "2.0.13"
		val versionOfZIO_test = "2.0.13"

		val versionOfZIO_schema = "0.4.11" //"0.4.8"

		val versionOfShapeless = "2.3.10"

		val versionOfDroste = "0.9.0"
		val versionOfMatryoshka = "0.21.3"


		val versionOfSkeuomorph = "0.0.0+1156-3632aa97+20231001-0125-SNAPSHOT" //"0.2.1"
		val versionOfAndyGlowScalaJsonSchema = "0.7.9"

		val versionOfSaulAutoschema = "1.0.4"
		val versionOfOpetushallitus = "2.33.0_2.12_beta" //"2.23.0_2.12"
		// Try downgrading to 3.6.6 because of "NoClassDefFoundError" for Jvalue
		// Source = https://stackoverflow.com/questions/69912882/java-lang-classnotfoundexception-org-json4s-jsonastjvalue
		val versionOfJson4s_simple = "3.2.11" // for scala 2.11
		val versionOfJson4s_others = "4.0.6" //"3.6.6" //3.6.6"//"4.0.6"
		val versionofFge = "2.2.6"
		val versionOfDocless = "0.5.0"

		val versionOfAvroTools = "1.11.1"

		val versionOfAvro4S = "4.1.1"

		val versionOfScalaRecords = "0.4"

		//------------------

		// Listing the different dependencies
		val scalaLibrary = "org.scala-lang" % "scala-library" % versionOfScala
		val scalaCompiler = "org.scala-lang" % "scala-compiler" % versionOfScala
		val scalaReflect = "org.scala-lang" % "scala-reflect" % versionOfScala


		//val scalactic = "org.scalactic" %% "scalactic" % versionOfScalactic

		val scalaTest = "org.scalatest" %% "scalatest" % versionOfScalaTest % Test

		val scalaCheck = "org.scalacheck" %% "scalacheck" % versionOfScalaCheck % Test
		// https://mvnrepository.com/artifact/io.chrisdavenport/cats-scalacheck
		val scalaCheckCats = "io.chrisdavenport" %% "cats-scalacheck" % "0.3.2" % Test


		val specs2Core = "org.specs2" %% "specs2-core" % versionOfSpecs2 % Test
		val specs2ScalaCheck = "org.specs2" %% "specs2-scalacheck" % versionOfSpecs2 % Test
		// TODO - difference between specs2-scalacheck and the ordinary scalacheck???


		//val discipline = "org.typelevel" %% "discipline" % versionOfDiscipline
		//val discipline_core = "org.typelevel" %% "discipline-core" % versionOfDiscipline_core
		//val discipline_scalatest = "org.typelevel" %% "discipline-scalatest" % versionOfDiscipline_scalatest % Test
		//val discipline_specs2 = "org.typelevel" %% "discipline-specs2" % versionOfDiscipline_specs2 % Test

		val spireKindProjector = "org.spire-math" %% "kind-projector" % versionOfSpireKindProjector
		val typelevelKindProjector = "org.typelevel" %% "kind-projector" % versionOfTypelevelKindProjector cross CrossVersion.full

		val cats_core = "org.typelevel" %% "cats-core" % versionOfCats
		val cats_kernel = "org.typelevel" %% "cats-kernel" % versionOfCats
		val cats_laws = "org.typelevel" %% "cats-laws" % versionOfCats % Test
		val cats_free = "org.typelevel" %% "cats-free" % versionOfCats
		val cats_macros = "org.typelevel" %% "cats-macros" % versionOfCats_macros //versionOfCats
		//versionOfCats_macros
		val cats_testkit = "org.typelevel" %% "cats-testkit" % versionOfCats % Test
		val cats_effects = "org.typelevel" %% "cats-effect" % versionOfCats_effects % Test

		//Shapeless
		val shapeless = "com.chuusai" %% "shapeless" % versionOfShapeless

		// Kind projector plugin
		// technicalities here = https://github.com/typelevel/kind-projector
		//val kindProjector = compilerPlugin("org.typelevel" %% "kind-projector" % versionOfKindProjector)
		//val kindProjector = "org.typelevel" %% "kind-projector" % versionOfKindProjector
		//val kindProjector = "org.typelevel" %% "kind-projector" % versionOfKindProjector
		//"org.typelevel" %% "kind-projector" %
		//val kindProjector = compilerPlugin("org.spire-math" %% "kind-projector" % versionOfKindProjector)

		// ZIO-schema
		val zio = "dev.zio" %% "zio" % versionOfZIO
		val zioSchema = "dev.zio" %% "zio-schema" % versionOfZIO_schema
		val zioSchemaAvro = "dev.zio" %% "zio-schema-avro" % versionOfZIO_schema
		val zioSchemaJson = "dev.zio" %% "zio-schema-json" % versionOfZIO_schema
		val zioSchemaProtobuf = "dev.zio" %% "zio-schema-protobuf" % versionOfZIO_schema
		// Required for automatic generic derivation of schemas
		val zioSchemaDerivation = "dev.zio" %% "zio-schema-derivation" % versionOfZIO_schema
		val zioStream = "dev.zio" %% "zio-streams" % versionOfZIO_streams
		val zioTest = "dev.zio" %% "zio-test" % versionOfZIO_test


		// Matryoshka recursion schemes
		val matryoshka = "com.slamdata" %% "matryoshka-core" % "0.21.3"
		// TODO WARNING matryoshka is the only lib that doesn't support over scala 2.12

		//Droste recursion schemes
		val drosteCore = "io.higherkindness" %% "droste-core" % versionOfDroste
		val drosteLaws = "io.higherkindness" %% "droste-laws" % versionOfDroste
		val drosteMacros = "io.higherkindness" %% "droste-macros" % versionOfDroste
		/*"io.higherkindness" %% "droste-meta" % "0.8.0",
		"io.higherkindness" %% "droste-reftree" % "0.8.0",*/
		val drosteScalaCheck = "io.higherkindness" %% "droste-scalacheck" % versionOfDroste


		// Other schema libraries
		val skeuomorph = "io.higherkindness" %% "skeuomorph" % versionOfSkeuomorph // "0.0.0+1150-dc2f08c4+20230820-1843-SNAPSHOT"//versionOfSkeuomorph
		//val skeuomorph = "io.higherkindness" % "skeuomorph" % "v0.2.1"
		//val skeuomorph_publishLocal = "io.higherkindness" %% "skeuomorph" % "7164525f-SNAPSHOT" //"0.0.0+1149-7164525f-SNAPSHOT"///

		val andyGlowScalaJsonSchema = "com.github.andyglow" %% "scala-jsonschema" % versionOfAndyGlowScalaJsonSchema
		val andyGlow_jsonschema_Macros = "com.github.andyglow" %% "scala-jsonschema-macros" % versionOfAndyGlowScalaJsonSchema % Provided // <-- transitive
		// json bridge. pick one
		val andyGlow_jsonschema_PlayJson = "com.github.andyglow" %% "scala-jsonschema-play-json" % versionOfAndyGlowScalaJsonSchema // <-- optional
		val andyGlow_jsonschema_SprayJson = "com.github.andyglow" %% "scala-jsonschema-spray-json" % versionOfAndyGlowScalaJsonSchema // <-- optional
		val andyGlow_jsonschema_CirceJson = "com.github.andyglow" %% "scala-jsonschema-circe-json" % versionOfAndyGlowScalaJsonSchema // <-- optional
		val andyGlow_jsonschema_Json4sJson = "com.github.andyglow" %% "scala-jsonschema-json4s-json" % versionOfAndyGlowScalaJsonSchema // <-- optional
		val andyGlow_jsonschema_UJson = "com.github.andyglow" %% "scala-jsonschema-ujson" % versionOfAndyGlowScalaJsonSchema // <-- optional
		// joda-time support
		val andyGlow_jsonschema_Joda = "com.github.andyglow" %% "scala-jsonschema-joda-time" % versionOfAndyGlowScalaJsonSchema // <-- optional
		// cats support
		val andyGlow_jsonschema_Cats = "com.github.andyglow" %% "scala-jsonschema-cats" % versionOfAndyGlowScalaJsonSchema // <-- optional
		// refined support
		val andyGlow_jsonschema_Refined = "com.github.andyglow" %% "scala-jsonschema-refined" % versionOfAndyGlowScalaJsonSchema // <-- optional

		val andyGlow_jsonschema_Derived = "com.github.andyglow" %% "scala-jsonschema-derived" % versionOfAndyGlowScalaJsonSchema
		// enumeratum support
		val andyGlow_jsonschema_Enumeratum = "com.github.andyglow" %% "scala-jsonschema-enumeratum" % versionOfAndyGlowScalaJsonSchema // <-- optional
		// zero-dependency json and jsonschema parser
		val andyGlow_jsonschema_Parser = "com.github.andyglow" %% "scala-jsonschema-parser" % versionOfAndyGlowScalaJsonSchema // <-- optional


		val saul_autoschema = "com.sauldhernandez" %% "autoschema" % versionOfSaulAutoschema
		val docless = "com.timeout" %% "docless" % versionOfDocless
		val opetushallitus_scalaschema = "com.github.Opetushallitus" % "scala-schema" % versionOfOpetushallitus
		val json4s = "org.json4s" %% "json4s" % versionOfJson4s_simple
		val json4s_jackson = "org.json4s" %% "json4s-jackson" % versionOfJson4s_others
		val json4s_jackson_core = "org.json4s" %% "json4s-jackson-core" % versionOfJson4s_others
		val json4s_core = "org.json4s" %% "json4s-core" % versionOfJson4s_others
		val json4s_ast = "org.json4s" %% "json4s-ast" % versionOfJson4s_others
		val json4s_native = "org.json4s" %% "json4s-native" % versionOfJson4s_others
		val json4s_native_core = "org.json4s" %% "json4s-native-core" % versionOfJson4s_others
		val json4s_ext = "org.json4s" %% "json4s-ext" % versionOfJson4s_others
		val json4s_scalap = "org.json4s" %% "json4s-scalap" % versionOfJson4s_others
		val fge_jsonschemavalidator = "com.github.fge" % "json-schema-validator" % versionofFge

		// https://mvnrepository.com/artifact/org.apache.avro/avro-tools
		val avroTools_for_avdlToAvsc = "org.apache.avro" % "avro-tools" % versionOfAvroTools

		val avro4s_core = "com.sksamuel.avro4s" %% "avro4s-core" % versionOfAvro4S
		val avro4s_json = "com.sksamuel.avro4s" %% "avro4s-json" % versionOfAvro4S

		val scala_records =  "ch.epfl.lamp" %% "scala-records" % versionOfScalaRecords


		/*val coursierrepo = "io.get-coursier" % "sbt-coursier" % "2.0.8"

		val sbtgitpackages = "com.codecommit" % "sbt-github-packages" % "0.3.1"

		val sbtdotenv = "nl.gn0s1s" % "sbt-dotenv" % "2.1.233"

		val sbtbuildinfo = "com.eed3si9n" % "sbt-buildinfo" % "0.11.0"*/

	}


//addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.10")
addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full)


//lazy val commonDependencies = Seq(
//	/*dependencies.logback,
//	dependencies.logstash,
//	dependencies.scalaLogging,
//	dependencies.slf4j,
//	dependencies.typesafeConfig,
//	dependencies.akka,
//	dependencies.scalatest  % "test",
//	dependencies.scalacheck % "test"*/
//)





// SETTINGS
//
//lazy val mysettings =
//	commonSettings
//		/*wartremoverSettings ++
//		scalafmtSettings*/

// Recommended scala 2.13 compiler options = https://nathankleyn.com/2019/05/13/recommended-scalac-flags-for-2-13/
lazy val compilerOptions = Seq(
	"-deprecation",
	"-unchecked",
	"-feature",
	"-language:existentials",
	"-language:higherKinds",
	"-language:implicitConversions",
	"-language:postfixOps",
	//"-Ylog-classpath"
	// TODO try putting Xnojline:off = https://hyp.is/Ard1uM71Ee2sWMf7uSXXaQ/docs.scala-lang.org/overviews/compiler-options/index.html

	//"-XJline:off" // TODO trying to stop this message from appearing on REPL: warning: -Xnojline is
	// deprecated: Replaced by -Xjline:off
	//"-Ypartial-unification" //todo got error in sbt compilation " error: bad option" why?
	//"-encoding",	//"utf8"
)

lazy val commonSettings = Seq(
	scalacOptions ++= compilerOptions,

	resolvers ++= (/*Seq(Resolver.githubPackages("statisticallyfit"))
				++ */ Resolver.sonatypeOssRepos("releases") // for the kind projector plugin
	                     //++ Seq(Resolver.mavenLocal)
	                     ++ Resolver.sonatypeOssRepos("snapshots")
		              //++ Seq("jitpack" at "https://jitpack.io") // jitpack for opetushallitus
		              ++ Seq("Local Coursier Repository" at ("file://" + "/development/tmp/.coursier"))
		              //++ Seq("Local Ivy Repository" at ("file://" + Path.userHome.absolutePath + "/.ivy2/local"))
		              //ThisBuild / useCoursier := false)
		              ),
	libraryDependencies ++= Seq(/*commonDependencies ++*/

		allDependencies.scalaLibrary,
		allDependencies.scalaCompiler,
		allDependencies.scalaReflect,

		allDependencies.scalaCheck,
		allDependencies.scalaCheckCats,

		allDependencies.specs2Core,
		allDependencies.specs2ScalaCheck,

		allDependencies.scalaTest,

		//allDependencies.discipline,
		//allDependencies.discipline_core,
		//allDependencies.discipline_scalatest,
		//allDependencies.discipline_specs2,


		allDependencies.cats_core,
		allDependencies.cats_kernel,
		allDependencies.cats_laws,
		allDependencies.cats_free,
		allDependencies.cats_macros,
		allDependencies.cats_testkit,
		allDependencies.cats_effects,

		allDependencies.shapeless,

		allDependencies.zio,
		allDependencies.zioSchema,
		allDependencies.zioSchemaAvro,
		allDependencies.zioSchemaJson,
		allDependencies.zioSchemaProtobuf,
		allDependencies.zioSchemaDerivation,
		allDependencies.zioStream,
		allDependencies.zioTest,

		allDependencies.matryoshka,

		//allDependencies.spireKindProjector,
		//allDependencies.typelevelKindProjector,

		allDependencies.drosteCore,
		allDependencies.drosteLaws,
		allDependencies.drosteMacros,
		allDependencies.drosteScalaCheck,

		allDependencies.skeuomorph,
		//allDependencies.skeuomorph_publishLocal,

		allDependencies.andyGlowScalaJsonSchema,
		allDependencies.andyGlow_jsonschema_Macros,
		allDependencies.andyGlow_jsonschema_PlayJson,
		allDependencies.andyGlow_jsonschema_SprayJson,
		allDependencies.andyGlow_jsonschema_CirceJson,
		allDependencies.andyGlow_jsonschema_Json4sJson,
		allDependencies.andyGlow_jsonschema_UJson,
		allDependencies.andyGlow_jsonschema_Joda,
		allDependencies.andyGlow_jsonschema_Cats,
		allDependencies.andyGlow_jsonschema_Refined,
		allDependencies.andyGlow_jsonschema_Derived,
		allDependencies.andyGlow_jsonschema_Enumeratum,
		allDependencies.andyGlow_jsonschema_Parser,

		allDependencies.saul_autoschema,

		allDependencies.docless,
		/*allDependencies.opetushallitus_scalaschema,
		// Dependency (fge) for opetus hallitus - was not pulled in by itself, why?
		allDependencies.fge_jsonschemavalidator,
		// Dependecy (json4s-core, ast, jackson) - versioning error. If for all the json4s libs, if I don't keep
		// the version the same, and state them explciitly here, then compiler complains with classpath error (jvalue not found)
		// Solution source = https://stackoverflow.com/a/47669923
		//allDependencies.json4s,
		allDependencies.json4s_jackson,
		//allDependencies.json4s_jackson_core,
		//allDependencies.json4s_core,
		allDependencies.json4s_ast,
		//allDependencies.json4s_native,
		//allDependencies.json4s_native_core,
		allDependencies.json4s_ext,
		//allDependencies.json4s_scalap,*/

		allDependencies.avroTools_for_avdlToAvsc,


		allDependencies.avro4s_core,
		allDependencies.avro4s_json,

		/*allDependencies.coursierrepo,
		allDependencies.sbtdotenv,
		allDependencies.sbtgitpackages,
		allDependencies.sbtbuildinfo*/
		//allDependencies.scala_records
	)
) ++ compilerPlugins


lazy val compilerPlugins = Seq(
	libraryDependencies ++= /*(CrossVersion.partialVersion(scalaVersion.value) match {
		case Some((2, _)) =>*/
			Seq(
				//compilerPlugin("org.spire-math" %% "kind-projector" % "0.9.10" /*cross CrossVersion.full*/),
				compilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full),

				// NOTE: got withFilter error (in objectJsonSchemaDecoder) in for-comprehension so using this plugin = https://github.com/oleg-py/better-monadic-for
				compilerPlugin("com.olegpy"   %% "better-monadic-for" % "0.3.1")
				//compilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
			)
	/*case _ => Seq.empty
}*/)

//addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.10")






// ---------- TRYING TO MAKE SBT HERE PICK UP CHANGES IN COURSIER:

// Way 1 - setting coursier-time-limit-pickup
// Sources:
// https://stackoverflow.com/a/67862862
// https://stackoverflow.com/a/60776765

import scala.concurrent.duration.DurationInt
import lmcoursier.definitions.CachePolicy


csrConfiguration := csrConfiguration.value
	.withTtl(Some(0.seconds))
	.withCachePolicies(Vector(CachePolicy.LocalOnly))


