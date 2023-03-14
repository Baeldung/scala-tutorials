import ammonite.ops._

@main
def fileOps() = {
  val workingDir: os.Path = pwd / "base"

  // cleaning up the working directory to avoid any issues with next run
  rm ! workingDir

  mkdir ! pwd / "base"
  mkdir ! workingDir / "sub1" / "sub2" / "sub3"

  val simpleTextFile = workingDir / "text" / "simple.txt"

  rm ! simpleTextFile

  // Write text file
  write(
    simpleTextFile,
    "This is a simple text file written using ammonite ops.",
    createFolders = true
  )

  // read from the text file
  val content = read(simpleTextFile)
  assert(content == "This is a simple text file written using ammonite ops.")

  // append new text to the file
  write.append(simpleTextFile, "Append new contents", createFolders = true)
  val appendContent = read(simpleTextFile)
  assert(
    appendContent == "This is a simple text file written using ammonite ops.Append new contents"
  )

  // overwrite entire file contents
  write.over(simpleTextFile, "Overwrite contents", createFolders = true)
  val contentOverwrite = read(simpleTextFile)
  assert(contentOverwrite == "Overwrite contents")

  write(workingDir / "dummy.txt", "Line 1")

  // list directory contents
  val items: LsSeq = ls ! workingDir
  assert(items.size == 3)
  val onlyFiles = items.filter(_.isFile)
  assert(onlyFiles.size == 1)

  write.append(workingDir / "dummy.txt", "\nLine 2")
  write.append(workingDir / "dummy.txt", "\nLine 3")

  // Read the file contents as lines
  val contentAsLines = read.lines(workingDir / "dummy.txt")
  assert(contentAsLines.size == 3)

  // copy and move operations
  cp.into(workingDir / "dummy.txt", workingDir / "text")
  mv(workingDir / "text" / "dummy.txt", workingDir / "text" / "renamed.txt")

  val isRenamed = exists ! workingDir / "text" / "renamed.txt"
  assert(isRenamed)

  import ammonite.ops.ImplicitWd._
  // ls and writes the response to output stream
  %("ls")

  // ls and set the response to the variable lsResults
  val lsResults = %%("ls")
  assert(lsResults.exitCode == 0)
  assert(lsResults.out.lines.size == 2)

}
