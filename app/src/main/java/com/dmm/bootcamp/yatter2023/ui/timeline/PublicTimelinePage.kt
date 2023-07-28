package com.dmm.bootcamp.yatter2023.ui.timeline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PublicTimelinePage(viewModel: PublicTimelineViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PublicTimelineTemplate(
        statusBindingModel =   StatusBindingModel(
            id = "id",
            displayName = "display name",
            username = "username",
            avatar = null,
            content = "preview content",
            attachmentMediaList = listOf()
        ),
        statusList = uiState.statusList,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefreshing,
        onRefresh = viewModel::onRefresh,
        onClickPost = viewModel::onClickPost, // 追加分
        onLogout = viewModel::onLogout  // onLogoutを追加

    ) }