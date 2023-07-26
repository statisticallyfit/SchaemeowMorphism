package conversionsOfSchemaADTs.avro_json.parsing


import higherkindness.droste.data.Fix
//import org.scalatest.featurespec.AnyFeatureSpec
import higherkindness.skeuomorph.openapi.{JsonSchemaF ⇒ JsonSchema_S}
import io.circe.{Json ⇒ JsonCirce}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should._
import testData.rawstringData.jsonData.Data._
import testData.schemaData.jsonData.skeuoData.Data._
import utilMain.UtilMain.implicits._
import utilMain.utilJson.utilSkeuo_ParseJsonSchemaStr.UnsafeParser._

//import conversionsOfSchemaADTs.avro_json.parsing.ParseChecker


/**
 *
 */
class ParseDecode_Primitives_JsonStr_Circe_JsonSkeuo_Specs extends AnyFunSuite with Matchers {
	
	
	def testStructure(title: String, givenRawJsonStr: String, checkerJsonSkeuo: Fix[JsonSchema_S]): Unit = {
		
		test(title) {
			
			val rawJsonStr: String = givenRawJsonStr.stripMargin.trim
			
			val circeJsonStr: JsonCirce = unsafeParse(rawJsonStr)
			
			// value-check
			circeJsonStr.noSpaces shouldEqual rawJsonStr.noSpaces
			// type check
			circeJsonStr shouldBe a[JsonCirce]
			
			
			val decodedJsonSkeuo: Option[Fix[JsonSchema_S]] = ParseStringToCirceToADT.strToCirceToSkeuoJson(rawJsonStr)
			
			// value check
			decodedJsonSkeuo shouldEqual Some(checkerJsonSkeuo)
			// type check
			decodedJsonSkeuo.get shouldBe a[JsonSchema_S[_]]
			
			
			info(s"raw json str = \n$rawJsonStr")
			//info(s"result json circe = \n$circeJsonStr")
			info(s"result json circe (removeColonSpace) = \n${circeJsonStr.removeSpaceBeforeColon}")
			info(s"circeJsonStr.noSpaces = ${circeJsonStr.noSpaces}")
			info(s"raw json str no space = ${rawJsonStr.noSpaces}")
			
			/*info(s"raw length | trimmed = ${rawJsonStr.length} | ${rawJsonStr.trim.length}")
			info(s"circe length | trimmed = ${circeJsonStr.removeSpaceBeforeColon.length} | ${circeJsonStr.removeSpaceBeforeColon.trim.length}")*/
			
			info(s"DECODING: Json circe --> SKEUO: $decodedJsonSkeuo")
		}
	}
	
	testStructure("null",
		givenRawJsonStr = nullJson_R,
		checkerJsonSkeuo = nullJson_Fix_S
	)
	
	
	testStructure("integer",
		givenRawJsonStr = intJson_R,
		checkerJsonSkeuo = intJson_Fix_S
	)
	
	testStructure("string",
		givenRawJsonStr = strJson_R,
		checkerJsonSkeuo = strJson_Fix_S
	)
	
	testStructure("boolean",
		givenRawJsonStr = strJson_R,
		checkerJsonSkeuo = strJson_Fix_S
	)
	
	testStructure("long",
		givenRawJsonStr = strJson_R,
		checkerJsonSkeuo = strJson_Fix_S
	)
	
	testStructure("float",
		givenRawJsonStr = strJson_R,
		checkerJsonSkeuo = strJson_Fix_S
	)
	
	testStructure("double",
		givenRawJsonStr = strJson_R,
		checkerJsonSkeuo = strJson_Fix_S
	)
	
	testStructure("bytes",
		givenRawJsonStr = strJson_R,
		checkerJsonSkeuo = strJson_Fix_S
	)
	
	testStructure("array of string",
		givenRawJsonStr = array1StrJson_R,
		checkerJsonSkeuo = array1StrJson_Fix_S
	)
	
	testStructure("array (triple) of int)",
		givenRawJsonStr = array1StrJson_R,
		checkerJsonSkeuo = array1StrJson_Fix_S
	)
	
	testStructure("map",
		givenRawJsonStr = strJson_R,
		checkerJsonSkeuo = strJson_Fix_S
	)
	
	testStructure("record (e.g. Position, from citymesh-datasource-devs json file, generated by autoschema)",
		givenRawJsonStr = recordExampleJson_Position_R,
		checkerJsonSkeuo = recordExampleJson_Position_Fix_S
	)
	
	testStructure("record (e.g. Location, from citymesh-datasource-devs json file, generated by autoschema)",
		givenRawJsonStr = recordExampleJson_Location_R,
		checkerJsonSkeuo = recordExampleJson_Location_Fix_S
	)
	
	// TODO FILL IN
	//	testStructure("enum",
	//		givenRawJsonStr = strJson_R,
	//		checkerJsonSkeuo = strJson_Fix_S
	//	)
	//
	//	testStructure("fixed",
	//		givenRawJsonStr = strJson_R,
	//		checkerJsonSkeuo = strJson_Fix_S
	//	)
	//
	//	testStructure("date",
	//		givenRawJsonStr = strJson_R,
	//		checkerJsonSkeuo = strJson_Fix_S
	//	)
	//
	//	testStructure("timestamp-millis",
	//		givenRawJsonStr = strJson_R,
	//		checkerJsonSkeuo = strJson_Fix_S
	//	)
	//
	//	testStructure("time-millis",
	//		givenRawJsonStr = strJson_R,
	//		checkerJsonSkeuo = strJson_Fix_S
	//	)
	//
	//	testStructure("decimal",
	//		givenRawJsonStr = strJson_R,
	//		checkerJsonSkeuo = strJson_Fix_S
	//	)
}
