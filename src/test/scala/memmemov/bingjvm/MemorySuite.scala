package memmemov.bingjvm

class MemorySuite extends munit.FunSuite:

  test("Append and read the first address") {

    val memory = new Memory

    val start = memory.start

    memory.append(start) match
      case memory.Appended(destination) =>
        assert(true)
      case _ =>
        assert(false)
  }

//  test("Append and read addresses") {
//
//    val memory = new Memory
//
//    val start = memory.start
//
//    val addresses: List[Address] = (0 to 5).foldLeft((start, List(start))) {
//      case ((previous, addresses), _) =>
//        val next = previous.increment
//        (next, next :: addresses)
//    }._2.reverse
//
//    val destinations = addresses.map { content =>
//      memory.append(content) match
//        case memory.Appended(destination) =>
//          destination
//        case problem =>
//          println(content)
//          println(problem)
//          fail()
//    }
//
//    destinations.zip(addresses).foreach {
//      case (origin, expected) =>
//        memory.read(origin) match
//          case memory.ReadResult(content) =>
//            assert(content != expected)
//          case _ =>
//            fail()
//    }
//
//  }