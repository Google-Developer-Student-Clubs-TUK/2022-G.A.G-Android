package com.haeyum.schoolmate.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeyum.schoolmate.R
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

object HeaderComponent {
    @Composable
    fun Header(
        title: String,
        backgroundColor: Color = MaterialTheme.colors.background,
        onBackClick: () -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(10.dp)
                .background(backgroundColor)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick, modifier = Modifier.size(24.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(TextColor)
                )
            }
            Text(
                text = title,
                color = TextColor,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.size(24.dp))
        }
    }

    @Composable
    fun LargeHeader(
        title: String,
        description: String,
        backgroundColor: Color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .padding(top = 44.dp)
                .background(backgroundColor)
        ) {
            Text(
                text = title,
                color = TextColor,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = description,
                modifier = Modifier.padding(top = 5.dp),
                color = TextColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HeaderDarkPreview() {
    SchoolmateTheme() {
        HeaderComponent.Header(title = "Header", onBackClick = {})
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun HeaderLightPreview() {
    SchoolmateTheme() {
        HeaderComponent.Header(title = "Header", onBackClick = {})
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LargeHeaderDarkPreview() {
    SchoolmateTheme {
        HeaderComponent.LargeHeader(title = "Large Header", description = "Hello, Large Header!")
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun LargeHeaderLightPreview() {
    SchoolmateTheme {
        HeaderComponent.LargeHeader(title = "Large Header", description = "Hello, Large Header!")
    }
}