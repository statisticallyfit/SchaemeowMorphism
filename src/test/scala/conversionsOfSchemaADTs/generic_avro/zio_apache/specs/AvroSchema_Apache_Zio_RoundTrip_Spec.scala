package conversionsOfSchemaADTs.generic_avro.zio_apache.specs

import org.apache.avro.{Schema ⇒ AvroSchema_A}
import org.specs2._
import org.specs2.specification.core.SpecStructure
import testData.schemaData.avroData.apacheData.Data._
import testData.schemaData.genericSchemaData.zioData.Data._
import zio.schema.codec.AvroCodec
import zio.schema.{Schema ⇒ ZioGenericSchema}


/**
 *
 */
class AvroSchema_Apache_Zio_RoundTrip_Spec extends Specification with ScalaCheck {
	
	
	def is: SpecStructure =
		s2"""
			Converting between ZIO's generic-schema-ADT and Skeuomorph's avro-schema-ADT
		"""
	
	
	println("\n----------------------------------------------------------------")
	println("Printing : scala case class --> zio schema --> apache avro string ")
	
	
	// TODO left off here - use another case class (fruit banana example)
	//val tangeloZioGenericSchema: ZioGenericSchema[Tangelo] = DeriveSchema.gen[Tangelo]
	val tangeloApacheAvroSchema: Either[String, AvroSchema_A] = AvroCodec.encodeToApacheAvro(tangeloGeneric_Zio)
	val tangeloApacheAvroString: Either[String, String] = AvroCodec.encode(tangeloGeneric_Zio)
	
	println(s"ZioGenericSchema tangelo = $tangeloGeneric_Zio")
	println(s"avro adt tangelo (apache) = $tangeloApacheAvroSchema")
	println(s"avro string tangelo = $tangeloApacheAvroString")
	
	
	/// --------------------------------------------
	
	
	//val arrayZio: Either[String, Any] = apacheAvroSchemaToZioGenericSchema(arrayApache)
	val arrayZio2: Either[String, ZioGenericSchema[_]] = AvroCodec.decodeFromApacheAvro(arrayIntAvro_A)
	//println(s"(ARRAY ZIO-ADT): zio avro array = $arrayZio")
	println(s"(ARRAY ZIO-ADT): zio avro array = $arrayZio2")
	
}
