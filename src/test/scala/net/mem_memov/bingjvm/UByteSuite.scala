package net.mem_memov.bingjvm

class UByteSuite extends munit.FunSuite:

  test("Convert unsigned byte to signed integer") {

    (0 to 255).foreach { int =>
      val byte = UByte(int)
      assert(byte.toInt == int)
    }
  }

  test("Fail converting a bigger positive integer") {

    intercept[java.lang.IllegalArgumentException] {
      UByte(256)
    }
  }

  test("Fail converting a negative integer") {

    intercept[java.lang.IllegalArgumentException] {
      UByte(-1)
    }
  }
