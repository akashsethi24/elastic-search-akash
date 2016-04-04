def demo(str:String):Unit = {

  if (str.contains(',')) {
    if(str.contains('.')){
      if(str.contains('$'))
      println(". , $ h ")
      else
        println(", . h ")
    }
    else{
      if(str.contains('$'))
        println(" , $ h ")
      else
        println(",  h ")
    }
  }
  else if (str.contains('.')) {

    if(str.contains('$'))
      println(" . $ h ")
    else
      println(".  h ")
  }
  else if (str.contains('$')) {
    println("$ h ")
  }
}
var str = ".$,"
val v = demo(str)