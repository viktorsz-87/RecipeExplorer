package com.andronest.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmSnackbar(
    onDismiss: () -> Unit,
    message: String,
    modifier: Modifier = Modifier
) {

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(initialOffsetY = { 200 }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { 200 }) + fadeOut(),
        modifier = Modifier.padding(16.dp)
    ) {

        Surface(
            shadowElevation = 3.dp,
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = Color(0xB2CDDC39),
        ) {

            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(10.dp)) {

                Icon(
                    modifier=modifier.requiredSize(30.dp),
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Success"
                )

                Spacer(Modifier.width(8.dp))

                TextButton(onClick = { onDismiss() }) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
