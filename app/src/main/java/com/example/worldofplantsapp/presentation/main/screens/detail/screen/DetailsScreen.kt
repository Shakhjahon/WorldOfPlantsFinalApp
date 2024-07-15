package com.example.worldofplantsapp.presentation.main.screens.detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.mediconsultfinalapp.ui.theme.dp1
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp12
import com.example.mediconsultfinalapp.ui.theme.dp125
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp170
import com.example.mediconsultfinalapp.ui.theme.dp20
import com.example.mediconsultfinalapp.ui.theme.dp220
import com.example.mediconsultfinalapp.ui.theme.dp25
import com.example.mediconsultfinalapp.ui.theme.dp250
import com.example.mediconsultfinalapp.ui.theme.dp3
import com.example.mediconsultfinalapp.ui.theme.dp30
import com.example.mediconsultfinalapp.ui.theme.dp35
import com.example.mediconsultfinalapp.ui.theme.dp4
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.dp6
import com.example.mediconsultfinalapp.ui.theme.sp15
import com.example.mediconsultfinalapp.ui.theme.sp16
import com.example.mediconsultfinalapp.ui.theme.sp18
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.main.screens.detail.screen.details.loading.DetailsLoading
import com.example.worldofplantsapp.presentation.main.screens.erroe.screen.ErrorScreen
import com.example.worldofplantsapp.utils.theme.BoGray
import com.example.worldofplantsapp.utils.theme.BoWhite
import com.example.worldofplantsapp.utils.theme.BoxGray
import com.example.worldofplantsapp.utils.theme.BoxWhite
import com.example.worldofplantsapp.utils.util.IsSavedPlantsPainter
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailsScreen(
    uiStateFlow: StateFlow<DetailsUiState>,
    onRetry: () -> Unit,
    themeViewModel: ThemeViewModel,
    onNavigatyYouTube: (String) -> Unit,
    onSavedPlantScreen: (ImmutableList<GetPlantDomainModel>) -> Unit,
    popBackStack: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        onRetry()
    }
    val viewModel: DetailsViewModel = hiltViewModel()
    when (val uiState = uiStateFlow.collectAsState().value) {
        is DetailsUiState.Error -> ErrorScreen {}
        is DetailsUiState.Loading -> DetailsLoading()
        is DetailsUiState.Success -> {
            DetailsScreenLoaded(
                uiState = uiState,
                themeViewModel = themeViewModel,
                onNavigatyYouTube = onNavigatyYouTube,
                getPlantUiModel = uiState.model,
                onSavedPlantScreen = onSavedPlantScreen,
                isSaved = uiState.isSaved,
                popBackStack = popBackStack
            )
        }
    }
}

@Composable
fun DetailsScreenLoaded(
    modifier: Modifier = Modifier,
    uiState: DetailsUiState.Success,
    themeViewModel: ThemeViewModel,
    onNavigatyYouTube: (String) -> Unit,
    onSavedPlantScreen: (ImmutableList<GetPlantDomainModel>) -> Unit,
    getPlantUiModel: ImmutableList<GetPlantDomainModel>,
    isSaved: Boolean,
    popBackStack: () -> Unit,
) {
    val switchState by remember { themeViewModel.isDarkThemeEnabled }
    var showDialog by remember { mutableStateOf(false) }
    val verticalScrollState = rememberScrollState()


    Column(
        modifier = modifier
            .padding(bottom = dp125)
            .verticalScroll(verticalScrollState)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(dp220)
                .clickable { showDialog = true },
        ) {
            AsyncImage(
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = uiState.model.getOrNull(0)?.imageDetails?.url,
                contentDescription = null,
                placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
            )
            IconButton(
                modifier = modifier.padding(top = dp30, start = dp10),
                onClick = { popBackStack() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon_plant),
                    contentDescription = null,
                )
            }
        }
        if (showDialog) {
            AlertDialog(
                modifier = Modifier
                    .height(dp250)
                    .fillMaxWidth(),
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    AsyncImage(
                        modifier = modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(dp20)),
                        contentScale = ContentScale.Crop,
                        model = uiState.model.getOrNull(0)?.imageDetails?.url,
                        contentDescription = null
                    )
                },
                containerColor = if (switchState) BoxGray else BoxWhite,
            )
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(if (switchState) BoxGray else BoxWhite),
        ) {
            Column(
                modifier = modifier.padding(horizontal = dp10)
            ) {
                Row {
                    Text(
                        text = "${uiState.model.getOrNull(0)?.biologicalName},",
                        modifier = modifier
                            .weight(1f)
                            .padding(top = dp15),
                        fontSize = sp18,
                        fontWeight = FontWeight.SemiBold
                    )
                    IsSavedPlantsPainter(onSavedPlantScreen, getPlantUiModel, isSaved)
                }
                Row(
                    modifier = modifier.padding(top = dp10)
                ) {
                    Text(
                        text = "Известен как:",
                        fontSize = 14.sp,
                        color = if (switchState) Color.Gray else Color.DarkGray
                    )
                    Text(
                        modifier = modifier.padding(start = dp5),
                        text = "${uiState.model.getOrNull(0)?.famous}",
                        fontSize = sp16,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row(
                    modifier = modifier.padding(top = dp3)
                ) {
                    Text(
                        text = "Ботаническое звание:",
                        fontSize = 14.sp,
                        color = if (switchState) Color.Gray else Color.DarkGray
                    )
                    Text(
                        modifier = modifier.padding(start = dp5),
                        text = "${uiState.model.getOrNull(0)?.biologicalName}",
                        fontSize = sp16,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row(
                    modifier = modifier.padding(top = dp15)
                ) {
                    Icon(
                        modifier = modifier.padding(top = dp1),
                        painter = painterResource(id = R.drawable.icon_photo_plant),
                        contentDescription = null,
                        tint = if (switchState) Color.Gray else Color.DarkGray
                    )
                    Text(
                        modifier = modifier.padding(start = dp4),
                        fontWeight = FontWeight.Medium,
                        fontSize = sp15,
                        text = "Фотогрфии ${uiState.model.getOrNull(0)?.biologicalName}",
                        color = if (switchState) Color.Gray else Color.DarkGray
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(dp170)
                        .padding(top = dp5, bottom = dp10),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth(0.5f)
                            .padding(end = dp5)
                            .clip(RoundedCornerShape(dp15))
                    ) {
                        AsyncImage(
                            modifier = modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            model = uiState.model.getOrNull(0)?.image?.url,
                            contentDescription = null,
                            placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
                        )
                    }
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = dp5)
                            .clip(RoundedCornerShape(dp15))
                    ) {
                        AsyncImage(
                            modifier = modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            model = uiState.model.getOrNull(0)?.little?.url,
                            contentDescription = null,
                            placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
                        )
                    }
                }
            }
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dp10)
                .background(if (switchState) BoxGray else BoxWhite),
        ) {
            Column(
                modifier = modifier
                    .padding(horizontal = dp10)
                    .padding(top = dp15)
            ) {
                Row {
                    Icon(
                        modifier = modifier.padding(top = dp1),
                        painter = painterResource(id = R.drawable.icon_book_plant),
                        contentDescription = null,
                        tint = if (switchState) Color.White else Color.Black
                    )
                    Text(
                        modifier = modifier.padding(start = dp5),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = sp18,
                        text = "Ключевые факты",
                        color = if (switchState) Color.White else Color.Black
                    )
                }
                Text(
                    modifier = modifier.padding(top = dp10, bottom = dp15),
                    text = "${uiState.model.getOrNull(0)?.keyFact}",
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dp10)
                .background(if (switchState) BoxGray else BoxWhite),
        ) {
            Column(
                modifier = modifier
                    .padding(horizontal = dp10)
                    .padding(top = dp15)
            ) {
                Row {
                    Icon(
                        modifier = modifier.padding(top = dp1),
                        painter = painterResource(id = R.drawable.plant_icon),
                        contentDescription = null,
                        tint = if (switchState) Color.White else Color.Black
                    )
                    Text(
                        modifier = modifier.padding(start = dp6),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = sp18,
                        text = "Ухаживание",
                        color = if (switchState) Color.White else Color.Black
                    )
                }
                Spacer(modifier = modifier.height(dp15))
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(dp35)
                        .clip(RoundedCornerShape(dp5))
                        .background(if (switchState) BoGray else BoWhite),
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = dp10),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = modifier.weight(1f),
                            text = "Полив",
                            color = if (switchState) Color.LightGray else Color.DarkGray,
                            fontWeight = FontWeight.Medium,
                        )
                        Text(
                            fontWeight = FontWeight.SemiBold,
                            text = "${uiState.model.getOrNull(0)?.water}",
                        )
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = dp12)
                        .padding(horizontal = dp10),
                ) {
                    Text(
                        modifier = modifier.weight(1f),
                        text = "Почева",
                        color = if (switchState) Color.LightGray else Color.DarkGray,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        text = "${uiState.model.getOrNull(0)?.climateZon}",
                    )
                }
                Spacer(modifier = modifier.height(dp12))
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(dp35)
                        .clip(RoundedCornerShape(dp5))
                        .background(if (switchState) BoGray else BoWhite),
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = dp10),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = modifier.weight(1f),
                            text = "Температура",
                            color = if (switchState) Color.LightGray else Color.DarkGray,
                            fontWeight = FontWeight.Medium,
                        )
                        Text(
                            modifier = modifier,
                            fontWeight = FontWeight.SemiBold,
                            text = "${uiState.model.getOrNull(0)?.temperature}",
                        )
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = dp12)
                        .padding(horizontal = dp10), verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.weight(1f),
                        text = "Удобрение",
                        color = if (switchState) Color.LightGray else Color.DarkGray,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        text = "${uiState.model.getOrNull(0)?.fertilizer}",
                    )
                }

                Spacer(modifier = modifier.height(dp12))
                val video =
                    "как правильно ухаживать за Растением ${uiState.model.getOrNull(0)?.name}"
                Box(modifier = modifier
                    .padding(bottom = dp15)
                    .fillMaxWidth()
                    .height(dp35)
                    .clip(RoundedCornerShape(dp5))
                    .background(if (switchState) BoGray else BoWhite)
                    .clickable { onNavigatyYouTube(video) }) {
                    Row(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = dp10),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = modifier.weight(1f),
                            text = "Еще ухаживание за: ${uiState.model.getOrNull(0)?.name}",
                            color = if (switchState) Color.LightGray else Color.DarkGray,
                            fontWeight = FontWeight.Medium,
                        )
                        Image(
                            painter = painterResource(id = R.drawable.youtube_icon_plant),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dp10)
                .background(if (switchState) BoxGray else BoxWhite),
        ) {
            Column(
                modifier = modifier
                    .padding(horizontal = dp10)
                    .padding(top = dp15)
            ) {
                Row {
                    Icon(
                        modifier = modifier.padding(top = dp1),
                        painter = painterResource(id = R.drawable.icon_description_plant),
                        contentDescription = null,
                        tint = if (switchState) Color.White else Color.Black
                    )
                    Text(
                        modifier = modifier.padding(start = dp5),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = sp18,
                        text = "Описание",
                        color = if (switchState) Color.White else Color.Black
                    )
                }
                Text(
                    modifier = modifier.padding(top = dp10, bottom = dp15),
                    text = "${uiState.model.getOrNull(0)?.descriptions}",
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    MaterialTheme {
        DetailsScreenLoaded(
            uiState = DetailsUiState.Success(
                model = persistentListOf(),
            ),
            themeViewModel = remember { ThemeViewModel() },
            onNavigatyYouTube = {},
            onSavedPlantScreen = {},
            getPlantUiModel = persistentListOf(),
            isSaved = false,
            popBackStack = {}
        )
    }
}
