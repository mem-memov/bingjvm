package net.mem_memov.bingjvm

class StoreSuite extends munit.FunSuite:

  test("Write to a simple store and read from it") {

    val blocks = new Array[Block](1).map(_ => new Block)
    val store = new Store(blocks)

    val block = new Block
    (
      for {
        index <- 0 to 255
        content <- 0 to 255
      } yield (UByte(index), UByte(content))
      ).foreach {
      case (index, content) =>
        val address = new Address(List(content))
        store.write(index, address) match
          case store.Written =>
            val obtained = store.read(index)
            assertEquals(obtained, address)
          case problem =>
            println(problem)
            println(index)
            println(address)
            assert(false)
    }
  }

  test("Write to a double store and read from it") {

    val blocks = new Array[Block](2).map(_ => new Block)
    val store = new Store(blocks)

    val block = new Block
    (
      for {
        index <- 0 to 255
        content <- 0 to 255
      } yield (UByte(index), UByte(content))
      ).foreach {
      case (index, content) =>
        val address = new Address(List(content, content))
        store.write(index, address) match
          case store.Written =>
            val obtained = store.read(index)
            assertEquals(obtained, address)
          case problem =>
            println(problem)
            println(index)
            println(address)
            assert(false)
    }
  }
