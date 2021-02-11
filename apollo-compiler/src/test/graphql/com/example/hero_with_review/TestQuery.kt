// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_with_review

import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.hero_with_review.adapter.TestQuery_ResponseAdapter
import com.example.hero_with_review.type.Episode
import kotlin.Any
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.jvm.Transient

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
data class TestQuery(
  val ep: Episode
) : Mutation<TestQuery.Data> {
  @Transient
  private val variables: Operation.Variables = object : Operation.Variables() {
    override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
      this["ep"] = this@TestQuery.ep
    }

    override fun marshaller(): InputFieldMarshaller {
      return InputFieldMarshaller.invoke { writer ->
        writer.writeString("ep", this@TestQuery.ep.rawValue)
      }
    }
  }

  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = variables

  override fun name(): String = OPERATION_NAME

  override fun adapter(): ResponseAdapter<Data> = TestQuery_ResponseAdapter
  override fun responseFields(): List<ResponseField.FieldSet> = listOf(
    ResponseField.FieldSet(null, TestQuery_ResponseAdapter.RESPONSE_FIELDS)
  )
  /**
   * The mutation type, represents all updates we can make to our data
   */
  data class Data(
    val createReview: CreateReview?
  ) : Operation.Data {
    /**
     * Represents a review for a movie
     */
    data class CreateReview(
      /**
       * The number of stars this review gave, 1-5
       */
      val stars: Int,
      /**
       * Comment about the movie
       */
      val commentary: String?
    )
  }

  companion object {
    const val OPERATION_ID: String =
        "1faa220c7551ff1343a9bce722af8c12b87c70cc579f743aff1374ae1066f163"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |mutation TestQuery(${'$'}ep: Episode!) {
          |  createReview(episode: ${'$'}ep, review: {
          |    stars: 5
          |    listOfEnums: [JEDI,EMPIRE,NEWHOPE]
          |    listOfStringNonOptional: ["1","2","3"]
          |    favoriteColor: {
          |      red: 1
          |      blue: 1
          |    }
          |    
          |  }
          |  ) {
          |    stars
          |    commentary
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "TestQuery"
  }
}
