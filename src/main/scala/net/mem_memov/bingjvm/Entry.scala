package net.mem_memov.bingjvm

trait Entry:

  def foreach(f: UByte => Unit): Unit