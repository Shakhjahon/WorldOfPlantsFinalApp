package com.example.worldofplantsapp.presentation.main.screens.category.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp126
import com.example.mediconsultfinalapp.ui.theme.dp13
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp40
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.dp50
import com.example.mediconsultfinalapp.ui.theme.sp18
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.main.components.items.CategoryItems
import com.example.worldofplantsapp.presentation.main.screens.detail.screen.details.loading.DetailsLoading
import com.example.worldofplantsapp.presentation.main.screens.erroe.screen.ErrorScreen
import com.example.worldofplantsapp.utils.theme.ToolBarGray
import com.example.worldofplantsapp.utils.theme.ToolBarWhite
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    uiStateFlow: StateFlow<CategoryUiState>,
    onRetry: () -> Unit,
    themeViewModel: ThemeViewModel,
    onNavigatyDetails: (String) -> Unit,
    popBackStack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        onRetry()
    }
    val viewModel: CategoryViewModel = hiltViewModel()
    when (val uiState = uiStateFlow.collectAsState().value) {
        is CategoryUiState.Error -> ErrorScreen {}
        is CategoryUiState.Loading -> DetailsLoading()
        is CategoryUiState.Success -> {
            CategoryScreenLoaded(
                uiState = uiState,
                themeViewModel = themeViewModel,
                onNavigatyDetails = onNavigatyDetails,
                popBackStack = popBackStack
            )
        }
    }
}

@Composable
fun CategoryScreenLoaded(
    modifier: Modifier = Modifier,
    uiState: CategoryUiState.Success,
    themeViewModel: ThemeViewModel,
    onNavigatyDetails: (String) -> Unit,
    popBackStack: () -> Unit
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(if (switchState) ToolBarGray else ToolBarWhite),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp40, bottom = dp13)
                    .padding(horizontal = dp15),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier
                        .clip(RoundedCornerShape(dp5))
                        .clickable { popBackStack() },
                    painter = painterResource(id = R.drawable.arrow_back_icon),
                    contentDescription = null,
                )
                Text(
                    text = "Исследуйте растения",
                    fontSize = sp18,
                    fontWeight = FontWeight.Medium,
                    color = if (switchState) Color.White else Color.Black,
                    modifier = modifier.padding(start = dp10)
                )
            }
        }
        LazyColumn(
            modifier = modifier.padding(bottom = dp126)
        ) {
            items(uiState.plant) {
                CategoryItems(
                    image = it.image.url,
                    description = it.descriptions,
                    name = it.biologicalName,
                    modifier = modifier,
                    themeViewModel = themeViewModel,
                    plantId = it.objectId,
                    onNavigatyDetails = onNavigatyDetails
                )
            }
        }
    }
}
