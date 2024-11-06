package com.my.github.presentation.Downloaded.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my.github.domain.models.GithubDownloadedRepository

@Composable
fun DownloadedRepositoriesListItemView(
  item: GithubDownloadedRepository,
  openUri: (String) -> Unit,
  onDelete: () -> Unit
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .height(72.dp)
      .padding(horizontal = 16.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1.0f)
    ) {
      Text(
        item.ownerName,
        fontSize = 16.sp,
        fontWeight = FontWeight.W600,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
      Text(item.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
    IconButton(onClick = onDelete) {
      Icon(imageVector = Default.Clear, contentDescription = "Delete")
    }
    Spacer(modifier = Modifier.width(8.dp))
    IconButton(
      onClick = { openUri(item.htmlUrl) }) {
      Icon(imageVector = Default.Share, contentDescription = "Open in external browser")
    }
  }

}