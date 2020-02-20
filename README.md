# Java Standard Library 2 GWT
---
### What it is
This is a GWT port of the Java Standard Libraries that are missing from GWT.
  - AWT
  - Swing
  - JavaFX
  - And anything else not on the included on [this GWT default list](http://www.gwtproject.org/doc/latest/RefJreEmulation.html)

### What it is not
This is not, atleast in the current state, a drop in replacement for these missing classes. Do not expect to drop a .JAR file into this in expect it to work. Classes may be missing functionality, things might work differently, and packages aren't the same.

### How to use
Right now it is not recommended to use this for non-testing purposes. This project is in very alpha stages. But if you do want to use this, it is a normal GWT module. All ported classes packages will start with `stdlibport.client`
Ex: `java.awt.Color` would be `stdlibport.client.java.awt.Color`

There is a demo located in [the index.html file](index.html)