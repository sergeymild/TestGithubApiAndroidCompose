package com.my.github.helpers

fun generateDownloadLink(fullName: String, defaultBranch: String): String {
    return "/repos/$fullName/zipball/$defaultBranch"
}

fun generateDownloadFileName(fullName: String, defaultBranch: String): String {
    return "${fullName.replace("/", "_")}_${defaultBranch}.zip"
}