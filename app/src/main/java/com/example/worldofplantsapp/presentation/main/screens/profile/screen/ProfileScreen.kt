package com.example.worldofplantsapp.presentation.main.screens.profile.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp120
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp19
import com.example.mediconsultfinalapp.ui.theme.dp2
import com.example.mediconsultfinalapp.ui.theme.dp20
import com.example.mediconsultfinalapp.ui.theme.dp30
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.dp50
import com.example.mediconsultfinalapp.ui.theme.dp9
import com.example.mediconsultfinalapp.ui.theme.sp16
import com.example.mediconsultfinalapp.ui.theme.sp18
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.utils.theme.BoxGray
import com.example.worldofplantsapp.utils.theme.BoxWhite
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.Reds
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ProfileScreen(
    uiStateFlow: StateFlow<ProfileUiState>,
    onInteraction: (ProfileInteraction) -> Unit,
    themeViewModel: ThemeViewModel,
    onNavigatyMap: (String) -> Unit,
    onNavigatyTelegram: () -> Unit
) {
    when (val uiState = uiStateFlow.collectAsState().value) {
        is ProfileUiState.Error -> Unit
        is ProfileUiState.Loaded -> {
            LoadedProfileScreen(
                uiState = uiState,
                onInteraction = onInteraction,
                themeViewModel = themeViewModel,
                onNavigatyMap = onNavigatyMap,
                onNavigatyTelegram = onNavigatyTelegram
            )
        }

        ProfileUiState.Loading -> Unit
    }
}

@Composable
fun LoadedProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState.Loaded,
    onInteraction: (ProfileInteraction) -> Unit,
    themeViewModel: ThemeViewModel,
    onNavigatyMap: (String) -> Unit,
    onNavigatyTelegram: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    val switchState by remember { themeViewModel.isDarkThemeEnabled }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = modifier.height(dp30))
            ProfileToolBar()
            AsyncImage(
                modifier = modifier
                    .size(dp120)
                    .clip(CircleShape),
                model = uiState.profile.userAvatar.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
            )
            Row(
                modifier = modifier.padding(top = dp9)
            ) {
                Text(
                    text = uiState.profile.userName,
                    fontSize = sp18,
                    fontFamily = FontFamily.Serif,
                )
                Text(
                    text = uiState.profile.lastname,
                    fontSize = sp18,
                    fontFamily = FontFamily.Serif,
                    modifier = modifier.padding(start = dp5),
                )
            }
            Spacer(modifier = modifier.height(dp20))
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(dp50)
                    .padding(horizontal = dp50)
                    .clip(RoundedCornerShape(dp19))
                    .background(if (switchState) BoxGray else BoxWhite),
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = dp10),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Тема",
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = modifier
                            .fillMaxWidth(0.8f)
                            .padding(start = dp5)
                    )
                    PlantTheme(
                        themeViewModel = themeViewModel
                    )
                }
            }
            val map = "Ближайший магазин растений в Ош"
            Spacer(modifier = modifier.height(dp15))
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(dp50)
                    .padding(horizontal = dp50)
                    .clip(RoundedCornerShape(dp19))
                    .background(if (switchState) BoxGray else BoxWhite)
                    .clickable { onNavigatyMap(map) },
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = dp10),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Ближайшие магазины",
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = modifier
                            .fillMaxWidth(0.8f)
                            .padding(start = dp5)
                    )
                    Icon(
                        modifier = modifier.padding(start = dp10),
                        painter = painterResource(id = R.drawable.location_icon),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = modifier.height(dp15))
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(dp50)
                    .padding(horizontal = dp50)
                    .clip(RoundedCornerShape(dp19))
                    .background(if (switchState) BoxGray else BoxWhite)
                    .clickable { onNavigatyTelegram() },
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = dp10),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Связаться с нами",
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = modifier
                            .fillMaxWidth(0.8f)
                            .padding(start = dp5)
                    )
                    Image(
                        modifier = modifier.padding(start = dp10),
                        painter = painterResource(id = R.drawable.telegram_icon_plant),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = modifier.height(dp15))
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(dp50)
                    .padding(horizontal = dp50)
                    .clip(RoundedCornerShape(dp19))
                    .background(if (switchState) BoxGray else BoxWhite)
                    .clickable { showDialog = true },
            ) {
                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(start = dp15),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Выйти с аккаунта",
                        color = Reds,
                        fontSize = sp16,
                    )
                }
            }
        }
    }
    if (showDialog) {
        LogautShowDialog(
            onDismiss = {
                showDialog = false
            },
            onConfirm = {
                onInteraction(ProfileInteraction.OnLogoutFromAccount)
                showDialog = false
            }, themeViewModel = themeViewModel
        )
    }
}


@Composable
fun LogautShowDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    themeViewModel: ThemeViewModel,
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(dp30),
            modifier = Modifier.fillMaxWidth(),
            color = if (switchState) BoxGray else BoxWhite
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Вы действительно хотите \n Выйти с аккаунта",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    OutlinedButton(
                        onClick = onDismiss, border = BorderStroke(dp2, Green)
                    ) {
                        Text(
                            "Отмена", color = if (switchState) Color.White else Color.Black
                        )
                    }
                    OutlinedButton(
                        onClick = onConfirm, border = BorderStroke(dp2, Green)
                    ) {
                        Text("Выйти", color = if (switchState) Color.White else Color.Black)
                    }
                }
            }
        }
    }
}