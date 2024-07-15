package com.example.worldofplantsapp.presentation.graphMain.bottom.navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mediconsultfinalapp.ui.theme.dp100
import com.example.mediconsultfinalapp.ui.theme.dp125
import com.example.mediconsultfinalapp.ui.theme.dp19
import com.example.mediconsultfinalapp.ui.theme.dp4
import com.example.mediconsultfinalapp.ui.theme.dp40
import com.example.mediconsultfinalapp.ui.theme.dp8
import com.example.mediconsultfinalapp.ui.theme.sp14
import com.example.worldofplantsapp.utils.theme.Black
import com.example.worldofplantsapp.utils.theme.BoxGray
import com.example.worldofplantsapp.utils.theme.BoxWhite
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel

@SuppressLint("AutoboxingStateCreation", "RestrictedApi, UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationUi(
    themeViewModel: ThemeViewModel,
    modifier: Modifier = Modifier,
    onNavigaty: (String) -> Unit,
    context: Context,
    onNavigatyMap: (String) -> Unit,
    onNavigatyTelegram: () -> Unit
) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, themeViewModel = themeViewModel)
        },
    ) { innerPadding ->
        BottomNavGraph(
            modifier = modifier.padding(innerPadding),
            navHostController = navController,
            themeViewModel = themeViewModel,
            onNavigatyYouTube = onNavigaty,
            context = context,
            onNavigatyMap = onNavigatyMap,
            onNavigatyTelegram = onNavigatyTelegram
        )

    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    themeViewModel: ThemeViewModel,
) {

    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    val listIcon = listOf(
        BottomIcon.Home,
        BottomIcon.Search,
        BottomIcon.Plant,
        BottomIcon.Align,
        BottomIcon.Profile,
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Box {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(dp125)
                .background(if (switchState) BoxGray else BoxWhite),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listIcon.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    modifier: Modifier = Modifier,
    screen: BottomIcon,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background = if (selected) Black.copy(alpha = 0.3f) else Color.Transparent

    val containerColor = if (selected) Color.White else Color.Black

    Spacer(modifier = modifier.padding(bottom = dp100))
    Box(
        modifier = modifier
            .height(dp40)
            .clip(CircleShape)
            .background(background)
            .clickable(
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
            ),
    ) {
        Row(
            modifier = modifier
                .padding(top = dp8, bottom = dp8)
                .padding(horizontal = dp19),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(dp4)
        ) {
            Icon(
                painter = painterResource(
                    id = if (selected) screen.iconFocused
                    else screen.icon
                ),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )

            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    fontSize = sp14,
                    color = containerColor,
                )
            }
        }
    }
}
