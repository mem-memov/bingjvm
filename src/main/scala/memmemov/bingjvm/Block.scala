package memmemov.bingjvm

private[bingjvm] class Block:

  private lazy val bytes: Array[UByte] =

    new Array[UByte](Block.size).map(_ => UByte.MinValue)
  
  def read(origin: UByte): UByte =

    bytes(origin.toInt)
  
  def write(destination: UByte, content: UByte): Unit =

    bytes(destination.toInt) = content

object Block:

  private lazy val size: Int = UByte.MaxValue.toInt + 1
