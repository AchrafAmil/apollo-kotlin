// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.arguments_simple

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.arguments_simple.adapter.TestQuery_ResponseAdapter
import com.example.arguments_simple.fragment.HeroDetails
import com.example.arguments_simple.type.Episode
import kotlin.Any
import kotlin.Boolean
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
  val episode: Input<Episode> = Input.absent(),
  val includeName: Boolean,
  val friendsCount: Int,
  val listOfListOfStringArgs: List<List<String?>>
) : Query<TestQuery.Data> {
  @Transient
  private val variables: Operation.Variables = object : Operation.Variables() {
    override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
      if (this@TestQuery.episode.defined) {
        this["episode"] = this@TestQuery.episode.value
      }
      this["IncludeName"] = this@TestQuery.includeName
      this["friendsCount"] = this@TestQuery.friendsCount
      this["listOfListOfStringArgs"] = this@TestQuery.listOfListOfStringArgs
    }

    override fun marshaller(): InputFieldMarshaller {
      return InputFieldMarshaller.invoke { writer ->
        if (this@TestQuery.episode.defined) {
          writer.writeString("episode", this@TestQuery.episode.value?.rawValue)
        }
        writer.writeBoolean("IncludeName", this@TestQuery.includeName)
        writer.writeInt("friendsCount", this@TestQuery.friendsCount)
        writer.writeList("listOfListOfStringArgs") { listItemWriter ->
          this@TestQuery.listOfListOfStringArgs.forEach { value ->
            listItemWriter.writeList { listItemWriter ->
              value.forEach { value ->
                listItemWriter.writeString(value)
              }
            }
          }
        }
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
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val hero: Hero?,
    val heroWithReview: HeroWithReview?
  ) : Operation.Data {
    /**
     * A character from the Star Wars universe
     */
    interface Hero {
      val __typename: String

      /**
       * The name of the character
       */
      val name: String?

      interface Character : Hero, HeroDetails {
        override val __typename: String

        /**
         * The name of the character
         */
        override val name: String?

        /**
         * The friends of the character exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection

        /**
         * A connection object for a character's friends
         */
        interface FriendsConnection : HeroDetails.FriendsConnection {
          /**
           * The total number of friends
           */
          override val totalCount: Int?

          /**
           * The edges for each of the character's friends.
           */
          override val edges: List<Edge?>?

          /**
           * An edge object for a character's friends
           */
          interface Edge : HeroDetails.FriendsConnection.Edge {
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?

            /**
             * A character from the Star Wars universe
             */
            interface Node : HeroDetails.FriendsConnection.Edge.Node {
              /**
               * The name of the character
               */
              override val name: String?
            }
          }
        }
      }

      data class CharacterHero(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String?,
        /**
         * The friends of the character exposed as a connection with edges
         */
        override val friendsConnection: FriendsConnection
      ) : Hero, Character, HeroDetails {
        /**
         * A connection object for a character's friends
         */
        data class FriendsConnection(
          /**
           * The total number of friends
           */
          override val totalCount: Int?,
          /**
           * The edges for each of the character's friends.
           */
          override val edges: List<Edge?>?
        ) : Character.FriendsConnection, HeroDetails.FriendsConnection {
          /**
           * An edge object for a character's friends
           */
          data class Edge(
            /**
             * The character represented by this friendship edge
             */
            override val node: Node?
          ) : Character.FriendsConnection.Edge, HeroDetails.FriendsConnection.Edge {
            /**
             * A character from the Star Wars universe
             */
            data class Node(
              /**
               * The name of the character
               */
              override val name: String?
            ) : Character.FriendsConnection.Edge.Node, HeroDetails.FriendsConnection.Edge.Node
          }
        }
      }

      data class OtherHero(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String?
      ) : Hero

      companion object {
        fun Hero.asCharacter(): Character? = this as? Character

        fun Hero.heroDetails(): HeroDetails? = this as? HeroDetails
      }
    }

    /**
     * A humanoid creature from the Star Wars universe
     */
    data class HeroWithReview(
      /**
       * What this human calls themselves
       */
      val name: String
    )
  }

  companion object {
    const val OPERATION_ID: String =
        "b4e56c478d8dd79496d9d5e474e09c5f71a0deda5180d8e28be697c37605ce9d"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery(${'$'}episode: Episode, ${'$'}IncludeName: Boolean!, ${'$'}friendsCount: Int!, ${'$'}listOfListOfStringArgs: [[String]!]!) {
          |  hero(episode: ${'$'}episode, listOfListOfStringArgs: ${'$'}listOfListOfStringArgs) {
          |    __typename
          |    name @include(if: ${'$'}IncludeName)
          |    ...HeroDetails
          |  }
          |  heroWithReview(episode: ${'$'}episode, review: {
          |    stars: 5
          |    favoriteColor: {
          |      red: 1
          |      blue: 1
          |    }
          |    
          |    listOfStringNonOptional: []
          |  }
          |  ) {
          |    name
          |  }
          |}
          |fragment HeroDetails on Character {
          |  __typename
          |  friendsConnection(first: ${'$'}friendsCount) {
          |    totalCount
          |    edges {
          |      node {
          |        name @include(if: ${'$'}IncludeName)
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "TestQuery"
  }
}
