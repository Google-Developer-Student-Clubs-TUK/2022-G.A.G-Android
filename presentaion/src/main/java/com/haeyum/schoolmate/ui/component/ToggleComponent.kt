package com.haeyum.schoolmate.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haeyum.schoolmate.R
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme

object ToggleComponent {
    @Composable
    fun Toggle(value: Boolean, onValueChange: (Boolean) -> Unit) {
        val circleX by animateDpAsState(targetValue = if (value) 22.dp else 0.dp)

        Button(
            modifier = Modifier.size(width = 42.dp, height = 20.dp),
            shape = AbsoluteRoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = if (value) MaterialTheme.colors.secondary else MaterialTheme.colors.secondaryVariant),
            contentPadding = PaddingValues(0.dp),
            onClick = {
                onValueChange(!value)
            }
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .offset(x = circleX)
                        .background(color = Color.White, shape = CircleShape)
                )
            }
        }
    }

    @Composable
    fun TogglePin(value: Boolean, onValueChange: (Boolean) -> Unit) {
        IconButton(
            onClick = {
                onValueChange(!value)
            }
        ) {
            Image(
                painter = painterResource(id = if (value) R.drawable.ic_pin_on else R.drawable.ic_pin_off),
                contentDescription = "pin change to $value",
            )
        }
    }
}

@Preview
@Composable
private fun TogglePreview() {
    SchoolmateTheme {
        val (toggle, setToggle) = remember { mutableStateOf(false) }
        ToggleComponent.Toggle(value = toggle, onValueChange = setToggle)
    }
}

@Preview
@Composable
private fun TogglePinPreview() {
    SchoolmateTheme {
        val (toggle, setToggle) = remember { mutableStateOf(false) }
        ToggleComponent.TogglePin(value = toggle, onValueChange = setToggle)
    }
}