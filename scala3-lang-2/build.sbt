val scala3Version = "3.2.2"

scalaVersion := scala3Version

/* extra runtime checks to find ill-formed trees or types as soon as they are created
*  and check compiler invariants for tree well-formedness
*/
//scalacOptions += "-Xcheck-macros"
//scalacOptions += "-Ycheck:all"
