package memmemov.bingjvm

class UByte(private val byte: Byte) extends AnyVal:

  def +(that: UByte): UByte =

    UByte(this.toInt + that.toInt)

  def >(that: UByte): Boolean =

    this.toInt > that.toInt

  def toInt: Int =

    val int = byte.toInt
    if int >= 0 && int <= 127 then
      int
    else
      int + 256

object UByte:

  val MinValue = new UByte(0.toByte)

  val MaxValue = new UByte((-1).toByte)

  def apply(int: Int): UByte =

    require(int >= 0 && int <= 255)

    if int >= 0 && int <= 127 then
      new UByte(int.toByte)
    else
      new UByte((int - 256).toByte)