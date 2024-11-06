package com.my.github.extensions

import android.os.Environment
import okhttp3.ResponseBody
import java.io.File

fun ResponseBody.saveFileToDownloads(fileName: String): String {
  val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
  val destinationFile = File(path, fileName)
  if (destinationFile.exists()) destinationFile.delete()
  byteStream().use { inputStream->
    destinationFile.outputStream().use { outputStream->
      inputStream.copyTo(outputStream)
    }
  }
  return destinationFile.absolutePath
}