import com.knoldus.CrudOperation
import org.scalatest.FunSuite

/**
  * Created by akash on 3/4/16.
  */
class CrudOperationSpec extends FunSuite {

  val obj = new CrudOperation()

  test("Add a Student") {

    val add = obj.addJson("1", "Akash", "Noida", "9808980898")
    assert(add.getId == "1")
  }

  test("Count Total Students") {

    val res = obj.totalCount()
    assert(res==3)
  }

  test("Update a Student") {

    val res = obj.updateJson("1","address","noida")
    assert(res.getVersion==9)
  }

  test("Delete Student") {

    val res = obj.deleteJson("2")
    assert(res.getTotalDeleted==1)
  }

  test("Read from a Json File"){

    val res = obj.readFromFile("inputJson.json")
    assert(res!=null)
  }

  test("Write  to a File"){

    val res = obj.writeToFile()
    assert(res)
  }
}
