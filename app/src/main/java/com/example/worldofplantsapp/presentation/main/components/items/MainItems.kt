package com.example.worldofplantsapp.presentation.main.components.items

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp110
import com.example.mediconsultfinalapp.ui.theme.dp115
import com.example.mediconsultfinalapp.ui.theme.dp12
import com.example.mediconsultfinalapp.ui.theme.dp120
import com.example.mediconsultfinalapp.ui.theme.dp13
import com.example.mediconsultfinalapp.ui.theme.dp130
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp150
import com.example.mediconsultfinalapp.ui.theme.dp17
import com.example.mediconsultfinalapp.ui.theme.dp170
import com.example.mediconsultfinalapp.ui.theme.dp180
import com.example.mediconsultfinalapp.ui.theme.dp2
import com.example.mediconsultfinalapp.ui.theme.dp20
import com.example.mediconsultfinalapp.ui.theme.dp220
import com.example.mediconsultfinalapp.ui.theme.dp290
import com.example.mediconsultfinalapp.ui.theme.dp3
import com.example.mediconsultfinalapp.ui.theme.dp400
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.dp55
import com.example.mediconsultfinalapp.ui.theme.dp6
import com.example.mediconsultfinalapp.ui.theme.dp7
import com.example.mediconsultfinalapp.ui.theme.dp70
import com.example.mediconsultfinalapp.ui.theme.dp85
import com.example.mediconsultfinalapp.ui.theme.sp15
import com.example.mediconsultfinalapp.ui.theme.sp16
import com.example.mediconsultfinalapp.ui.theme.sp17
import com.example.mediconsultfinalapp.ui.theme.sp18
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.main.screens.plant.screen.ReminderScreen
import com.example.worldofplantsapp.utils.theme.Blacks
import com.example.worldofplantsapp.utils.theme.BoGray
import com.example.worldofplantsapp.utils.theme.BoWhite
import com.example.worldofplantsapp.utils.theme.BoxGray
import com.example.worldofplantsapp.utils.theme.BoxWhite
import com.example.worldofplantsapp.utils.theme.Grays
import com.example.worldofplantsapp.utils.theme.Greens
import com.example.worldofplantsapp.utils.theme.Reds
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel

@Composable
fun BerriesItem(
    modifier: Modifier = Modifier,
    image: String,
    text: String,
    onNavigatyDetails: (String) -> Unit,
    plantId: String,
    themeViewModel: ThemeViewModel
) {
    var scale by remember { mutableFloatStateOf(1f) }
    val animatedScale by animateFloatAsState(targetValue = scale, label = "")

    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    Column(
        modifier = modifier.padding(start = dp10)
    ) {
        Box(
            modifier = modifier
                .padding(top = dp15)
                .size(dp115)
                .scale(animatedScale)
                .clip(RoundedCornerShape(dp17))
                .pointerInput(Unit) {
                    detectTapGestures(onPress = {
                        scale = 0.95f
                        tryAwaitRelease()
                        scale = 1f
                    }, onTap = { onNavigatyDetails(plantId) })
                },
        ) {
            Column {
                AsyncImage(
                    modifier = modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = image,
                    contentDescription = null,
                    placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
                )
            }
        }
        Text(
            text = text,
            fontSize = sp15,
            modifier = modifier.padding(bottom = dp10),
        )
    }
}

@Composable
fun BackgroundItem(
    categoryId: String,
    modifier: Modifier = Modifier,
    model: String,
    text: String,
    onNavigatyCategoryDetails: (String) -> Unit,
    themeViewModel: ThemeViewModel,
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    Box(modifier = modifier
        .width(dp180)
        .height(dp120)
        .padding(dp6)
        .clip(RoundedCornerShape(dp15))
        .clickable { onNavigatyCategoryDetails(categoryId) }) {
        AsyncImage(
            modifier = modifier.fillMaxSize(),
            model = model,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Blacks)
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = sp18,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun BackgroundItemPreview() {
    MaterialTheme {
        BackgroundItem(
            model = "",
            text = "",
            categoryId = "",
            onNavigatyCategoryDetails = {},
            themeViewModel = ThemeViewModel()
        )
    }
}

@Composable
fun Vegetables(
    modifier: Modifier = Modifier,
    image: String,
    plantId: String,
    text: String,
    onNavigatyDetails: (String) -> Unit,
    themeViewModel: ThemeViewModel,
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dp130)
            .clickable {
                onNavigatyDetails(plantId)
            }
            .padding(top = dp17)
            .padding(horizontal = dp10)
            .clip(RoundedCornerShape(dp17)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = image,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(RoundedCornerShape(dp15))
                    .width(dp150),
                contentDescription = null,
                placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                maxLines = 4,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = dp10)
            )
        }
    }
}

@Composable
fun AdviceItems(
    modifier: Modifier = Modifier,
    image: String,
    text: String,
    themeViewModel: ThemeViewModel,
    adviceId: String,
    onNavigatyAdviceDetails: (String) -> Unit,
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    var scale by remember { mutableFloatStateOf(1f) }
    val animatedScale by animateFloatAsState(targetValue = scale, label = "")

    Box(
        modifier = modifier
            .width(dp400)
            .height(dp220)
            .scale(animatedScale)
            .padding(horizontal = dp15)
            .clip(RoundedCornerShape(dp15))
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    scale = 0.95f
                    tryAwaitRelease()
                    scale = 1f
                }, onTap = { onNavigatyAdviceDetails(adviceId) })
            },
    ) {
        AsyncImage(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(dp15)),
            model = image,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(dp55)
                .padding(bottom = dp10)
                .padding(horizontal = dp20)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(dp12))
                .background(if (switchState) BoGray else BoWhite),
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = modifier.padding(start = dp5, end = dp5),
                    text = text,

                    )
            }
        }
    }
}


@Composable
fun FavoriteItems(
    modifier: Modifier = Modifier,
    plantId: String,
    image: String,
    text: String,
    description: String,
    themeViewModel: ThemeViewModel,
    onNavigatyDetails: (String) -> Unit,
    deletePlants: (String) -> Unit,
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }

    var showDialog by remember { mutableStateOf(false) }
    var showDelete by remember { mutableStateOf(false) }
    var scale by remember { mutableFloatStateOf(1f) }
    val animatedScale by animateFloatAsState(targetValue = scale, label = "")

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dp10)
            .padding(horizontal = dp10)
            .scale(animatedScale)
            .clip(RoundedCornerShape(dp10))
            .background(if (switchState) BoxGray else BoxWhite)

    ) {
        Column {
            Row {
                Row(
                    modifier = modifier
                        .weight(1f)
                        .padding(top = dp10)
                        .clip(RoundedCornerShape(dp10))
                        .clickable { onNavigatyDetails(plantId) }
                        .padding(horizontal = dp15),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = modifier
                            .size(dp110)
                            .clip(RoundedCornerShape(dp10)),
                    ) {
                        AsyncImage(
                            modifier = modifier.fillMaxSize(),
                            model = image,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
                        )
                    }
                    Column(
                        modifier = modifier.padding(start = dp15)
                    ) {
                        Text(
                            text = text,
                            fontSize = sp17,
                            fontWeight = FontWeight.SemiBold,
                            color = if (switchState) Color.White else Color.Black,
                        )
                        Text(
                            fontStyle = FontStyle.Italic,
                            text = description,
                            color = if (switchState) Color.Gray else Color.DarkGray
                        )
                    }
                }
                Icon(
                    modifier = modifier
                        .padding(top = dp5, end = dp10)
                        .clip(RoundedCornerShape(dp5))
                        .clickable { showDelete = true },
                    painter = painterResource(id = R.drawable.more_icon_plant),
                    tint = Color.Gray,
                    contentDescription = null,
                )
            }
            Divider(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp10),
                color = Grays,
                thickness = 0.5.dp
            )
            Row(
                modifier = modifier
                    .padding(top = dp10, bottom = dp10)
                    .padding(horizontal = dp10)
                    .clip(RoundedCornerShape(dp10))
                    .clickable { showDialog = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.alarm_icon_plant),
                    tint = Greens,
                    contentDescription = null,
                )
                Text(
                    modifier = modifier
                        .weight(1f)
                        .padding(start = dp3),
                    text = "Добавить напоминание",
                    fontSize = sp15,
                    color = Greens,
                )
                Icon(
                    modifier = modifier.padding(end = dp5),
                    painter = painterResource(id = R.drawable.note_icon_add),
                    tint = Color.Gray,
                    contentDescription = null,
                )
            }
            if (showDialog) {
                AlertDialog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dp290),
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        ReminderScreen()
                    },
                    containerColor = if (switchState) BoxGray else BoxWhite,
                )
            }
            if (showDelete) {
                AlertDialog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dp70),
                    onDismissRequest = { showDelete = false },
                    confirmButton = {
                        Box(modifier = modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(dp5))
                            .clickable { showDelete = false; deletePlants(plantId) }) {
                            Row(
                                modifier = modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = "Удалить",
                                    fontSize = sp16,
                                    color = Reds,
                                    modifier = modifier.weight(1f)
                                )
                                Icon(
                                    painter = painterResource(id = R.drawable.delete_icon_plant),
                                    contentDescription = null,
                                    tint = Reds,
                                )
                            }
                        }
                    },
                    containerColor = if (switchState) BoxGray else BoxWhite,
                )
            }
        }
    }
}

@Composable
fun CategoryItems(
    modifier: Modifier = Modifier,
    image: String,
    description: String,
    name: String,
    themeViewModel: ThemeViewModel,
    onNavigatyDetails: (String) -> Unit,
    plantId: String
) {

    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    Box(
        modifier = modifier
            .padding(top = dp10)
            .padding(horizontal = dp7)
            .fillMaxWidth()
            .clip(RoundedCornerShape(dp15))
            .background(if (switchState) BoxGray else BoxWhite)
            .clickable { onNavigatyDetails(plantId) },
    ) {
        Column(
            modifier = modifier
                .padding(top = dp13)
                .padding(horizontal = dp13)
        ) {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth()
                    .height(dp170)
                    .clip(RoundedCornerShape(dp7)),
                model = image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
            )
            Text(
                modifier = modifier.padding(top = dp10),
                text = name,
                fontSize = sp18,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                modifier = modifier.padding(top = dp10),
                text = description,
                maxLines = 3,
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = dp10),
                horizontalAlignment = Alignment.End,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(top = dp5)
                        .clip(RoundedCornerShape(dp10))
                        .clickable { onNavigatyDetails(plantId) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Более",
                        modifier = modifier.padding(end = dp2),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_forvard_icon),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}


@Composable
fun SearchItems(
    modifier: Modifier = Modifier,
    image: String,
    name: String,
    description: String,
    themeViewModel: ThemeViewModel,
    onNavigatyDetails: (String) -> Unit,
    plantId: String
) {
    val switchState by remember {
        themeViewModel.isDarkThemeEnabled
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = dp15)
            .clip(RoundedCornerShape(dp10))
            .clickable { onNavigatyDetails(plantId) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .size(dp85)
                .clip(RoundedCornerShape(dp10)),
        ) {
            AsyncImage(
                modifier = modifier.fillMaxSize(),
                model = image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = if (switchState) R.drawable.plaseholder_image_black else R.drawable.plaseholder_image)
            )
        }
        Column(
            modifier = modifier
                .weight(1f)
                .padding(start = dp10),
        ) {
            Text(
                text = name,
                fontSize = sp17,
                fontWeight = FontWeight.SemiBold,
                color = if (switchState) Color.White else Color.Black,
            )
            Text(
                modifier = modifier.padding(top = dp5),
                fontStyle = FontStyle.Italic,
                text = description,
                color = if (switchState) Color.Gray else Color.DarkGray
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.arrow_forvard_icon), contentDescription = null
        )
    }
}