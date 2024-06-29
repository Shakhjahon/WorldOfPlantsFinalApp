package com.example.worldofplantsapp.presentation.main.screens.profile.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp60
import com.example.mediconsultfinalapp.ui.theme.sp25
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.app.prefs
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.LedgerFont
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel

@Composable
fun ProfileToolBar(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dp60)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dp15),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Мой профиль",
                fontSize = sp25,
                fontFamily = LedgerFont,
                modifier = modifier.padding(start = dp15)
            )
        }
    }
}


@Composable
fun PlantTheme(
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel,
) {
    var switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    Switch(
        checked = switchState,
        onCheckedChange = {
            switchState = !switchState
            prefs.themeDark = switchState
        },
        colors = SwitchDefaults.colors(
            uncheckedBorderColor = Color.Gray,
            checkedBorderColor = Green,
            uncheckedIconColor = Color.Gray,
            checkedIconColor = Green
        ),
        thumbContent = {
            if (switchState) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_dark_plant),
                    contentDescription = null
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.icon_sunny_plant),
                    contentDescription = null
                )
            }
        },
    )
}