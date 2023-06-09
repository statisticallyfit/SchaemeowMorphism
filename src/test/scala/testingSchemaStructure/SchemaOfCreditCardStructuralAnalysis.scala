//package testingSchemaStructure
//
//
//import org.scalatest.featurespec.AnyFeatureSpecLike
//import org.specs2.matcher.{MustThrownMatchers, ShouldThrownMatchers}
//import org.specs2.mutable.Specification
//
//import scala.tools.nsc.Reporting.WarningCategory.Feature
//
//
///*import scala.reflect.{ClassTag, classTag}
//import scala.reflect.runtime.universe._
//import zio._
//import zio.schema.codec.DecodeError
//import zio.schema.{DeriveSchema, Schema, TypeId}
//import zio.stream.ZPipeline*/
//
//
//import ZioSchemaExamples.Example1_PaymentWireTransfer._
//import Domain._
//import Domain.PaymentMethod._
//import zio.schema.Schema._
//import ManualConstruction._
//import utilTest.GeneralTestUtil
//
//
///**
// *
// */
//class SchemaOfCreditCardStructuralAnalysis extends Specification /*with AnyFeatureSpecLike with MustThrownMatchers
//	with ShouldThrownMatchers*/
//	{
//
//
//  "Component Test: schemaPaymentMethodCreditCard" should {
//
//    "show parameter (function) types" in {
//
//	    getLongFuncType(typeId_creditCard) must beEqualTo("zio.schema.TypeId")
//      getShortFuncType(typeId_creditCard) must beEqualTo("TypeId")
//
//
//      val longName1 = "zio.schema.Schema.Field[Example1_PaymentWireTransfer.Domain.PaymentMethod.CreditCard,String]"
//	    getLongFuncType(field1_creditCard) must beEqualTo(longName1)
//      getShortFuncType(field1_creditCard) must beEqualTo("Field[CreditCard,String]")
//
//      val longName2 = "zio.schema.Schema.Field[Example1_PaymentWireTransfer.Domain.PaymentMethod.CreditCard,zio.RuntimeFlags]"
//	    getLongFuncType(field2_creditCard) must beEqualTo(longName2)
//      getShortFuncType(field2_creditCard) must beEqualTo("Field[CreditCard,RuntimeFlags]")
//
//
//      val longName3 = "zio.schema.Schema.Field[Example1_PaymentWireTransfer.Domain.PaymentMethod.CreditCard,zio.RuntimeFlags]"
//	    getLongFuncType(field3_creditCard) must beEqualTo(longName3)
//      getShortFuncType(field3_creditCard) must beEqualTo("Field[CreditCard,RuntimeFlags]")
//
//
//      val longName4 = "(String, Int, Int) => Example1_PaymentWireTransfer.Domain.PaymentMethod.CreditCard"
//	    getLongFuncType(construct0_creditCard) must beEqualTo(longName4)
//      getShortFuncType(construct0_creditCard) must beEqualTo("(String, Int, Int) => CreditCard")
//
//
//      val longName5: String = "Component "
//	    getLongFuncType(construct0_creditCard) must beEqualTo(longName4)
//      getShortFuncType(construct0_creditCard) must beEqualTo("(String, Int, Int) => CreditCard")
//    }
//  }
//
//}
