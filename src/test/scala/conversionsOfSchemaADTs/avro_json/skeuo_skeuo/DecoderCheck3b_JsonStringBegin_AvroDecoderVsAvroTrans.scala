package conversionsOfSchemaADTs.avro_json.skeuo_skeuo


import conversionsOfSchemaADTs.avro_avro.skeuo_apache.Skeuo_Apache._
import conversionsOfSchemaADTs.avro_json.skeuo_skeuo.Skeuo_Skeuo.ByTrans._
import higherkindness.droste.data.Fix
import higherkindness.skeuomorph.avro.{AvroF ⇒ AvroSchema_S}
import higherkindness.skeuomorph.openapi.{JsonSchemaF ⇒ JsonSchema_S}
import io.circe.{Json ⇒ JsonCirce}
import io.circe.Decoder.Result
import utilMain.utilJson.utilSkeuo_ParseJsonSchemaStr.UnsafeParser._
import conversionsOfSchemaADTs.avro_json.parsing.ParseADTToCirceToADT._
import conversionsOfSchemaADTs.avro_json.parsing.ParseStringToCirceToADT._
import org.apache.avro.{Schema ⇒ AvroSchema_A}
import org.scalatest.Assertion
import org.scalatest.Inspectors._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should._
import utilMain.UtilMain.implicits._


/**
 *
 */
case class DecoderCheck3b_JsonStringBegin_AvroDecoderVsAvroTrans(implicit imp: ImplicitArgs)
	extends AnyFunSpec with DecoderChecks with Matchers {
	
	import imp._
	
	/*val jsonCirceFromStr: JsonCirce = unsafeParse(rawJsonStr)
	val skeuoAvro_fromDecoder: Result[Fix[AvroSchema_S]] = decodeJsonStringToCirceToAvroSkeuo(rawJsonStr)
	*/
	
	val step: Stepping = Stepping(rawAvroStr, rawJsonStr)
	val sa: AvroStringDecodeInfo = step.avroStep
	val sj: JsonStringDecodeInfo = step.jsonStep
	val sai: SkeuoDecodeInfo = step.avroStep.skInfo
	val sji: SkeuoDecodeInfo = step.jsonStep.skInfo
	
	
	val skeuoJson_fromTransOfGivenAvroSkeuo: Fix[JsonSchema_S] = avroToJson_byCataTransAlg(avroFixS)
	val skeuoAvro_fromTransOfGivenJsonSkeuo: Fix[AvroSchema_S] = jsonToAvro_byAnaTransCoalg(jsonFixS)
	
	def printOuts(): Unit = {
		
		// RULE: if starting from avro string in the test, then use avrostep, else use jsonstep.
		
		info(s"\n-----------------------------------------------------------")
		info(s"\nCHECKER 3b: " +
		     s"\nraw-json-str (input) -> json-circe -> skeuo-avro (decoder output) vs. skeuo-avro (trans output)" +
		     s"\n|\t Reason: find out how json-str translates to avro-adt  + get common denominator (skeuo-avro), from avro-side." +
		     s"\n|\t (from avro-side) " +
		     s"\n|\t (starting from: json-str)" +
		     s"\n--- raw-json-str (given): \n$rawJsonStr" +
		     s"\n--> json-circe (from json-str): \n${sj.interimCirce.manicure}" +
		     s"\n    json-circe (from json-str): \n${sa.interimCirce.manicure}" +
		     s"\n--> skeuo-avro (avro-decoder output): ${sai.skeuoAvro_fromRaw}" +
		     s"\n    skeuo-avro (json-decoder output): ${sji.skeuoAvro_fromRaw}" +
		     s"\n    skeuo-avro (trans output): ${skeuoAvro_fromTransOfGivenJsonSkeuo}"
		)
	}
	
	
	override def checking(): Unit = {
		import Checking._
		
		// Spirit of the test
		equalityOfAvroSkeuoFromDecoderAndTrans()
	}
	
	object Checking {
		
		// Spirit of the test
		
		def equalityOfAvroSkeuoFromDecoderAndTrans(): Assertion = {
			
			forEvery(List(
				
				Decoding.decodeAvroStringToCirceToAvroSkeuo(rawAvroStr, rawJsonStr),
				
				sai.skeuoAvro_fromDecodeAvroSkeuo,
				sai.skeuoAvro_fromRaw,
				
				Decoding.decodeJsonStringToCirceToAvroSkeuo(rawAvroStr, rawJsonStr),
				
				sji.skeuoAvro_fromDecodeAvroSkeuo,
				sji.skeuoAvro_fromRaw,
				
				Right(skeuoAvro_fromTransOfGivenJsonSkeuo)
			)
			) {
				ska ⇒ ska.right.get should equal(avroFixS)
			}
		}
	}
	
}
