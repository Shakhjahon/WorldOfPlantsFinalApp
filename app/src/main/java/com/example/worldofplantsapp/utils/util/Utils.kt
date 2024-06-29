package com.example.worldofplantsapp.utils.util

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.sp20
import com.example.worldofplantsapp.R
import kotlinx.collections.immutable.ImmutableList
import java.util.regex.Pattern

fun emptyString(): String = ""

fun validateName(name: String): Pair<Boolean, String> {
    return if (name.isNotEmpty()) {
        Pair(true, "")
    } else {
        Pair(false, "Имени")
    }
}

fun isValidEmail(email: String): Boolean {
    val emailPattern = Pattern.compile(
        "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    )
    return emailPattern.matcher(email).matches()
}

fun validateEmail(email: String): Pair<Boolean, String> {
    val emailPattern = Pattern.compile(
        "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    )
    return if (emailPattern.matcher(email).matches()) {
        Pair(true, "")
    } else {
        Pair(false, "Email")
    }
}

fun validatePassword(password: String): Pair<Boolean, String> {
    return if (password.length >= 6) {
        Pair(true, "")
    } else {
        Pair(false, "Пароля.")
    }
}

fun isValidatePassword(password: String): Boolean {
    return (password.length >= 8)
}

fun isValidateName(name: String): Boolean {
    return (name.length >= 5)
}

fun makeToast(
    text: String, context: Context
) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}


@Composable
fun ComponentText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = Modifier.padding(start = dp10, top = dp10, bottom = dp5),
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = sp20
    )
}

@Composable
fun DottedDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    strokeWidth: Float = 1f,
    interval: Float = 10f
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(
            floatArrayOf(interval, interval), 0f
        )

        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(0f, canvasHeight / 1),
            end = androidx.compose.ui.geometry.Offset(canvasWidth, canvasHeight / 1),
            strokeWidth = strokeWidth,
            pathEffect = pathEffect
        )
    }
}


@Composable
fun IsSavedMoviePainter(
    onSaveMovieToCache: (ImmutableList<GetPlantDomainModel>) -> Unit,
    movieModel: ImmutableList<GetPlantDomainModel>,
    isSaved: Boolean
) {
    IconButton(onClick = {
        onSaveMovieToCache(movieModel)
    }) {
        Icon(
            painter = painterResource(
                id = if (isSaved)
                    R.drawable.favorite_icon_is_saved
                else R.drawable.favorite_icon_saved
            ),
            contentDescription = null,
        )
    }
}
