package conversionsOfSchemaADTs.avro_avro.skeuo_apache.specs


import higherkindness.droste._
import higherkindness.droste.data.Fix
import higherkindness.droste.syntax.all._

import higherkindness.skeuomorph.avro.{AvroF ⇒ SchemaAvro_Skeuo}
import higherkindness.skeuomorph.avro.AvroF.{TString, TInt, TArray, TEnum, TRecord} // TODO adding more



import org.apache.avro.{Schema ⇒ SchemaAvro_Apache}
///import org.apache.avro.{LogicalType => LogicalTypeApache, LogicalTypes ⇒ LogicalTypesApache}


import scala.jdk.CollectionConverters._


import testData.ScalaCaseClassData._


//import org.scalacheck._
//import org.specs2._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should._
//import org.specs2.specification.core.SpecStructure


import conversionsOfSchemaADTs.avro_avro.Skeuo_Apache._ //{apacheToSkeuoAvroSchema, skeuoToApacheAvroSchema}
import conversionsOfSchemaStrings.avro_json.Skeuo_JsonCirce._
//{skeuoAvroSchemaToJsonString, jsonStringToSkeuoAvroSchema, avroSchemaToJsonString}


import utilTest.Util

import testData.schemaData.avroData.apacheData.ApacheAvroSchemaData._
import testData.GeneralTestData._
/**
 *
 */
class AvroSchema_SkeuoApacheRoundTrip_Skeuopecs extends AnyWordSpec with Matchers {
	//extends mutable.Specification /*with ScalaCheck */ {

	// OUTPUT:
	/*
	apache avro String (string): "string"
	(ARRAY AVRO APACHE-STRING) apache avro Array (string): {"type":"array","items":"int"}
	apache avro Enum (string): {"type":"enum","name":"Color","namespace":"namespace","doc":"doc","symbols":["red","yellow","blue"]}
	(ARRAY JSON CIRCE): arrayApache -> arraySkeuo adt -> array Json circe: {
	"type" : "array",
	"items" : "Int"
	}
	Printing long function type = org.apache.avro.Schema => higherkindness.droste.data.Fix[higherkindness.skeuomorph.avro.AvroF]
	Printing short function type = SchemaAvro_Apache => Fix[SchemaAvro_Skeuo]
	FUNC TYPE: apacheToSkeuoAvroSchema: SchemaAvro_Apache => Fix[SchemaAvro_Skeuo]
	Printing long function type = higherkindness.droste.data.Fix[higherkindness.skeuomorph.avro.AvroF] => io.circe.Json
	Printing short function type = Fix[SchemaAvro_Skeuo] => io.circe.Json
	FUNC TYPE: avroSchemaToJsonString: Fix[SchemaAvro_Skeuo] => io.circe.Json
	skeuo avro string = TString()
	CHECK TRUE TSTRING: true
	(ARRAY AVRO SKEUO-ADT): skeuo avro array = TArray(TInt())
	skeuo avro enum: TEnum(Color,Some(namespace),List(),Some(doc),List(red, yellow, blue))
	skeuo avro enum unfix: TEnum(Color,Some(namespace),List(),Some(doc),List(red, yellow, blue))
	strSchemaBack_Apache = "string"
	Inside avroFToApache ARRAY converter:
	apacheSchema = "int"
	apacheSchema.getType = INT
	arrayApacheBack = {"type":"array","items":"int"}
	enumApacheBack = {"type":"enum","name":"Color","namespace":"namespace","doc":"doc","symbols":["red","yellow","blue"]}
	 */

	// TODO import instances = https://github.com/higherkindness/skeuomorph/blob/main/src/test/scala/higherkindness/skeuomorph/avro/AvroSchemaSpec.scala#L20

	//TODO when testing isvalidated for logical types apache, must use assertthrows = https://www.scalatest.org/user_guide/selecting_a_style
	
	// TODO: checking doesnot compile or does not typecheck = https://hyp.is/ARIQjgsXEe6YspfkWnX0nA/www.scalatest.org/user_guide/using_matchers

	
	// TODO next steps:
	// 1) add more apache-avro-schema data
	// 2) run this file, get the results, assert as printed
	// 3) do the PROPS with scalacheck
	
	// TODO check containword / existword / haveword operations when comparing more complicated schemas, just in case the contents are not in the same order.
	
	// shouldBe a [Type] - source = https://hyp.is/U-0D0gsREe6sV2tTo6rVyg/www.scalatest.org/user_guide/using_matchers
	
	// Apache avro schema
	//println(s"apache avro String (string): $strApache")
	
	"Morphism conversions" when {
		"anamorphism should extract inner type of coalgebra" in {
			
			// type-check -- for coalgebra
			Util.getFuncTypeSubs(coalgebra_ApacheToSkeuo) shouldEqual "Coalgebra[SchemaAvro_Skeuo,SchemaAvro_Apache]"
			coalgebra_ApacheToSkeuo shouldBe a [Coalgebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
			
			
			// type-check -- for the extracted coalgebra type
			val funcTypeAnamorphismOfCoalgebra: String = Util.getFuncTypeSubs(scheme.ana(coalgebra_ApacheToSkeuo).apply(_))
			val funcTypeApacheToSkeuo: String = Util.getFuncTypeSubs(apacheToSkeuoAvroSchema)
			
			funcTypeApacheToSkeuo shouldEqual "SchemaAvro_Apache => Fix[SchemaAvro_Skeuo]"
			funcTypeAnamorphismOfCoalgebra shouldEqual funcTypeApacheToSkeuo
			
			scheme.ana(coalgebra_ApacheToSkeuo) shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
			apacheToSkeuoAvroSchema shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
			
		}
		"catamorphism should extract inner type of algebra" in {
			// type-check -- for algebra
			Util.getFuncTypeSubs(algebra_SkeuoToApache) shouldEqual "Algebra[SchemaAvro_Skeuo,SchemaAvro_Apache]"
			algebra_SkeuoToApache shouldBe a [Algebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
			
			
			// type-check -- for the extracted algebra type
			val funcTypeCatamorphismOfAlgebra: String = Util.getFuncTypeSubs(scheme.cata(algebra_SkeuoToApache).apply(_))
			val funcTypeSkeuoToApache: String = Util.getFuncTypeSubs(skeuoToApacheAvroSchema)
			
			funcTypeSkeuoToApache shouldEqual "Fix[SchemaAvro_Skeuo] => SchemaAvro_Apache"
			funcTypeCatamorphismOfAlgebra shouldEqual funcTypeSkeuoToApache
			
			scheme.cata(algebra_SkeuoToApache) shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache]
			skeuoToApacheAvroSchema shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache]
		}
	}
	
	"Converting skeuomorph's Avro Schema-ADT to org.apache.avro's Avro Schema ADT" when {
		
		"String" should {
			"start as Apache (avro schema)" in {
				
				// value-check
				strSchema_Apache should equal (SchemaAvro_Apache.create(SchemaAvro_Apache.Type.STRING))
				// NOTE: double quotes because avro schema prints the quotes
				strSchema_Apache.toString should equal("\"string\"") // checks value
				
				// type-check
				strSchema_Apache shouldBe a [SchemaAvro_Apache]
				strSchema_Apache.toString shouldBe a [String] // checks type
			}
			
			"convert from Apache -> Skeuo (Avro Schema) using anamorphism of coalgebra" in {
				
				
				// type-check -- for coalgebra
				coalgebra_ApacheToSkeuo shouldBe a [Coalgebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
				scheme.ana(coalgebra_ApacheToSkeuo) shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
				apacheToSkeuoAvroSchema shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
				
				Util.getFuncTypeSubs(apacheToSkeuoAvroSchema) shouldEqual "SchemaAvro_Apache => Fix[SchemaAvro_Skeuo]"
				Util.getFuncTypeSubs(scheme.ana(coalgebra_ApacheToSkeuo).apply(_)) shouldEqual "SchemaAvro_Apache => Fix[SchemaAvro_Skeuo]"
				
				
				// value-check --- for conversion result
				val strSchema_Skeuo: Fix[SchemaAvro_Skeuo] = apacheToSkeuoAvroSchema(strSchema_Apache)
				
				strSchema_Skeuo shouldEqual TString()
				strSchema_Skeuo.toString shouldEqual "TString()"
				
				//type -check --- for conversion result
				Util.getFuncTypeSubs(strSchema_Skeuo) shouldEqual "Fix[SchemaAvro_Skeuo]"
				strSchema_Skeuo shouldBe a [SchemaAvro_Skeuo[_]]
				
				// NOTE: interesting, Fix type is invisible???
				//strSchema_Skeuo shouldBe a [ Fix[_] ] //runtime erases type parameters
			}
			
			"convert from Skeuo -> Apache (Avro Schema) using catamorphism of algebra" in {
				
				
				// type-check -- for algebra
				algebra_SkeuoToApache shouldBe a [Algebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
				scheme.cata(algebra_SkeuoToApache) shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache ]
				skeuoToApacheAvroSchema shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache]
				
				Util.getFuncTypeSubs(skeuoToApacheAvroSchema) shouldEqual "Fix[SchemaAvro_Skeuo] => SchemaAvro_Apache"
				Util.getFuncTypeSubs(scheme.cata(algebra_SkeuoToApache).apply(_)) shouldEqual "Fix[SchemaAvro_Skeuo] => SchemaAvro_Apache"
				
				
				// value-check --- for conversion result
				val strSchema_Skeuo: Fix[SchemaAvro_Skeuo] = apacheToSkeuoAvroSchema(strSchema_Apache)
				strSchema_Skeuo shouldEqual TString()
				strSchema_Skeuo.toString shouldEqual "TString()"
				
				val strSchemaBack_Apache: SchemaAvro_Apache = skeuoToApacheAvroSchema(strSchema_Skeuo)
				strSchemaBack_Apache should equal(SchemaAvro_Apache.create(SchemaAvro_Apache.Type.STRING))
				strSchemaBack_Apache.toString should equal("\"string\"") // checks value
				
				
				//type -check --- for conversion result
				strSchema_Skeuo shouldBe a [SchemaAvro_Skeuo[_]]
				strSchemaBack_Apache shouldBe a [SchemaAvro_Apache]
				strSchemaBack_Apache.toString shouldBe a [String] // checks type
				
				Util.getFuncTypeSubs(strSchema_Skeuo) shouldEqual "Fix[SchemaAvro_Skeuo]"
				Util.getFuncTypeSubs(strSchemaBack_Apache) shouldEqual "SchemaAvro_Apache"
				
			}
			
			// TODO refactor this fucntion to show cata-ana-hylo (type and value checks)
			"(round-trip) convert from Apache -> Skeuo -> Apache and Skeuo -> Apache -> Skeuo (avro schema) using hylomorphism " in {
				
				// type-check -- for algebra, coalgebra
				coalgebra_ApacheToSkeuo shouldBe a [Coalgebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
				algebra_SkeuoToApache shouldBe a [Algebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
				
				scheme.ana(coalgebra_ApacheToSkeuo) shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
				scheme.cata(algebra_SkeuoToApache) shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache]
				
				apacheToSkeuoAvroSchema shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
				skeuoToApacheAvroSchema shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache]
				
				
				Util.getFuncTypeSubs(apacheToSkeuoAvroSchema) shouldEqual "SchemaAvro_Apache => Fix[SchemaAvro_Skeuo]"
				Util.getFuncTypeSubs(skeuoToApacheAvroSchema) shouldEqual "Fix[SchemaAvro_Skeuo] => SchemaAvro_Apache"
				
				
				//----------------------
				val strSchema_Skeuo: Fix[SchemaAvro_Skeuo] = apacheToSkeuoAvroSchema(strSchema_Apache)
				
				
				// value-check --- of conversion
				apacheRoundTrip(strSchema_Apache) should equal (strSchema_Apache)
				skeuoRoundTrip(strSchema_Skeuo) should equal (strSchema_Skeuo)
				
				// type-check --- of conversion
				apacheRoundTrip shouldBe a [SchemaAvro_Apache => SchemaAvro_Apache]
				scheme.hylo(algebra_SkeuoToApache, coalgebra_ApacheToSkeuo) shouldBe a [SchemaAvro_Apache => SchemaAvro_Apache]
				Util.getFuncTypeSubs(apacheRoundTrip) shouldEqual "SchemaAvro_Apache => SchemaAvro_Apache"
				
				skeuoRoundTrip shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Skeuo[_]]
				(apacheToSkeuoAvroSchema compose skeuoToApacheAvroSchema) shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Skeuo[_]]
				Util.getFuncTypeSubs(skeuoRoundTrip) shouldEqual "Fix[SchemaAvro_Skeuo] => Fix[SchemaAvro_Skeuo]"
			}
		}
		
		// -----------------------------------------------------------------------------------------------
		
		"Array" should {
			
			
			"start as Apache (avro schema)" in {
				
				// value-check
				arrayIntSchema_Apache should equal(SchemaAvro_Apache.createArray(intSchema_Apache)) // use this for Props: arrayIntSchema_Apache.getElementType
				arrayIntSchema_Apache.toString should equal("{\"type\":\"array\",\"items\":\"int\"}")
				// NOTE: with triple quotes this error is fixed:
				//  (previous) error:  ')' expected but string literal found.
				arrayIntSchema_Apache.toString shouldEqual s"""{\"type\":\"array\",\"items\":${arrayIntSchema_Apache.getElementType.toString}}"""
				
				
				arrayStrSchema_Apache should equal(SchemaAvro_Apache.createArray(strSchema_Apache))
				arrayStrSchema_Apache.toString should equal("{\"type\":\"array\",\"items\":\"string\"}")
				// NOTE: double quotes because avro schema prints the quotes
				
				
				array3IntSchema_Apache shouldEqual SchemaAvro_Apache.createArray(SchemaAvro_Apache.createArray(SchemaAvro_Apache.createArray(intSchema_Apache)))
				array3IntSchema_Apache.toString shouldEqual "{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":{\"type\":\"array\",\"items\":\"int\"}}}"
				
				
				
				
				// type-check
				arrayIntSchema_Apache shouldBe a [SchemaAvro_Apache]
				arrayStrSchema_Apache shouldBe a [SchemaAvro_Apache]
				array3IntSchema_Apache shouldBe a [SchemaAvro_Apache]
				
				arrayIntSchema_Apache.toString shouldBe a [String]
				arrayStrSchema_Apache.toString shouldBe a [String]
				array3IntSchema_Apache.toString shouldBe a [String]
			}
			
			"convert from Apache -> Skeuo (Avro Schema) using anamorphism of coalgebra" in {
				
				
				// type-check -- for coalgebra
				coalgebra_ApacheToSkeuo shouldBe a [Coalgebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
				scheme.ana(coalgebra_ApacheToSkeuo) shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
				apacheToSkeuoAvroSchema shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
				
				Util.getFuncTypeSubs(apacheToSkeuoAvroSchema) shouldEqual "SchemaAvro_Apache => Fix[SchemaAvro_Skeuo]"
				Util.getFuncTypeSubs(scheme.ana(coalgebra_ApacheToSkeuo).apply(_)) shouldEqual "SchemaAvro_Apache => Fix[SchemaAvro_Skeuo]"
				
				
				// value-check --- for conversion result
				val arrayIntSchema_Skeuo: Fix[SchemaAvro_Skeuo] = apacheToSkeuoAvroSchema(arrayIntSchema_Apache)
				val innerSchema_Skeuo: Fix[SchemaAvro_Skeuo] = apacheToSkeuoAvroSchema(arrayIntSchema_Apache.getElementType)
				
				innerSchema_Skeuo shouldEqual TInt()
				arrayIntSchema_Skeuo shouldEqual TArray(TInt())
				arrayIntSchema_Skeuo.toString shouldEqual "TArray(TInt())"
				
				//type -check --- for conversion result
				Util.getFuncTypeSubs(arrayIntSchema_Skeuo) shouldEqual "Fix[SchemaAvro_Skeuo]"
				arrayIntSchema_Skeuo shouldBe a [SchemaAvro_Skeuo[_]]
			}
			
			"convert from Skeuo -> Apache (Avro Schema) using catamorphism of algebra" in {
				
				
				// type-check -- for algebra
				algebra_SkeuoToApache shouldBe a [Algebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
				scheme.cata(algebra_SkeuoToApache) shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache]
				skeuoToApacheAvroSchema shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache]
				
				Util.getFuncTypeSubs(skeuoToApacheAvroSchema) shouldEqual "Fix[SchemaAvro_Skeuo] => SchemaAvro_Apache"
				Util.getFuncTypeSubs(scheme.cata(algebra_SkeuoToApache).apply(_)) shouldEqual "Fix[SchemaAvro_Skeuo] => SchemaAvro_Apache"
				
				
				// value-check --- for conversion result
				val arrayIntSchema_Skeuo: Fix[SchemaAvro_Skeuo] = apacheToSkeuoAvroSchema(arrayIntSchema_Apache)
				arrayIntSchema_Skeuo shouldEqual TArray(TInt())
				arrayIntSchema_Skeuo.toString shouldEqual "TArray(TInt())"
				
				val arrayIntSchemaBack_Apache: SchemaAvro_Apache = skeuoToApacheAvroSchema(arrayIntSchema_Skeuo)
				arrayIntSchemaBack_Apache should equal(SchemaAvro_Apache.createArray(intSchema_Apache))
				arrayIntSchemaBack_Apache.toString should equal("{\"type\":\"array\",\"items\":\"int\"}") // checks value
				
				
				//type -check --- for conversion result
				arrayIntSchema_Skeuo shouldBe a [SchemaAvro_Skeuo[_]]
				arrayIntSchemaBack_Apache shouldBe a [SchemaAvro_Apache]
				arrayIntSchemaBack_Apache.toString shouldBe a [String] // checks type
				
				Util.getFuncTypeSubs(arrayIntSchema_Skeuo) shouldEqual "Fix[SchemaAvro_Skeuo]"
				Util.getFuncTypeSubs(arrayIntSchemaBack_Apache) shouldEqual "SchemaAvro_Apache"
				
			}
			
			// TODO refactor this fucntion to show cata-ana-hylo (type and value checks)
			"(round-trip) convert from Apache -> Skeuo -> Apache and Skeuo -> Apache -> Skeuo (avro schema) using hylomorphism " in {
				
				// type-check -- for algebra, coalgebra
				coalgebra_ApacheToSkeuo shouldBe a [Coalgebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
				algebra_SkeuoToApache shouldBe a [Algebra[SchemaAvro_Skeuo, SchemaAvro_Apache]]
				
				scheme.ana(coalgebra_ApacheToSkeuo) shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
				scheme.cata(algebra_SkeuoToApache) shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache]
				
				apacheToSkeuoAvroSchema shouldBe a [SchemaAvro_Apache => SchemaAvro_Skeuo[_]]
				skeuoToApacheAvroSchema shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Apache]
				
				
				Util.getFuncTypeSubs(apacheToSkeuoAvroSchema) shouldEqual "SchemaAvro_Apache => Fix[SchemaAvro_Skeuo]"
				Util.getFuncTypeSubs(skeuoToApacheAvroSchema) shouldEqual "Fix[SchemaAvro_Skeuo] => SchemaAvro_Apache"
				
				
				//----------------------
				val arrayIntSchema_Skeuo: Fix[SchemaAvro_Skeuo] = apacheToSkeuoAvroSchema(arrayIntSchema_Apache)
				
				// value-check --- of conversion
				apacheRoundTrip(arrayIntSchema_Apache) should equal(arrayIntSchema_Apache)
				skeuoRoundTrip(arrayIntSchema_Skeuo) should equal(arrayIntSchema_Skeuo)
				
				// type-check --- of conversion
				apacheRoundTrip shouldBe a [SchemaAvro_Apache => SchemaAvro_Apache]
				scheme.hylo(algebra_SkeuoToApache, coalgebra_ApacheToSkeuo) shouldBe a [SchemaAvro_Apache => SchemaAvro_Apache]
				Util.getFuncTypeSubs(apacheRoundTrip) shouldEqual "SchemaAvro_Apache => SchemaAvro_Apache"
				
				skeuoRoundTrip shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Skeuo[_]]
				(apacheToSkeuoAvroSchema compose skeuoToApacheAvroSchema) shouldBe a [SchemaAvro_Skeuo[_] => SchemaAvro_Skeuo[_]]
				Util.getFuncTypeSubs(skeuoRoundTrip) shouldEqual "Fix[SchemaAvro_Skeuo] => Fix[SchemaAvro_Skeuo]"
			}
		}
	}
	println(s"STRING: strSchema_Apache.toString == 'string': ${strSchema_Apache.toString == "string"}")
	println(s"(ARRAY AVRO APACHE-STRING) apache avro Array (string): $arrayIntSchema_Apache")
	println(s"apache avro Enum (string): $enumSchema_Apache")

	// SchemaAvro_Apache --> skeuomorph avro schema
	// NOTE noting the types here when applying ana / cata morphisms
	/*val schemeAna: SchemaAvro_Apache ⇒ Fix[SchemaAvro_Skeuo] = scheme.ana(SchemaAvro_Skeuo.fromAvro)
	val avroToJson: Fix[SchemaAvro_Skeuo] ⇒ Json = scheme.cata(SchemaAvro_Skeuo.toJson)*/


	// NOTE: not implemented in skeuo code
	//println(s"ENUM JSON: enumApache -> skeuoAvro -> Json circe = ${avroToJson(schemeAna(enumApache))}")
	println(s"(ARRAY JSON CIRCE): arrayApache -> arraySkeuo adt -> array Json circe: ${avroSchemaToJsonString(apacheToSkeuoAvroSchema(arrayIntSchema_Apache))}")
	
	
	
	println(s"FUNC TYPE: avroSchemaToJsonString: ${Util.getFuncTypeSubs(avroSchemaToJsonString, keepPckgs, classesToSubs)}")


	// scheme.ana(SchemaAvro_Skeuo.fromAvro).apply(arrayApache)
	val arraySkeuo: Fix[SchemaAvro_Skeuo] = apacheToSkeuoAvroSchema(arrayIntSchema_Apache)
	println(s"(ARRAY AVRO SKEUO-ADT): skeuo avro array = $arraySkeuo")






	// scheme.ana(SchemaAvro_Skeuo.fromAvro).apply(enumApache)
	// scheme.ana(coalgebra_ApacheSkeuo).apply(enumApache)
	val enumSkeuo: Fix[SchemaAvro_Skeuo] = apacheToSkeuoAvroSchema(enumSchema_Apache)
	println(s"skeuo avro enum: ${enumSkeuo.toString}")
	println(s"skeuo avro enum unfix: ${enumSkeuo.unfix}")

	// inverse:

	//scheme.cata(algebra_SkeuoApache).apply(strSchema_Skeuo)
	
	//scheme.cata(algebra_SkeuoApache).apply(arraySkeuo)
	val arrayApacheBack: SchemaAvro_Apache = skeuoToApacheAvroSchema(arraySkeuo)
	println(s"arrayApacheBack = $arrayApacheBack")
	//scheme.cata(algebra_SkeuoApache).apply(enumSkeuo)
	val enumApacheBack: SchemaAvro_Apache = skeuoToApacheAvroSchema(enumSkeuo)
	println(s"enumApacheBack = $enumApacheBack")


	// --------------------------------------------------------------------------------------------------------------------------------



	// TODO left off here - plan idea outlined here:
	// 1) start from wiem el abadine's schemaF to learn to print out the schema from schmeaF using hylomorphism (trick: migrate matryoshka to droste) since hylo is different.
	// 2) feed it values see print out
	// 3) adapt to the skeuomorph AvroF -> SchemaAvro_Apache
	// 4) feed THAT values
	// 5) see the differences between the schema (#1)
	// 5) see how the avro string gets printed from the schema
	// 6) repeat for the skeuomorph json side
	// 7) compare syntax between json/avro strings (#2)


	/**
	 * Wiem El Abadine's `SchemaS` = https://github.com/wi101/recursion-schemes-lc2018/blob/master/src/main/scala/solutions/1-schema.scala#L11-L22
	 */

	/**
	 * Skeuomorph's `AvroF` = https://github.com/higherkindness/skeuomorph/blob/main/src/main/scala/higherkindness/skeuomorph/avro/schema.scala#L34-L122
	 */

	/**
	 * Zio's `Schema` =https://github.com/zio/zio-schema/blob/4e1e00193a59e5d3465fbb76433be5e680df21d7/zio-schema/shared/src/main/scala/zio/schema/Schema.scala#L287-L373
	 */

	// TODO - check what is "Fixed" type in zio / apache avro / skeuomorph - key insight tati says to howt he schemas differ between each other.

	// Fixed builder in org.apache.avro = https://github.com/apache/avro/blob/cdfd66fed1cb366400a41aa7dcbec19d1fad8a09/lang/java/avro/src/main/java/org/apache/avro/SchemaBuilder.java#L291

	// FixedSchema in org.apache.avro = https://github.com/apache/avro/blob/cdfd66fed1cb366400a41aa7dcbec19d1fad8a09/lang/java/avro/src/main/java/org/apache/avro/Schema.java#L1283

	// write fixed from a byte buffer (org.apache avro) = https://github.com/apache/avro/blob/57f1d5278427e2b095ec8d539d32bb9b309f9e07/lang/java/avro/src/main/java/org/apache/avro/io/Encoder.java#L183
	//read fixed from a byte buffer (org.apache.avro) = https://github.com/apache/avro/blob/57f1d5278427e2b095ec8d539d32bb9b309f9e07/lang/java/avro/src/main/java/org/apache/avro/io/Decoder.java#L158

	// GenericFixed (org.apache.avro) = https://github.com/apache/avro/blob/57f1d5278427e2b095ec8d539d32bb9b309f9e07/lang/java/avro/src/main/java/org/apache/avro/Conversions.java#L103

	// create fixed reader from writerschema (org.apache.avro) =https://github.com/apache/avro/blob/57f1d5278427e2b095ec8d539d32bb9b309f9e07/lang/java/avro/src/main/java/org/apache/avro/io/FastReaderBuilder.java#L500

	// writefixed (json encoder file, org.apache.avro) = https://github.com/apache/avro/blob/57f1d5278427e2b095ec8d539d32bb9b309f9e07/lang/java/avro/src/main/java/org/apache/avro/io/JsonEncoder.java#L225

	/// ---------------

	// Fixed in skeuomorph (avro adt) = https://github.com/higherkindness/skeuomorph/blob/main/src/main/scala/higherkindness/skeuomorph/avro/schema.scala#L118

	// Fixed in skeuomorph (mu adt) = https://github.com/higherkindness/skeuomorph/blob/485c49f55e955468053ef4f92ec6c33a33df031b/src/main/scala/higherkindness/skeuomorph/mu/schema.scala#L40

	// Fixed in skeuomorph (protobuf adt) = https://github.com/higherkindness/skeuomorph/blob/485c49f55e955468053ef4f92ec6c33a33df031b/src/main/scala/higherkindness/skeuomorph/protobuf/schema.scala#L81

	// Fixed in skeuomorph (protobuf -> mu adt) = https://github.com/higherkindness/skeuomorph/blob/485c49f55e955468053ef4f92ec6c33a33df031b/src/main/scala/higherkindness/skeuomorph/mu/Transform.scala#L41-L44

	// Fixed (mu -> avro schema) = https://github.com/higherkindness/skeuomorph/blob/485c49f55e955468053ef4f92ec6c33a33df031b/src/main/scala/higherkindness/skeuomorph/avro/Protocol.scala#L136

	// Fixed in skeuomorph (apache avro FIXED --> AvroF Fixed)  = https://github.com/higherkindness/skeuomorph/blob/main/src/main/scala/higherkindness/skeuomorph/avro/schema.scala#L229-L235

	// skeuomorph Fixed --> io.circe.Json's Fixed string = https://github.com/higherkindness/skeuomorph/blob/main/src/main/scala/higherkindness/skeuomorph/avro/schema.scala#L275-L279


}
