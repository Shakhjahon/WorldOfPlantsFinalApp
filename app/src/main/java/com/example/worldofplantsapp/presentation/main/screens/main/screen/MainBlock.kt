package com.example.worldofplantsapp.presentation.main.screens.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp125
import com.example.mediconsultfinalapp.ui.theme.dp40
import com.example.worldofplantsapp.presentation.main.components.items.BackgroundItem
import com.example.worldofplantsapp.presentation.main.components.items.BerriesItem
import com.example.worldofplantsapp.presentation.main.components.items.Vegetables
import com.example.worldofplantsapp.presentation.main.screens.main.screen.horizontal.pager.HorizontalPager
import com.example.worldofplantsapp.utils.theme.BoxGray
import com.example.worldofplantsapp.utils.theme.BoxWhite
import com.example.worldofplantsapp.utils.util.ComponentText
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MainBlock(
    modifier: Modifier = Modifier,
    uiState: MainScreenUiState.Success,
    onNavigatyDetails: (String) -> Unit,
    themeViewModel: ThemeViewModel,
    onNavigatyCategoryDetails: (String) -> Unit
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }

    val rememberScroll = rememberScrollState()

    BoxWithConstraints {
        val height = maxHeight
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = dp125)
                .verticalScroll(rememberScroll)
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(if (switchState) BoxGray else BoxWhite)
            ) {
                Column(
                    modifier = modifier.padding(top = dp40)
                ) {
                    ComponentText(
                        text = "Ягодные растения"
                    )
                    LazyRow(
                        state = rememberLazyListState()
                    ) {
                        items(uiState.berries, key = { it.objectId }) { model ->
                            BerriesItem(
                                image = model.image.url,
                                text = model.name,
                                plantId = model.objectId,
                                onNavigatyDetails = onNavigatyDetails,
                                themeViewModel = themeViewModel
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
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    HorizontalPager(
                        floraList = uiState.flowers,
                        navigateToDetails = onNavigatyDetails,
                        themeViewModel = themeViewModel
                    )
                }
            }

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp10)
                    .background(if (switchState) BoxGray else BoxWhite)
            ) {
                Column {
                    ComponentText(text = "Исследуйте растения")
                    LazyRow(
                        modifier = modifier.padding(top = dp10, bottom = dp10),
                    ) {
                        items(uiState.plantItem, key = { it.objectId }) { model ->
                            BackgroundItem(
                                model = model.image.url,
                                text = model.name,
                                categoryId = model.objectId,
                                onNavigatyCategoryDetails = onNavigatyCategoryDetails,
                                themeViewModel = themeViewModel
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp10)
                    .height(height)
                    .background(if (switchState) BoxGray else BoxWhite)
            ) {
                Column {
                    ComponentText(text = "Овощные растения")
                    LazyColumn(
                        state = rememberLazyListState(),
                    ) {
                        items(uiState.vegetables, key = { it.objectId }) { model ->
                            Vegetables(
                                image = model.image.url,
                                plantId = model.objectId,
                                onNavigatyDetails = onNavigatyDetails,
                                text = model.descriptions,
                                themeViewModel = themeViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun MainBlockPreview() {
    MaterialTheme {
        MainBlock(
            uiState = MainScreenUiState.Success(
                berries = persistentListOf(),
                flowers = persistentListOf(),
                homeFlowers = persistentListOf(),
                vegetables = persistentListOf(),
                plantItem = persistentListOf()
            ),
            onNavigatyDetails = {},
            themeViewModel = ThemeViewModel(),
            onNavigatyCategoryDetails = {}
        )
    }
}