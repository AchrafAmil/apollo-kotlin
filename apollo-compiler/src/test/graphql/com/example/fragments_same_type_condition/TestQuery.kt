// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragments_same_type_condition

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.fragments_same_type_condition.adapter.TestQuery_ResponseAdapter
import com.example.fragments_same_type_condition.fragment.DroidDetails1
import com.example.fragments_same_type_condition.fragment.DroidDetails2
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data> {
  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  override fun name(): String = OPERATION_NAME

  override fun adapter(): ResponseAdapter<Data> = TestQuery_ResponseAdapter
  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet(null, TestQuery_ResponseAdapter.RESPONSE_FIELDS)
  )
  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    /**
     * A character from the Star Wars universe
     */
    interface Hero {
      val __typename: String

      interface Droid : Hero, DroidDetails1, DroidDetails2 {
        override val __typename: String

        /**
         * What others call this droid
         */
        override val name: String

        /**
         * This droid's primary function
         */
        override val primaryFunction: String?
      }

      data class DroidHero(
        override val __typename: String,
        /**
         * What others call this droid
         */
        override val name: String,
        /**
         * This droid's primary function
         */
        override val primaryFunction: String?
      ) : Hero, Droid, DroidDetails1, DroidDetails2

      data class OtherHero(
        override val __typename: String
      ) : Hero

      companion object {
        fun Hero.asDroid(): Droid? = this as? Droid

        fun Hero.droidDetails1(): DroidDetails1? = this as? DroidDetails1

        fun Hero.droidDetails2(): DroidDetails2? = this as? DroidDetails2
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "4146de3cd98689d76350979dfcd21d35c4be6bb8727b689e26706fdbbb091383"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  hero {
          |    __typename
          |    ...DroidDetails1
          |    ...DroidDetails2
          |  }
          |}
          |fragment DroidDetails1 on Droid {
          |  __typename
          |  name
          |}
          |fragment DroidDetails2 on Droid {
          |  __typename
          |  primaryFunction
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "TestQuery"
  }
}
