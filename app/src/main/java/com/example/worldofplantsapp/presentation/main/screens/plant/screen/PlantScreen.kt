package com.example.worldofplantsapp.presentation.main.screens.plant.screen

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.mediconsultfinalapp.ui.theme.dp126
import com.example.mediconsultfinalapp.ui.theme.dp13
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp2
import com.example.mediconsultfinalapp.ui.theme.dp30
import com.example.mediconsultfinalapp.ui.theme.dp40
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.dp50
import com.example.mediconsultfinalapp.ui.theme.sp18
import com.example.mediconsultfinalapp.ui.theme.sp20
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.main.components.items.FavoriteItems
import com.example.worldofplantsapp.presentation.main.screens.detail.screen.details.loading.DetailsLoading
import com.example.worldofplantsapp.presentation.main.screens.erroe.screen.ErrorScreen
import com.example.worldofplantsapp.presentation.main.screens.main.screen.MainScreenViewModel
import com.example.worldofplantsapp.utils.theme.BoxGray
import com.example.worldofplantsapp.utils.theme.BoxWhite
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.ToolBarGray
import com.example.worldofplantsapp.utils.theme.ToolBarWhite
import com.example.worldofplantsapp.utils.util.makeToast
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PlantScreen(
    uiStateFlow: StateFlow<PlantUiState>,
    onNavigatyDetails: (String) -> Unit,
    themeViewModel: ThemeViewModel,
    deletePlants: (String) -> Unit,
    deleteAllPlant: () -> Unit,
    context: Context
) {
    val viewModel: MainScreenViewModel = hiltViewModel()
    when (val uiState = uiStateFlow.collectAsState().value) {
        is PlantUiState.Loading -> DetailsLoading()
        is PlantUiState.Success -> {
            PlantScreenSuccess(
                plants = uiState.model,
                onNavigatyDetails = onNavigatyDetails,
                themeViewModel = themeViewModel,
                deletePlants = deletePlants,
                deleteAllPlant = deleteAllPlant,
                context = context
            )
        }

        is PlantUiState.Error -> ErrorScreen(callback = viewModel::fetchPlants)
    }
}

@Composable
fun PlantScreenSuccess(
    modifier: Modifier = Modifier,
    plants: List<GetPlantDomainModel>,
    onNavigatyDetails: (String) -> Unit,
    themeViewModel: ThemeViewModel,
    deletePlants: (String) -> Unit,
    deleteAllPlant: () -> Unit,
    context: Context
) {

    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    var plantList by remember { mutableStateOf(plants) }
    var showDialog by remember { mutableStateOf(false) }
    val visibilityMap = remember { mutableStateMapOf<String, Boolean>() }

    plantList.forEach { plant ->
        visibilityMap.putIfAbsent(plant.objectId, true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(if (switchState) ToolBarGray else ToolBarWhite),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp50, bottom = dp13)
                    .padding(horizontal = dp15),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Мой сад",
                    fontSize = sp20,
                    fontWeight = FontWeight.Medium,
                    color = if (switchState) Color.White else Color.Black,
                    modifier = modifier.padding(start = dp5),
                )
                Icon(
                    modifier = modifier
                        .clip(RoundedCornerShape(dp5))
                        .clickable {
                            if (plants.isNotEmpty()) {
                                showDialog = true
                            } else {
                                makeToast("Нет сохранённых", context)
                            }
                        },
                    painter = painterResource(id = R.drawable.delete_icon_plant),
                    contentDescription = null,
                )
            }
        }
        if (showDialog) {
            DeleteConfirmationDialog(onDismiss = {
                showDialog = false
            }, onConfirm = {
                deleteAllPlant()
                showDialog = false
            }, themeViewModel = themeViewModel
            )
        }
        if (plants.isEmpty()) {
            IsEmptyScreen(
                text = "Нет сохранённых"
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(bottom = dp126)
            ) {
                items(plants, key = { it.objectId }) { model ->
                    FavoriteItems(
                        image = model.image.url,
                        text = model.biologicalName,
                        themeViewModel = themeViewModel,
                        description = model.famous,
                        onNavigatyDetails = onNavigatyDetails,
                        plantId = model.objectId,
                        deletePlants = deletePlants
                    )
                }
            }
        }
    }
}

@Composable
fun IsEmptyScreen(
    modifier: Modifier = Modifier, text: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = dp40),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
        )
        Text(
            modifier = modifier.padding(top = dp15),
            text = text,
            fontSize = sp18,
            fontWeight = FontWeight.SemiBold,
        )
    }
}


@Composable
fun DeleteConfirmationDialog(
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
                    text = "Вы действительно хотите удалить \n все растения",
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
                        Text("Удалить", color = if (switchState) Color.White else Color.Black)
                    }
                }
            }
        }
    }
}