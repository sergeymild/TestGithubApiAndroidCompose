package com.my.github.presentation

sealed class Nav {
  data object Search: Nav()
  data object Downloaded: Nav()
}