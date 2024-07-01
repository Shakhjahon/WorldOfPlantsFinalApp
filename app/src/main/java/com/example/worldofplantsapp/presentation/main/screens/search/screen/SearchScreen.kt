package com.example.worldofplantsapp.presentation.main.screens.search.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp122
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp20
import com.example.mediconsultfinalapp.ui.theme.dp45
import com.example.mediconsultfinalapp.ui.theme.dp47
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.sp15
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.main.components.items.SearchItems
import com.example.worldofplantsapp.presentation.main.screens.plant.screen.IsEmptyScreen
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onValueChange: (String) -> Unit,
    onNavigatyDetails: (String) -> Unit,
    themeViewModel: ThemeViewModel,
    popBackStack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dp45)
            .padding(horizontal = dp15)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier
                    .clip(RoundedCornerShape(dp5))
                    .clickable { popBackStack() },
                painter = painterResource(id = R.drawable.arrow_back_icon),
                contentDescription = null,
            )
            OutlinedTextField(
                value = uiState.query,
                onValueChange = onValueChange,
                shape = CircleShape,
                textStyle = TextStyle(fontSize = sp15),
                modifier = modifier
                    .fillMaxWidth()
                    .height(dp47)
                    .padding(start = dp10),
                leadingIcon = {
                    Icon(
                        modifier = modifier.size(dp20),
                        painter = painterResource(id = R.drawable.search_icon_focused),
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Green,
                )
            )
        }

        if (uiState.fauna.orEmpty().isEmpty()) {
            IsEmptyScreen(text = "Нет данных")
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(top = dp10, bottom = dp122),
            ) {
                items(uiState.fauna.orEmpty()) {
                    SearchItems(
                        plantId = it.objectId,
                        image = it.image.url,
                        name = it.biologicalName,
                        description = it.famous,
                        themeViewModel = themeViewModel,
                        onNavigatyDetails = onNavigatyDetails,
                    )
                }
            }
        }
    }
}