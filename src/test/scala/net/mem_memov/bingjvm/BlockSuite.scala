package net.mem_memov.bingjvm

class BlockSuite extends munit.FunSuite:

  test("Read a byte from a block") {
    val block = new Block
    (
      for {
        index <- 0 to 255
        content <- 0 to 255
      } yield (UByte(index), UByte(content))
    ).foreach {
      case (index, content) =>
        block.write(index, content)
        val result = block.read(index)
        assert(result == content)
    }
  }
