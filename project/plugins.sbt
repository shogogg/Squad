resolvers ++= Seq(
  "jgit-repo" at "http://download.eclipse.org/jgit/maven",
  "hexx-releases" at "http://hexx.github.com/maven/releases"
)

// sbt-idea
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.1.0")

// sbt-github-repo
addSbtPlugin("com.github.hexx" % "sbt-github-repo" % "0.0.1")
