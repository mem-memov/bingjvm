package memmemov.bingjvm

class AddressSuite extends munit.FunSuite:

  test("Address gets incremented") {
    List(
      (
        new Address(List(UByte.MinValue)),
        new Address(List(UByte(1)))
      ),
      (
        new Address(List(UByte(254))),
        new Address(List(UByte(255)))
      ),
      (
        new Address(List(UByte(255))),
        new Address(List(UByte(1), UByte.MinValue))
      ),
      (
        new Address(List(UByte(255), UByte(255))),
        new Address(List(UByte(1), UByte.MinValue, UByte.MinValue))
      ),
    ).foreach{ case(original, expected) =>
      assert(
        original.increment == expected
      )
    }
  }

  test("Address gets incremented to a big value") {

    val incrementedAddress = (1 to 65536).foldLeft(new Address(List(UByte(0)))) { (address, _) =>
      address.increment
    }

    assert(incrementedAddress == new Address(List(UByte(1), UByte(0), UByte(0))))

  }

  test("Address provides its length") {
    (0 to 255).map(n => List.empty[UByte].padTo(n * 100, UByte(n))).foreach { indices =>
      assert(
        new Address(indices).hasLength(indices.length)
      )
    }
  }

  test("Create another address with some zero bytes at the head") {

    val low = UByte.MinValue
    val high = UByte.MaxValue

    List(
      (
        new Address(List.empty),
        2,
        new Address(List(low, low)),
        Option.empty[String]
      ),
      (
        new Address(List(low)),
        3,
        new Address(List(low, low ,low)),
        Option.empty[String]
      ),
      (
        new Address(List(high)),
        1,
        new Address(List(high)),
        Option.empty[String]
      ),
      (
        new Address(List(high)),
        3,
        new Address(List(low, low, high)),
        Option.empty[String]
      ),
      (
        new Address(List(high, low)),
        3,
        new Address(List(low, low, high, low)),
        Option.empty[String]
      ),
      (
        new Address(List(high, high, high)),
        2,
        new Address(List.empty),
        Some("NotPaddedBigAlreadyGreater")
      )
    ).foreach { case (original, target, expected, failure) =>

      original.padBig(target) match

        case original.PaddedBig(padded) =>
          assert(padded == expected)
          assert(failure.isEmpty)

        case original.NotPaddedBigAlreadyGreater =>
          assert(failure.contains("NotPaddedBigAlreadyGreater"))
    }
  }

  test("Create another address without zero bytes at the head") {

    val low = UByte.MinValue
    val high = UByte.MaxValue

    List(
      (
        new Address(List()),
        new Address(List())
      ),
      (
        new Address(List(low, low, low, low)),
        new Address(List(low))
      ),
      (
        new Address(List(high)),
        new Address(List(high))
      ),
      (
        new Address(List(low, high)),
        new Address(List(high))
      ),
      (
        new Address(List(low, low, low, high)),
        new Address(List(high))
      ),
      (
        new Address(List(low, high, low)),
        new Address(List(high, low))
      ),
      (
        new Address(List(high, low, low)),
        new Address(List(high, low, low))
      ),
      (
        new Address(List()),
        new Address(List())
      ),
      (
        new Address(List(low, low, low)),
        new Address(List())
      ),
    ).foreach { case (original, expected) =>
      assert(
        original.trimBig == expected
      )
    }
  }
