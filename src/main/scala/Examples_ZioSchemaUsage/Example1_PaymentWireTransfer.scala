package Examples_ZioSchemaUsage


import zio._
import zio.schema.Schema._
import zio.schema.{TypeId, Schema ⇒ SchemaZIO}


/**
 * SOURCE CODE = https://github.com/zio/zio-schema/blob/main/zio-schema-examples/shared/src/main/scala/dev/zio/schema/example/example1/Example1.scala
 */
object Example1_PaymentWireTransfer {
	
	
	object Domain {
		sealed trait PaymentMethod
		
		final case class Person(name: String, age: Int)
		
		final case class Customer(person: Person, paymentMethod: PaymentMethod)
		
		object PaymentMethod {
			final case class CreditCard(number: String, expirationMonth: Int, expirationYear: Int) extends PaymentMethod
			
			final case class WireTransfer(accountNumber: String, bankCode: String) extends PaymentMethod
		}
	}
	
	//import dev.zio.schema.example.example1.Domain._
	
	import Domain.PaymentMethod._
	import Domain._
	
	
	object ManualConstruction {
		
		val typeId_creditCard: TypeId = TypeId.parse("dev.zio.schema.example.example1.Domain.PaymentMethod.CreditCard")
		
		val field1_creditCard: Field[CreditCard, String] = SchemaZIO.Field[CreditCard, String](
			name0 = "number",
			schema0 = SchemaZIO.primitive[String],
			//get0: (R => A) --- (CreditCard => String)
			get0 = (r: CreditCard) => r.number,
			// set0: (R, A) => R ----- (CreditCard, String) => CreditCard
			set0 = (cc: CreditCard, s: String) => cc.copy(number = s)
		)
		
		val field2_creditCard: Field[CreditCard, RuntimeFlags] = SchemaZIO.Field[CreditCard, Int](
			name0 = "expirationMonth",
			SchemaZIO.primitive[Int],
			get0 = (cc: CreditCard) => cc.expirationMonth,
			set0 = (cc: CreditCard, rf: RuntimeFlags) => cc.copy(expirationMonth = rf)
		)
		
		val field3_creditCard: Field[CreditCard, RuntimeFlags] = SchemaZIO.Field[CreditCard, Int](
			"expirationYear",
			SchemaZIO.primitive[Int],
			get0 = (cc: CreditCard) => cc.expirationYear,
			set0 = (cc: CreditCard, rf: RuntimeFlags) => cc.copy(expirationYear = rf)
		)
		
		val construct0_creditCard: (String, Int, Int) => CreditCard =
			(number: String, expirationMonth: Int, expirationYear: Int) =>
				PaymentMethod.CreditCard(number, expirationMonth, expirationYear)
		
		//TODO ASSERT  properties of zioschema constrcuts - e.g. that caseclass3 has 3 fields and a construct (assert type
		// params using reflection/typetag)
		val schemaPaymentMethodCreditCard: SchemaZIO[CreditCard] = SchemaZIO.CaseClass3[String, Int, Int, CreditCard](
			id0 = typeId_creditCard,
			field01 = field1_creditCard,
			field02 = field2_creditCard,
			field03 = field3_creditCard,
			construct0 = construct0_creditCard
		)
		
		
		// -----------------------------------------
		val schemaPaymentMethodWireTransfer: SchemaZIO[WireTransfer] = SchemaZIO.CaseClass2[String, String, WireTransfer](
			TypeId.parse("dev.zio.schema.example.example1.Domain.PaymentMethod.WireTransfer"),
			field01 = SchemaZIO.Field[WireTransfer, String](
				"accountNumber",
				SchemaZIO.primitive[String],
				get0 = _.accountNumber,
				set0 = (p, v) => p.copy(accountNumber = v)
			),
			field02 = SchemaZIO.Field[WireTransfer, String](
				"bankCode",
				SchemaZIO.primitive[String],
				get0 = _.bankCode,
				set0 = (p, v) => p.copy(bankCode = v)
			),
			construct0 = (number, bankCode) => PaymentMethod.WireTransfer(number, bankCode)
		)
		
		val schemaPaymentMethod: SchemaZIO[PaymentMethod] =
			SchemaZIO.Enum2[PaymentMethod.CreditCard, PaymentMethod.WireTransfer, PaymentMethod](
				id = TypeId.parse("dev.zio.schema.example.example1.Domain.PaymentMethod"),
				case1 = Case[PaymentMethod, PaymentMethod.CreditCard](
					id = "CreditCard",
					schema = schemaPaymentMethodCreditCard,
					unsafeDeconstruct = pm => pm.asInstanceOf[PaymentMethod.CreditCard],
					construct = pc => pc.asInstanceOf[PaymentMethod],
					isCase = _.isInstanceOf[PaymentMethod.CreditCard],
					annotations = Chunk.empty
				),
				case2 = Case[PaymentMethod, PaymentMethod.WireTransfer](
					id = "WireTransfer",
					schema = schemaPaymentMethodWireTransfer,
					unsafeDeconstruct = pm => pm.asInstanceOf[PaymentMethod.WireTransfer],
					construct = pc => pc.asInstanceOf[PaymentMethod],
					isCase = _.isInstanceOf[PaymentMethod.WireTransfer],
					annotations = Chunk.empty
				),
				annotations = Chunk.empty
			)
	}
}
