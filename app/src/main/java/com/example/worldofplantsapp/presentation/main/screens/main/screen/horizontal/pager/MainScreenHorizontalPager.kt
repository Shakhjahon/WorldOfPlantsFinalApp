package com.example.worldofplantsapp.presentation.main.screens.main.screen.horizontal.pager

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp17
import com.example.mediconsultfinalapp.ui.theme.dp220
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.model.plant.GetPlantUioModel
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HorizontalPager(
    floraList: List<GetPlantUioModel>,
    navigateToDetails: (String) -> Unit,
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel
) {
    MainScreenHorizontal(floraList, navigateToDetails, modifier, themeViewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenHorizontal(
    floraList: List<GetPlantUioModel>,
    navigateToDetails: (String) -> Unit,
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier.padding(top = dp10, bottom = dp10)) {
        val pagerState = rememberPagerState(pageCount = { floraList.size })
        LaunchedEffect(Unit) {
            while (true) {
                delay(4000)
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                scope.launch {
                    pagerState.animateScrollToPage(
                        nextPage, animationSpec = tween(300)
                    )
                }
            }
        }
        HorizontalItems(
            pagerState,
            floraList,
            navigateToDetails,
            themeViewModel
        )
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(top = 24.dp)
                .align(Alignment.BottomCenter), horizontalArrangement = Arrangement.Center
        ) {
            repeat(floraList.size) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.White else Color.White.copy(alpha = 0.5f)
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(12.dp)

                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun HorizontalItems(
    pagerState: PagerState,
    plantList: List<GetPlantUioModel>,
    navigateToDetails: (String) -> Unit,
    themeViewModel: ThemeViewModel
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    HorizontalPager(
        beyondBoundsPageCount = 2,
        state = pagerState,
    ) { position ->
        val plant = plantList[position]
        AsyncImage(
            model = plant.image.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image),

            modifier = Modifier
                .fillMaxWidth()
                .height(dp220)
                .padding(top = dp5, bottom = dp5)
                .padding(horizontal = dp10)
                .clip(RoundedCornerShape(dp17))
                .clickable { navigateToDetails(plantList[position].objectId) },

            )
    }
}