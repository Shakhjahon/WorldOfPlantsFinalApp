package com.example.worldofplantsapp.presentation.main.screens.align.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp122
import com.example.mediconsultfinalapp.ui.theme.dp40
import com.example.mediconsultfinalapp.ui.theme.sp22
import com.example.worldofplantsapp.presentation.main.components.items.AdviceItems
import com.example.worldofplantsapp.presentation.main.screens.detail.screen.details.loading.DetailsLoading
import com.example.worldofplantsapp.presentation.main.screens.erroe.screen.ErrorScreen
import com.example.worldofplantsapp.utils.theme.BoxGray
import com.example.worldofplantsapp.utils.theme.BoxWhite
import com.example.worldofplantsapp.utils.theme.ToolBarGray
import com.example.worldofplantsapp.utils.theme.ToolBarWhite
import com.example.worldofplantsapp.utils.util.ComponentText
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AlignScreen(
    modifier: Modifier = Modifier,
    uiStateFlow: StateFlow<AlignUiState>,
    themeViewModel: ThemeViewModel,
    onNavigatyAdviceDetails: (String) -> Unit,
) {
    val viewModel: AlignViewModel = hiltViewModel()
    when (val uiState = uiStateFlow.collectAsState().value) {
        is AlignUiState.Error -> ErrorScreen(callback = viewModel::advicePlants)
        is AlignUiState.Loading -> DetailsLoading()
        is AlignUiState.Success -> {
            AlignScreenLoaded(
                uiState = uiState,
                themeViewModel = themeViewModel,
                onNavigatyAdviceDetails = onNavigatyAdviceDetails
            )
        }
    }
}

@Composable
fun AlignScreenLoaded(
    modifier: Modifier = Modifier,
    uiState: AlignUiState.Success,
    themeViewModel: ThemeViewModel,
    onNavigatyAdviceDetails: (String) -> Unit,
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }

    val scrollState = rememberScrollState()
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(if (switchState) ToolBarGray else ToolBarWhite),
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Советы по растениям",
                    fontSize = sp22,
                    fontWeight = FontWeight.Medium,
                    color = if (switchState) Color.White else Color.Black,
                    modifier = modifier.padding(top = dp40, bottom = dp10),
                )
            }
        }
        Column(
            modifier = modifier.verticalScroll(scrollState)
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp10)
                    .background(if (switchState) BoxGray else BoxWhite)
            ) {
                Column {
                    ComponentText(text = "Узнайте больше о Деревьях")
                    LazyRow(
                        modifier = modifier.padding(top = dp10, bottom = dp10)
                    ) {
                        items(uiState.trees) { model ->
                            AdviceItems(
                                image = model.imageDetails.url,
                                text = "Советы по ухаживанию: ${model.name}",
                                themeViewModel = themeViewModel,
                                adviceId = model.objectId,
                                onNavigatyAdviceDetails = onNavigatyAdviceDetails
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp10)
                    .background(if (switchState) BoxGray else BoxWhite)
            ) {
                Column {
                    ComponentText(text = "Узнайте больше о Фруктах")
                    LazyRow(
                        modifier = modifier.padding(top = dp10, bottom = dp10)
                    ) {
                        items(uiState.fruit) { model ->
                            AdviceItems(
                                image = model.imageDetails.url,
                                text = "Советы по ухаживанию: ${model.name}",
                                themeViewModel = themeViewModel,
                                adviceId = model.objectId,
                                onNavigatyAdviceDetails = onNavigatyAdviceDetails
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp10)
                    .background(if (switchState) BoxGray else BoxWhite)
            ) {
                Column {
                    ComponentText(text = "Узнайте больше о Ягодах")
                    LazyRow(
                        modifier = modifier.padding(top = dp10, bottom = dp10)
                    ) {
                        items(uiState.berries) { model ->
                            AdviceItems(
                                image = model.imageDetails.url,
                                text = "Советы по ухаживанию: ${model.name}",
                                themeViewModel = themeViewModel,
                                adviceId = model.objectId,
                                onNavigatyAdviceDetails = onNavigatyAdviceDetails
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp10)
                    .background(if (switchState) BoxGray else BoxWhite)
            ) {
                Column {
                    ComponentText(text = "Узнайте больше о Цветах")
                    LazyRow(
                        modifier = modifier.padding(top = dp10, bottom = dp10)
                    ) {
                        items(uiState.flowers) { model ->
                            AdviceItems(
                                image = model.imageDetails.url,
                                text = "Советы по ухаживанию: ${model.name}",
                                themeViewModel = themeViewModel,
                                adviceId = model.objectId,
                                onNavigatyAdviceDetails = onNavigatyAdviceDetails
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp10)
                    .background(if (switchState) BoxGray else BoxWhite)
            ) {
                Column {
                    ComponentText(text = "Узнайте больше о Домашних растениях")
                    LazyRow(
                        modifier = modifier.padding(top = dp10, bottom = dp10)
                    ) {
                        items(uiState.homeFlowers) { model ->
                            AdviceItems(
                                image = model.image.url,
                                text = "Советы по ухаживанию: ${model.name}",
                                themeViewModel = themeViewModel,
                                onNavigatyAdviceDetails = onNavigatyAdviceDetails,
                                adviceId = model.objectId
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp10, bottom = dp122)
                    .background(if (switchState) BoxGray else BoxWhite)
            ) {
                Column {
                    ComponentText(text = "Узнайте больше о Овощах")
                    LazyRow(
                        modifier = modifier.padding(top = dp10, bottom = dp10)
                    ) {
                        items(uiState.vegetables) { model ->
                            AdviceItems(
                                image = model.imageDetails.url,
                                text = "Советы по ухаживанию: ${model.name}",
                                themeViewModel = themeViewModel,
                                adviceId = model.objectId,
                                onNavigatyAdviceDetails = onNavigatyAdviceDetails
                            )
                        }
                    }
                }
            }
        }
    }
}
