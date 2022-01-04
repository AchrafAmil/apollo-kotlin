package testnetworktransport

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Error
import com.apollographql.apollo3.testing.QueueTestNetworkTransportHandler
import com.apollographql.apollo3.testing.TestNetworkTransport
import com.apollographql.apollo3.testing.runTest
import com.benasher44.uuid.uuid4
import testnetworktransport.test.GetHeroQuery_TestBuilder.Data
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ApolloExperimental::class)
class QueueTestNetworkTransportHandlerTest {
  private lateinit var queue: QueueTestNetworkTransportHandler
  private lateinit var apolloClient: ApolloClient

  private fun setUp() {
    queue = QueueTestNetworkTransportHandler()
    apolloClient = ApolloClient.Builder()
        .networkTransport(TestNetworkTransport(queue))
        .build()
  }

  private fun tearDown() {
    apolloClient.dispose()
  }

  @Test
  fun enqueueResponses() = runTest(before = { setUp() }, after = { tearDown() }) {
    val query1 = GetHeroQuery("001")
    val testResponse1 = ApolloResponse.Builder(query1, uuid4(), null)
        .errors(listOf(Error(
            message = "There was an error",
            locations = listOf(Error.Location(line = 1, column = 2)),
            path = listOf("hero", "name"),
            extensions = null,
            nonStandardFields = null))
        ).build()

    val query2 = GetHeroQuery("002")
    val query2TestData = GetHeroQuery.Data(
        GetHeroQuery.Hero(
            __typename = "Droid",
            name = "R2D2",
            id = "r2d2",
            onDroid = GetHeroQuery.OnDroid("mechadroid"),
            onHuman = null
        )
    )
    val testResponse2 = ApolloResponse.Builder(query2, uuid4(), query2TestData).build()

    val query3 = GetHeroNameOnlyQuery()
    val testResponse3 = ApolloResponse.Builder(query3, uuid4(), GetHeroNameOnlyQuery.Data(GetHeroNameOnlyQuery.Hero(name = "Darth Vader")))
        .build()

    queue.enqueue(testResponse1)
    queue.enqueue(testResponse2)
    queue.enqueue(testResponse3)

    val actual1: ApolloResponse<GetHeroQuery.Data> = apolloClient.query(query1).execute()
    assertTrue(actual1.hasErrors())
    assertEquals(testResponse1.errors!!.first().message, actual1.errors!!.first().message)

    val actual2: ApolloResponse<GetHeroQuery.Data> = apolloClient.query(query2).execute()
    assertFalse(actual2.hasErrors())
    assertEquals(query2TestData, actual2.data)

    val actual3: ApolloResponse<GetHeroNameOnlyQuery.Data> = apolloClient.query(query3).execute()
    assertFalse(actual3.hasErrors())
    assertEquals("Darth Vader", actual3.data!!.hero.name)
  }

  @Test
  fun enqueueError() = runTest(before = { setUp() }, after = { tearDown() }) {
    val query = GetHeroQuery("001")
    queue.enqueue(query, errors = listOf(Error(
        message = "There was an error",
        locations = listOf(Error.Location(line = 1, column = 2)),
        path = listOf("hero", "name"),
        extensions = mapOf("myExtension" to true),
        nonStandardFields = null))
    )

    val actual: ApolloResponse<GetHeroQuery.Data> = apolloClient.query(query).execute()
    assertTrue(actual.hasErrors())
    assertEquals(actual.errors!!.first().message, "There was an error")
    assertEquals(actual.errors!!.first().extensions!!["myExtension"], true)
  }

  @Test
  fun enqueueDataTestBuilder() = runTest(before = { setUp() }, after = { tearDown() }) {
    val query = GetHeroQuery("001")
    val testData = GetHeroQuery.Data {
      hero = droidHero {
        name = "R2D2"
      }
    }
    queue.enqueue(query, testData)

    val actual = apolloClient.query(query).execute().data!!
    assertEquals(testData.hero.name, actual.hero.name)
  }
}
