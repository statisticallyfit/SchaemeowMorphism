package conversionsOfSchemaADTs.avro_json.parsing


import higherkindness.droste.data.Fix
import higherkindness.droste._
import higherkindness.droste.syntax.all._

import org.scalatest.GivenWhenThen
//import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should._

import conversionsOfSchemaADTs.avro_json.skeuo_skeuo.Skeuo_Skeuo._
import conversionsOfSchemaADTs.avro_avro.skeuo_apache.Skeuo_Apache._

import io.circe.{Json ⇒ JsonCirce}
import io.circe.Decoder
import higherkindness.skeuomorph.openapi.JsonDecoders._

import higherkindness.skeuomorph.avro.{AvroF ⇒ AvroSchema_S}
import AvroSchema_S._

import higherkindness.skeuomorph.openapi.{JsonSchemaF ⇒ JsonSchema_S}
import JsonSchema_S._


import testData.schemaData.avroData.skeuoData.Data._
import testData.schemaData.jsonData.skeuoData.Data._

import scala.reflect.runtime.universe._

import utilMain.UtilMain
import utilMain.UtilMain.implicits._
import utilMain.utilJson.utilSkeuo_ParseJsonSchemaStr.UnsafeParser._

import conversionsOfSchemaADTs.avro_json.parsing.ParseChecker


/**
 *
 */
class ParseDecode_Primitives_JsonStr_Circe_JsonSkeuo extends AnyFunSuite with Matchers {
	
	
	def testStructure(title: String, givenRawJsonStr: String, checkerJsonSkeuo: Fix[JsonSchema_S]): Unit = {
		
		test(title) {
			
			val rawJsonStr: String = givenRawJsonStr.stripMargin.trim
			
			val circeJsonStr: JsonCirce = unsafeParse(rawJsonStr)
			
			// value-check
			circeJsonStr.noSpaces shouldEqual rawJsonStr.noSpaces
			// type check
			circeJsonStr shouldBe a[JsonCirce]
			
			
			val decodedJsonSkeuo: Option[Fix[JsonSchema_S]] = ParseChecker.strToCirceToSkeuoJson(rawJsonStr)
			
			// value check
			decodedJsonSkeuo shouldEqual Some(checkerJsonSkeuo)
			// type check
			decodedJsonSkeuo shouldBe a[JsonSchema_S[_]]
			
			
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
	
	testStructure("string", givenRawJsonStr =
		"""
		  |{
		  |  "type": "string"
		  |}
		  |""".stripMargin,
		checkerJsonSkeuo = strJson_Fix_S
	)
	
	testStructure("integer", givenRawJsonStr =
		"""
		  |{
		  |  "type": "integer"
		  |}
		  |""".stripMargin
	)
	
	testStructure("array of string", givenRawJsonStr =
		"""
		  |{
		  |  "type": "array",
		  |  "items": {
		  |    "type": "string"
		  |  }
		  |}
		  |""".stripMargin
	)
	
	testStructure("string", givenRawJsonStr =
		"""
		  |{
		  |  "type": "string"
		  |}
		  |""".stripMargin
	)
	
	
}
