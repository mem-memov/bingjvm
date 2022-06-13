Start with:

```scala

import net.mem_memov.bingjvm.{Entry, Inventory, Memory, UByte}

val inventory: Inventory[Entry] = new Memory
val firstEntry: Entry = memory.start

inventory.append(firstEntry) match
case inventory.Appended(secondEntry)
=>
inventory.append(secondEntry)
()
case _ =>
  ()

inventory.foreach { entry: bing.Entry =>
  entry.foreach { b: UByte =>
    // use the byte
    ()
  }
}
```