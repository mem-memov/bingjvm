# bing
### address database

This library provides an in-memory storage of addresses.
It enables you to send existing addresses to new places in memory.
You can run through all of them if you wish.
No thread safety is at your disposal.
Start with:

```scala
import memmemov.bingjvm

val inventory: bingjvm.Inventory[bingjvm.Entry] = new bingjvm.Memory
val firstEntry: bingjvm.Entry = memory.start

inventory.append(firstEntry) match
    case inventory.Appended(secondEntry) =>
      inventory.append(secondEntry)
      ()
    case _ =>
      ()

inventory.foreach { entry: bing.Entry =>
  entry.foreach { b: bingjvm.UByte =>
    // use the byte
    ()
  }
}
```

```bash
sbt test
sbt publishLocal
```