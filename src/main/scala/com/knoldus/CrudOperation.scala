package com.knoldus

import java.io.{File, PrintWriter}
import java.net.InetAddress
import org.elasticsearch.action.bulk.BulkResponse
import org.elasticsearch.action.deletebyquery.{DeleteByQueryResponse, DeleteByQueryAction}
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.action.update.UpdateResponse
import org.elasticsearch.client.Client
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.plugin.deletebyquery.DeleteByQueryPlugin
import scala.io.Source

/**
  * Created by akash on 3/4/16.
  */
class CrudOperation {

  val client: Client = TransportClient.builder()
    .addPlugin(classOf[DeleteByQueryPlugin]).build()
    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))

  def addJson(stu_id: String, stu_name: String, stu_add: String, stu_no: String): IndexResponse = {

    val stu_JSON = s"""{ "id":"$stu_id","name":"$stu_name","address":"$stu_add","number":"$stu_no" }"""
    client.prepareIndex("college", "student", stu_id).setSource(stu_JSON).get()

  }
def totalCount(): Long = {

    client.prepareSearch("college").execute().actionGet().getHits.totalHits()

  }

  def updateJson(stu_id: String, key: String, value: Any): UpdateResponse = {

    client.prepareUpdate("college", "student", stu_id).setDoc(key, value).get()

  }

  def deleteJson(stu_id: String) : DeleteByQueryResponse = {

    DeleteByQueryAction.INSTANCE.newRequestBuilder(client).setIndices("college").setQuery(QueryBuilders.termsQuery("_id", stu_id)).get()

  }

  def readFromFile(file:String) : BulkResponse = {

    val data = Source.fromFile(file).getLines().toList
    val bulk = client.prepareBulk()
    data.map{
      json => bulk.add(client.prepareIndex("socialmedia","twitter").setSource(json))
    }
    bulk.execute().actionGet()

  }

  def writeToFile() : Boolean = {

    val data=client.prepareSearch("socialmedia").setTypes("twitter").execute().get()
    val pw=new PrintWriter(new File("/home/akash/Assignment/Elastic_Search/src/test/resources/OutputJson.json"))
    pw.write(data.toString)
    true
  }
  
}

