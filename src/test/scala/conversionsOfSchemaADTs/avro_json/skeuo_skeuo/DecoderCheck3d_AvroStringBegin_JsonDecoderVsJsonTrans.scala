package conversionsOfSchemaADTs.avro_json.skeuo_skeuo


import conversionsOfSchemaADTs.avro_avro.skeuo_apache.Skeuo_Apache._
import conversionsOfSchemaADTs.avro_json.skeuo_skeuo.Skeuo_Skeuo.ByTrans._
import higherkindness.droste.data.Fix
import higherkindness.skeuomorph.avro.{AvroF ⇒ AvroSchema_S}
import higherkindness.skeuomorph.openapi.{JsonSchemaF ⇒ JsonSchema_S}
import org.apache.avro.{Schema ⇒ AvroSchema_A}
import io.circe.Decoder.Result
import io.circe.{Json ⇒ JsonCirce}
import utilMain.utilJson.utilSkeuo_ParseJsonSchemaStr.UnsafeParser._
import conversionsOfSchemaADTs.avro_json.parsing.ParseADTToCirceToADT._
import conversionsOfSchemaADTs.avro_json.parsing.ParseStringToCirceToADT._
import org.scalatest.Assertion
import org.scalatest.Inspectors._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should._
import utilMain.UtilMain.implicits._


/**
 *
 */
case class DecoderCheck3d_AvroStringBegin_JsonDecoderVsJsonTrans(implicit imp: ImplicitArgs)
	extends AnyFunSpec with DecoderChecks with Matchers {
	
	import imp._
	
	
	val step: Stepping = new Stepping(Some(rawAvroStr), Some(rawJsonStr))
	val sa: AvroStringDecodeInfo = step.avroInfoOpt.get
	val sj: JsonStringDecodeInfo = step.jsonInfoOpt.get
	val sai: SkeuoDecodeInfo = sa.skInfo
	val sji: SkeuoDecodeInfo = sj.skInfo
	
	
	val skeuoJson_fromTransOfGivenAvroSkeuo: Fix[JsonSchema_S] = avroToJson_byCataTransAlg(avroFixS)
	val skeuoAvro_fromTransOfGivenJsonSkeuo: Fix[AvroSchema_S] = jsonToAvro_byAnaTransCoalg(jsonFixS)
	
	def showResults(): String = {
		
		// RULE: if starting from avro string in the test, then use avrostep, else use jsonstep.
		
		var infoVar: String = s"\n-----------------------------------------------------------"
		infoVar += (s"\nCHECKER 3d: " +
			
		     s"\nraw-avro-str (input) -> (skeuo-avro) -> json-circe -> skeuo-json (decoder output) vs. skeuo-json (trans output)" +
			
		     s"\n|\t Reason: find out how avro-str translates to json-adt  + get common denominator (skeuo-json), from json-side." +
		     s"\n|\t (from json-side) " +
		     s"\n|\t (starting from: avro-str)" +
		     
		     s"\n" +
		     s"\n--- raw-avro-str (given): \n$rawAvroStr" +
		     s"\n--> (apache-avro): \n${sa.parsedApacheAvroStr}" +
		     s"\n--> (skeuo-avro):                     \t${sa.skeuoAvro_fromApache}" +
		     s"\n--> json-circe (from avro-str): \n${sa.interimCirce_fromAvroSKeuo.manicure}" +
		     s"\n    json-circe (from json-str): \n${sj.interimCirce_fromJsonStr.manicure}" +
		     s"\n--> skeuo-json (avro-decoder output): \t${sai.skeuoJson_fromRaw}" +
		     s"\n    skeuo-json (json-decoder output): \t${sji.skeuoJson_fromRaw}" +
		     s"\n    skeuo-json (trans output):        \t${skeuoJson_fromTransOfGivenAvroSkeuo}")
		
		infoVar
	}
	
	
	override def checking(): Unit = {
		import Checking._
		
		// Spirit of the test
		equalityOfJsonSkeuoFromDecoderAndTrans()
		
		
	}
	
	object Checking {
		
		// Spirit of the test
		
		def equalityOfJsonSkeuoFromDecoderAndTrans(): Assertion = {
			
			forEvery(List(
				
				DecodingStr.decodeAvroStringToCirceToJsonSkeuo(rawAvroStr, Some(rawJsonStr)),
				
				sai.skeuoJson_fromDecodeAvroSkeuo,
				sai.skeuoJson_fromRaw,
				
				DecodingStr.decodeJsonStringToCirceToJsonSkeuo(rawJsonStr, Some(rawAvroStr)),
				
				sji.skeuoJson_fromDecodeAvroSkeuo,
				sji.skeuoJson_fromRaw,
				
				Right(skeuoJson_fromTransOfGivenAvroSkeuo)
			)) {
				skj ⇒ skj.right.get should equal (jsonFixS)
			}
		}
		
		
	}
	
	
}

