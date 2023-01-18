// This can be placed either in project directory of the repo or can be kept at global location($HOME/.sbt/1.0)
shellPrompt := { state =>
  "[sbt (%s)====>] ".format(Project.extract(state).currentProject.id)
}

// Copy the below contents to the build.sbt after installing the sbt-prompt plugin (https://github.com/agemooij/sbt-prompt)
// For installation of the blugin plugin add this to plugins.sbt
//addSbtPlugin("com.scalapenos" % "sbt-prompt" % "1.0.2")

//Setting the configuration
/**
import com.scalapenos.sbt.prompt.SbtPrompt.autoImport._ 
promptTheme := com.scalapenos.sbt.prompt.PromptThemes.ScalapenosTheme  
*/ 
