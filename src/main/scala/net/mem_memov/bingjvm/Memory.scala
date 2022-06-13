package net.mem_memov.bingjvm

import scala.annotation.tailrec

class Memory extends Inventory[Address]:

  override val start: Address = new Address(List(UByte.MinValue))

  private var next: Address = start
  private lazy val root: Element = new Element(new Level)

  override def append(content: Address): Append =

    val trimmedContent = content.trimBig

    if trimmedContent > next then
      NotAppendedContentTooBig
    else
      root.write(next, trimmedContent) match
        case root.Written =>
          val destination = next
          next = next.increment
          Appended(destination)
        case root.NotWritten =>
          NotAppended

  override def update(destination: Address, content: Address): Update =

    val trimmedDestination = destination.trimBig
    val trimmedContent = content.trimBig

    if trimmedDestination > next then
      NotUpdatedDestinationTooBig
    else
      if trimmedContent > trimmedDestination then
        NotUpdatedContentTooBig
      else
        root.write(next, content) match
          case root.Written =>
            Updated
          case root.NotWritten =>
            NotUpdated

  override def read(origin: Address): Read =

    root.read(origin) match
      case root.ReadResult(content) =>
        ReadResult(content.trimBig)
      case root.NotRead =>
        NotRead

  override def foreach(f: Address => Unit): Unit =

    @tailrec
    def walk(f: Address => Unit, source: Address): Unit =
      if source == next then
        ()
      else
        read(source) match
          case ReadResult(content) =>
            f(content)
            walk(f, source.increment)
          case NotRead =>
            ()

    walk(f, start)


