package memmemov.bingjvm

trait Entry:

  def foreach(f: UByte => Unit): Unit