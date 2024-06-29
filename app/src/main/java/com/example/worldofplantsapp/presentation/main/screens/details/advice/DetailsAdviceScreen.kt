package com.example.worldofplantsapp.presentation.main.screens.details.advice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.mediconsultfinalapp.ui.theme.dp1
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp125
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp170
import com.example.mediconsultfinalapp.ui.theme.dp220
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.sp18
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.main.screens.detail.screen.details.loading.DetailsLoading
import com.example.worldofplantsapp.presentation.main.screens.erroe.screen.ErrorScreen
import com.example.worldofplantsapp.utils.theme.BoxGray
import com.example.worldofplantsapp.utils.theme.BoxWhite
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailsAdviceScreen(
    uiStateFlow: StateFlow<DetailsAdviceUiState>,
    onRetry: () -> Unit,
    themeViewModel: ThemeViewModel,
) {
    LaunchedEffect(key1 = true) {
        onRetry()
    }
    when (val uiState = uiStateFlow.collectAsState().value) {
        is DetailsAdviceUiState.Error -> ErrorScreen{}
        is DetailsAdviceUiState.Loading -> DetailsLoading()
        is DetailsAdviceUiState.Success -> {
            DetailsAdviceScreenLoaded(
                uiState = uiState,
                themeViewModel = themeViewModel,
            )
        }
    }
}

@Composable
fun DetailsAdviceScreenLoaded(
    modifier: Modifier = Modifier,
    uiState: DetailsAdviceUiState.Success,
    themeViewModel: ThemeViewModel,
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = dp125)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(dp220)
        ) {
            AsyncImage(
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = uiState.model.getOrNull(0)?.imageDetails?.url,
                contentDescription = null,
                placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
            )
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
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
                        text = "Советы",
                        color = if (switchState) Color.White else Color.Black
                    )
                }
                Text(
                    modifier = modifier.padding(top = dp10, bottom = dp15),
                    text = "${uiState.model.getOrNull(0)?.advice}",
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
                Text(
                    modifier = modifier.padding(top = dp10, bottom = dp15),
                    text = "${uiState.model.getOrNull(0)?.descriptions}",
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
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(dp170)
                    .padding(top = dp10, bottom = dp10),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = dp10)
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
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    MaterialTheme {
        DetailsAdviceScreenLoaded(
            uiState = DetailsAdviceUiState.Success(model = persistentListOf()),
            themeViewModel = remember { ThemeViewModel() },
        )
    }
}
