# bing
### address database

This library provides an in-memory storage of addresses.
It enables you to send existing addresses to new places in memory.
You can run through all of them if you wish.
No thread safety is at your disposal.
Start with:

```
import net.mem_memov.bingjvm.{Entry, Inventory, Memory, UByte}

val inventory: Inventory[Entry] = new Memory
val firstEntry: Entry = memory.start

inventory.append(firstEntry) match
  case inventory.Appended(secondEntry) =>
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

```bash
sbt test
sbt publishLocal
sbt publishSigned
```