package com.andronest.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreenItemCard(modifier: Modifier = Modifier) {


    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        border = BorderStroke(2.dp,color=Color.Black)
    ) {

        Text("Image and other info here")

    }
}