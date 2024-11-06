package com.my.github.presentation.Search.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my.github.domain.models.GithubRepository

@Composable
fun SearchRepositoriesListItemView(
  item: GithubRepository,
  openUri: (String) -> Unit,
  onDownload: () -> Unit
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
        item.owner.login,
        fontSize = 16.sp,
        fontWeight = FontWeight.W600,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
      Text(item.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
    if (item.isDownloading) {
      Box(
        modifier = Modifier
          .height(32.dp)
          .width(80.dp), contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator(
          strokeWidth = 2.dp,
          color = MaterialTheme.colorScheme.secondary,
          trackColor = MaterialTheme.colorScheme.surfaceVariant,
          modifier = Modifier
            .fillMaxHeight()
            .size(0.dp)
            .aspectRatio(1f, true)
        )
      }
    } else {
      IconButton(
        onClick = onDownload) {
        val icon = if (item.isError) Default.Refresh else Default.KeyboardArrowDown
        Icon(imageVector = icon, contentDescription = "Download")
      }
    }
    Spacer(modifier = Modifier.width(8.dp))
    IconButton(
      onClick = { openUri(item.htmlUrl) }) {
      Icon(imageVector = Default.Share, contentDescription = "Open in external browser")
    }
  }

}