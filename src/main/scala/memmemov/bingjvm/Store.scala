package memmemov.bingjvm

private[bingjvm] class Store(
  private val blocks: Array[Block]
):

  sealed trait Write
  object Written extends Write
  object NotWritten extends Write

  def write(destination: UByte, content: Address): Write =

    if content.hasLength(blocks.length) then
      content.zipWithIndex.foreach {
        case (contentPart, blockIndex) =>
          blocks(blockIndex).write(destination, contentPart)
      }
      Written
    else
      NotWritten

  def read(origin: UByte): Address =

    val parts = blocks.foldLeft(List.empty[UByte]) {
      case(parts, block) =>
        block.read(origin) :: parts
    }

    new Address(parts.reverse)

