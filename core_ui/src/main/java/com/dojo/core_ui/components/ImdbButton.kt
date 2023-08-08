package com.dojo.core_ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dojo.core_ui.theme.GrayImdb
import com.dojo.core_ui.theme.ImdbTheme

@Composable
fun ImdbButton(
    text: String = "",
    enabled: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary ,
            disabledBackgroundColor = MaterialTheme.colors.primaryVariant,
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
            color = if (enabled) MaterialTheme.colors.background else GrayImdb
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 320
)
@Composable
fun ButtonEnabledPreview(
) {
    ImdbTheme() {
        ImdbButton(
            text = "Login",
            enabled = true,
            onClick = { /*TODO*/ },
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 320
)
@Composable
fun ButtonDisabledPreview(
) {
    ImdbTheme() {
        ImdbButton(
            text = "Login",
            enabled = false,
            onClick = { /*TODO*/ },
        )
    }
}


