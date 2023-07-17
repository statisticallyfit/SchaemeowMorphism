package testData.schemaData.avroData.skeuoData

import higherkindness.skeuomorph.avro.{AvroF ⇒ SchemaAvro_Skeuo}
import SchemaAvro_Skeuo._
import SchemaAvro_Skeuo.{Field ⇒ FieldAvro}

import io.circe.Json

/**
 *
 */
object Data {

	// TODO - to put strin g/ int / null in place of T? or to leave as Nothing?
	val nullAvro_Skeuo: SchemaAvro_Skeuo[Null] = TNull()
	val nullAvro_SkeuoC: SchemaAvro_Skeuo[Json] = TNull()
	
	val strAvro_Skeuo: SchemaAvro_Skeuo[String] = TString()
	val intAvro_Skeuo: SchemaAvro_Skeuo[Int] = TInt()
	
	val arrayIntAvro_Skeuo: SchemaAvro_Skeuo[SchemaAvro_Skeuo[Int]] = TArray(intAvro_Skeuo)
	val arrayStrAvro_Skeuo: SchemaAvro_Skeuo[SchemaAvro_Skeuo[String]] = TArray(strAvro_Skeuo)
	
	// NOTE; trying to copy avro schema "RawCityMesh - devs - datasource
	// TODO what is the inner type parameter?
	/*val recordAvro_Skeuo/*: SchemaAvro_Skeuo[SchemaAvro_Skeuo[Nothing]]*/ = TRecord("Location", namespace = Some("location namespace"), aliases = List("a1", "a2"), doc = None, fields = List(
		FieldAvro/*[SchemaAvro_Skeuo[Nothing]]*/(name = "id", aliases = List("i1", "i2"), doc = None, order = None, tpe = TString()),
		
		FieldAvro/*[SchemaAvro_Skeuo[Nothing]]*/(name = "position", aliases = List(),
			doc = None, order = None, tpe = TRecord(
				name = "Position", namespace = Some("position namespace"), aliases = List(), doc = None, fields = List(
					FieldAvro(name = "coordinates", aliases = List(), doc = None, order = None, tpe = TArray(TFloat())),
					FieldAvro(name = "type", aliases = List(), doc = None, order = None, tpe = TString())
				)
			))
	))*/
	
	val recordStringAvro_Skeuo: SchemaAvro_Skeuo[SchemaAvro_Skeuo[Nothing]] = TRecord(
		name = "StringRecord",
		namespace = Some("StringNamespace"),
		aliases = List("a1", "a2"),
		doc = None,
		fields = List(
			FieldAvro(
				name = "stringField1",
				aliases = List(),
				doc = None,
				order = None,
				tpe = TString()
			)
		)
	)
}
