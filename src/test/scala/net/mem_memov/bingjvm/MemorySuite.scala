package net.mem_memov.bingjvm

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

  test("Append and read addresses") {

    val memory = new Memory

    val start = memory.start

    val addresses: List[Address] = (0 to 1000).foldLeft((start, List(start))) {
      case ((previous, addresses), _) =>
        val next = previous.increment
        (next, next :: addresses)
    }._2.reverse

    val destinations: List[Address] = addresses.map { content =>
      memory.append(content) match
        case memory.Appended(destination) =>
          destination
        case problem =>
          println(content)
          println(problem)
          assert(false)
          new Address(List.empty)
    }

    destinations.zip(addresses).foreach {
      case (origin, expected) =>
        memory.read(origin) match
          case memory.ReadResult(obtained) =>
            assertEquals(obtained, expected)
          case _ =>
            assert(false)
    }

  }
