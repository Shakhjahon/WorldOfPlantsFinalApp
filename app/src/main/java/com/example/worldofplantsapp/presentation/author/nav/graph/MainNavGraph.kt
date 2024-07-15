package com.example.worldofplantsapp.presentation.author.nav.graph

import android.content.Context
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.worldofplantsapp.presentation.graphMain.bottom.navigation.BottomNavigationUi
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel

const val MAIN_GRAPH_TAG = "MAIN_GRAPH_TAG"
const val BOTTOM_SCAFFOLD = "BOTTOM_SCAFFOLD"

@Composable
fun MainNavGraph(
    themeViewModel: ThemeViewModel,
    onNavigaty: (String) -> Unit,
    context: Context,
    onNavigatyMap: (String) -> Unit,
    onNavigatyTelegram : () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = BOTTOM_SCAFFOLD,
    ) {
        composable(BOTTOM_SCAFFOLD) {
            BottomNavigationUi(
                themeViewModel = themeViewModel,
                onNavigaty = onNavigaty,
                context = context,
                onNavigatyMap = onNavigatyMap,
                onNavigatyTelegram =  onNavigatyTelegram
            )
        }
    }
}

