package conversionsOfSchemaADTs.avro_json.skeuo_skeuo

import conversionsOfSchemaADTs.avro_avro.skeuo_apache.Skeuo_Apache._

import conversionsOfSchemaADTs.avro_json.parsing.ParseADTToCirceToADT._
import conversionsOfSchemaADTs.avro_json.parsing.ParseADTToCirceToADT
import ParseADTToCirceToADT.LibFuncs._

import conversionsOfSchemaADTs.avro_json.skeuo_skeuo.Skeuo_Skeuo.ByTrans.avroToJson_byCataTransAlg
import higherkindness.droste.data.Fix
import higherkindness.skeuomorph.avro.{AvroF ⇒ AvroSchema_S}
import higherkindness.skeuomorph.openapi.{JsonSchemaF ⇒ JsonSchema_S}
import io.circe.{Json ⇒ JsonCirce}
import org.apache.avro.{Schema ⇒ AvroSchema_A}
import org.scalatest.Assertion
import org.scalatest.Inspectors._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should._
import utilMain.UtilMain.implicits._


/**
 *
 */
// Using implicitargs class to remove the need for passing all the arguments explicitly
case class DecoderCheck1_Canonical_AvroStringToJsonString(implicit imp: ImplicitArgs )
	extends AnyFunSpec with DecoderChecks with Matchers {

	import imp._

	//val apacheAvro: AvroSchema_A = skeuoToApacheAvroSchema(avroFixS)
	//val apacheAvroStr: String = apacheAvro.toString(true).removeSpaceBeforeColon
	val apacheAvro_fromStr: AvroSchema_A = new AvroSchema_A.Parser().parse(rawAvroStr) // TODO Option for parsing failure?

	val skeuoAvro_fromApacheStr: Fix[AvroSchema_S] = apacheToSkeuoAvroSchema(apacheAvro_fromStr)

	val skeuoJson_fromTransOfAvroStr: Fix[JsonSchema_S] = avroToJson_byCataTransAlg(skeuoAvro_fromApacheStr)

	val skeuoJson_fromTransOfAvroSkeuo: Fix[JsonSchema_S] = avroToJson_byCataTransAlg(avroFixS)

	val circeJson_fromTransOfJsonSkeuoOfAvroStr: JsonCirce = libRenderAltered(skeuoJson_fromTransOfAvroStr)
	//val circeJson_fromJsonSkeuo: JsonCirce = libRenderAltered(skeuoJson_trans_fromApache)


	def showResults(): String = {

		var infoVar: String = s"\n-----------------------------------------------------------"

		infoVar += s"\n\nCANONICAL way: apache-avro-str ---> skeuo-avro --> skeuo-json --> json-circe-str"
		infoVar += s"\n"
		infoVar += s"\n--- apache-avro-str (given): \n${rawAvroStr}"
		infoVar += s"\n--> skeuoAvro:                  \t$skeuoAvro_fromApacheStr"
		infoVar += s"\n--> skeuoJson (from str-trans): \t$skeuoJson_fromTransOfAvroStr"
		infoVar += s"\n    skeuoJson (from adt-trans): \t$skeuoJson_fromTransOfAvroSkeuo"
		infoVar += s"\n--> json-circe (from trans): \n${circeJson_fromTransOfJsonSkeuoOfAvroStr.manicure}"

		infoVar
	}

	def checking(): Unit = {

		import Checking._

		parsingAvroStrToApacheToSkeuoYieldsCorrectAvroSkeuo()
		transOfApacheAvroOrSkeuoAvroShouldYieldSameJsonSkeuo()
		transLeadsToCorrectCirce()
	}

	object Checking {
		def parsingAvroStrToApacheToSkeuoYieldsCorrectAvroSkeuo(): Assertion = {
			skeuoAvro_fromApacheStr shouldEqual avroFixS
		}


		def transOfApacheAvroOrSkeuoAvroShouldYieldSameJsonSkeuo(): Assertion = {
			forEvery(List(
				skeuoJson_fromTransOfAvroStr,
				skeuoJson_fromTransOfAvroSkeuo
			)) {
				js ⇒ js should equal(jsonFixS)
			}
		}

		def transLeadsToCorrectCirce(): Assertion = {
			circeJson_fromTransOfJsonSkeuoOfAvroStr should equal(jsonCirceCheck)
		}
	}
}
