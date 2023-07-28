package com.dmm.bootcamp.yatter2023.ui.timeline

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmm.bootcamp.yatter2023.R
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme

import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PublicTimelineTemplate(
    statusBindingModel: StatusBindingModel, // 新しい引数を追加
    statusList: List<StatusBindingModel>,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onClickPost: () -> Unit,
    onLogout: () -> Unit  // 追加

) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, onRefresh)

    val scaffoldState = rememberScaffoldState(
    )
    val coroutineScope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open() // Open drawer
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Drawer Icon",
                        tint = Color.White
                    )
                }
            },
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.width(48.dp))

                    Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Image(
                            painter = painterResource(id = R.drawable.x_logo_2023),
                            contentDescription = "App Logo",
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(100.dp))
                }
            },
        )
    }, drawerContent = {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = MaterialTheme.colors.primary), // 背景色を指定
                contentAlignment = Alignment.CenterStart // ボックス内のコンテンツを左寄せにする
            ) {
                Column(
                    Modifier.padding(start = 8.dp), // ここで左側の余白を調整
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.Start), // 画像を左寄せにする

                    ) {
                        AsyncImage(
                            model = statusBindingModel.avatar,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        )
                    }
                    // 画像の下にテキストを追加
                    Text(
                        text = statusBindingModel.displayName,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .align(Alignment.Start) // テキストを左寄せにする
                            .padding(top = 8.dp)
                    )

                    Text(
                        text = statusBindingModel.username,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .align(Alignment.Start) // テキストを左寄せにする
                            .padding(top = 8.dp)
                    )
                }
            }
            Divider()
            DrawerMenuItem(
                text = "設定",
                onClick = {
                    // 「設定」項目がクリックされたときの処理を実装します。
                }

            )
            Divider()
            val showDialog = remember { mutableStateOf(false) }

            DrawerMenuItem(
                text = "ログアウト",
                onClick = {
                    showDialog.value = true
                }
            )

            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog.value = false
                    },
                    title = { Text(text = "ログアウト") },
                    text = { Text(text = "ログアウトしますか？") },
                    confirmButton = {
                        Button(onClick = {
                            // 「確認」ボタンがクリックされたときの処理を実装します。
                            // ここではダイアログを閉じるだけの処理をします。
                            onLogout()  // 追加

                        }) {
                            Text("確認")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            // 「キャンセル」ボタンがクリックされたときの処理を実装します。
                            // ここではダイアログを閉じるだけの処理をします。
                            showDialog.value = false
                        }) {
                            Text("キャンセル")
                        }
                    }
                )
            }

        }
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickPost, backgroundColor = Color(0xFF03A9F4)
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "post", tint = Color.White
                )
            }
        }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState),
            contentAlignment = Alignment.Center,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(statusList) { item ->
                    StatusRow(statusBindingModel = item)
                    Divider()

                }
            }
            PullRefreshIndicator(
                isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter)
            )
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun PublicTimelineTemplatePreview() {
    Yatter2023Theme {
        Surface {
            PublicTimelineTemplate(
                statusBindingModel =   StatusBindingModel(
                    id = "id",
                    displayName = "display name",
                    username = "username",
                    avatar = null,
                    content = "preview content",
                    attachmentMediaList = listOf()
                ),
                statusList = listOf(
                    StatusBindingModel(
                        id = "id",
                        displayName = "display name",
                        username = "username",
                        avatar = null,
                        content = "preview content",
                        attachmentMediaList = listOf()
                    )
                ),
                isLoading = false,
                isRefreshing = false,
                onRefresh = {},
                onClickPost = {},  // ダミーのonClickPost関数を追加
                onLogout = {},  // ダミーのonLogout関数も追加
                )  // 追加分


        }

    }
}




@Composable
fun DrawerMenuItem(text: String, onClick: (String) -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(text) }
            .padding(16.dp),
        style = MaterialTheme.typography.h6
    )
}