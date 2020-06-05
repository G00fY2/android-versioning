package eu.nanogiants.gradle.ext

import java.io.BufferedReader
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

internal fun String.getOutput() = this.runCommand()?.use { return it.readText().trim() } ?: ""

internal fun String.runCommand(): BufferedReader? = try {
  ProcessBuilder(this.split("\\s".toRegex())).start().run {
    waitFor(10, TimeUnit.SECONDS)
    inputStream.bufferedReader()
  }
} catch (e: IOException) {
  e.printStackTrace()
  null
}

internal fun String?.listContains(element: String) =
  this != null && this.split(",").map { it.toLowerCase(Locale.ROOT).trim() }.contains(element.toLowerCase(Locale.ROOT))