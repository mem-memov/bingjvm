package memmemov.bingjvm

class Address(
  private[Address] val indices: List[UByte]
) extends Entry with Ordered[Address]:

  private[Address] lazy val length: Int = indices.length

  private[bingjvm] def trimBig: Address =

    val trimmedIndices = indices.dropWhile(_ == UByte.MinValue)
    val nonEmptyIndices = if trimmedIndices.isEmpty then List(UByte.MinValue) else trimmedIndices
    new Address(nonEmptyIndices)

  private[bingjvm] sealed trait PadBig
  private[bingjvm] case class PaddedBig(padded: Address) extends PadBig
  private[bingjvm] object NotPaddedBigAlreadyGreater extends PadBig

  private[bingjvm] def padBig(target: Int): PadBig =

    if length == target then
      PaddedBig(this)
    else
      val trimmed = this.trimBig
      if trimmed.length > target then
        NotPaddedBigAlreadyGreater
      else
        val padding = (0 to length - target).map(_ => UByte.MinValue).toList
        PaddedBig(
          padded = new Address(
            padding ++ indices
          )
        )

  def hasLength(length: Int): Boolean =
    this.length == length

  def increment: Address =

    def plusOne(x: UByte): (UByte, Boolean) =

      if x == UByte.MaxValue then
        (UByte.MinValue, true)
      else
        (x + UByte(1), false)

    val (accumulator, _, hasOverflow) = indices.reverse.foldLeft((List.empty[UByte], true, false)) {
      case ((accumulator, isStart, hasOverflow), index) =>
        if isStart then
          val (incrementedIndex, overflow) = plusOne(index)
          (incrementedIndex :: accumulator, false, overflow)
        else
          if hasOverflow then
            val (incrementedIndex, overflow) = plusOne(index)
            (incrementedIndex :: accumulator, false, overflow)
          else
            (index :: accumulator, false, false)
    }

    val resultIndices = if hasOverflow then UByte(1) :: accumulator else accumulator

    new Address(resultIndices)

  override def foreach(f: UByte => Unit): Unit = indices.foreach(f)

  private[bingjvm] def zipWithIndex: List[(UByte, Int)] = indices.zipWithIndex

  private[bingjvm] sealed trait Shorten
  private[bingjvm] object NotShortened extends Shorten
  private[bingjvm] case class Shortened(addressPart: UByte, shorterAddress: Address) extends Shorten

  private[bingjvm] def shorten: Shorten =
    if length == 0 then
      NotShortened
    else
      Shortened(
        addressPart = indices.head,
        shorterAddress = new Address(indices.tail)
      )

  def isEmpty: Boolean =
    length == 0

  override def compare(that: Address): Int =
    val trimmedThis = this.trimBig
    val trimmedThat = that.trimBig
    if trimmedThis.length != trimmedThat.length then
      trimmedThis.length - trimmedThat.length
    else
      trimmedThis.indices.zipAll(trimmedThat.indices, UByte.MinValue, UByte.MinValue)
        .dropWhile { case (thisIndex, thatIndex) =>
          thisIndex == thatIndex
        } match
          case Nil => 0
          case (thisIndex, thatIndex) :: _ => if thisIndex > thatIndex then 1 else -1

  override def equals(that: Any): Boolean =
    that match
      case that: Address => compare(that) == 0
      case _ => false

  override def toString: String =

    indices.map(_.toInt.toString()).mkString("Address(", ",", ")")
