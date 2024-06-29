package com.example.worldofplantsapp.app

import android.content.Context
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.example.worldofplantsapp.presentation.graphMain.manGraph.PlantsAppNavGraph
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun PlantsMainApp(
    destinationFlow: SharedFlow<Pair<String, Boolean>>,
    themeViewModel: ThemeViewModel,
    onNavigaty: (String) -> Unit,
    context: Context,
    onNavigatyMap: (String) -> Unit,
) {
    val navController = rememberNavController()

    val (routeName, isBackStackClear) = destinationFlow.collectAsState(initial = "" to false).value

    if (routeName.isNotEmpty()) {
        navController.navigate(routeName) {
            if (isBackStackClear) popUpTo(0)
        }
    }
    Surface {
        PlantsAppNavGraph(
            navController = navController,
            themeViewModel = themeViewModel,
            onNavigaty = onNavigaty,
            context = context,
            onNavigatyMap = onNavigatyMap
        )
    }
}