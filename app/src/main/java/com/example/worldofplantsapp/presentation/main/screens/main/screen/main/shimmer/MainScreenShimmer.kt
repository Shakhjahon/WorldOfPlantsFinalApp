package com.example.worldofplantsapp.presentation.main.screens.main.screen.main.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp120
import com.example.mediconsultfinalapp.ui.theme.dp122
import com.example.mediconsultfinalapp.ui.theme.dp130
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp17
import com.example.mediconsultfinalapp.ui.theme.dp200
import com.example.mediconsultfinalapp.ui.theme.dp230
import com.example.mediconsultfinalapp.ui.theme.dp25
import com.example.mediconsultfinalapp.ui.theme.dp250
import com.example.mediconsultfinalapp.ui.theme.dp40
import com.example.mediconsultfinalapp.ui.theme.dp8
import com.example.worldofplantsapp.utils.theme.BoxGray
import com.example.worldofplantsapp.utils.theme.BoxWhite
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel

@Composable
fun MainScreenShimmer(
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel,
    brush: Brush,
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }

    val backgroundColor = if (switchState) BoxGray else BoxWhite

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ShimmerBox(
            modifier = modifier,
            backgroundColor = backgroundColor,
            brush = brush,
            innerContent = {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = dp40)
                ) {
                    ShimmerElement(
                        modifier = modifier
                            .fillMaxWidth(0.8f)
                            .height(dp25)
                            .padding(start = dp10),
                        brush = brush
                    )
                    ShimmerRow(
                        modifier = modifier,
                        itemCount = 5,
                        brush = brush,
                        itemModifier = modifier
                            .padding(top = dp8, start = dp10, bottom = dp8)
                            .width(dp120)
                            .height(dp120)
                    )
                }
            },
        )

        ShimmerBox(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dp10),
            backgroundColor = backgroundColor,
            brush = brush,
            innerContent = {
                ShimmerElement(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(dp230 )
                        .padding(top = dp10, bottom = dp10)
                        .padding(horizontal = dp10),
                    brush = brush
                )
            },
        )

        ShimmerBox(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dp10),
            backgroundColor = backgroundColor,
            brush = brush,
            innerContent = {
                ShimmerRow(
                    modifier = modifier,
                    itemCount = 3,
                    rowCount = 1,
                    brush = brush,
                    itemModifier = modifier
                        .width(195.dp)
                        .height(dp130)
                        .padding(dp8)
                )
            },
        )
        ShimmerBox(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dp10),
            backgroundColor = backgroundColor,
            brush = brush,
            innerContent = {
                ShimmerColumn(
                    modifier = modifier,
                    itemCount = 3,
                    brush = brush,
                    itemModifier = modifier
                        .fillMaxWidth()
                        .height(dp122)
                        .padding(top = dp15)
                        .padding(horizontal = dp15)
                )
            },
        )
    }
}

@Composable
fun ShimmerBox(
    modifier: Modifier,
    backgroundColor: Color,
    brush: Brush,
    innerContent: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        innerContent()
    }
}

@Composable
fun ShimmerElement(
    modifier: Modifier,
    brush: Brush
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(dp17))
            .background(brush)
    )
}

@Composable
fun ShimmerRow(
    modifier: Modifier,
    itemCount: Int,
    rowCount: Int = 1,
    brush: Brush,
    itemModifier: Modifier,
) {
    Column {
        repeat(rowCount) {
            Row {
                repeat(itemCount) {
                    ShimmerElement(modifier = itemModifier, brush = brush)
                }
            }
        }
    }
}

@Composable
fun ShimmerColumn(
    modifier: Modifier, itemCount: Int, brush: Brush, itemModifier: Modifier
) {
    Column {
        repeat(itemCount) {
            ShimmerElement(modifier = itemModifier, brush = brush)
        }
    }
}

@Preview
@Composable
private fun MainScreenShimmerPreview() {
    MaterialTheme {
        MainScreenShimmer(
            themeViewModel = ThemeViewModel(), brush = Brush.linearGradient(
                listOf(
                    Color.White.copy(alpha = 0.3f),
                    Color.White.copy(alpha = 0.5f),
                    Color.White.copy(alpha = 1.0f),
                    Color.White.copy(alpha = 0.5f),
                    Color.White.copy(alpha = 0.3f),
                )
            )
        )
    }
}