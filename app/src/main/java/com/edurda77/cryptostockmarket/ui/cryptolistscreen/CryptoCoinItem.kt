package com.edurda77.cryptostockmarket.ui.cryptolistscreen

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edurda77.cryptostockmarket.domain.entities.Coin
import com.edurda77.cryptostockmarket.ui.theme.md_theme_light_primary
import com.edurda77.cryptostockmarket.ui.theme.md_theme_light_primaryContainer

@Composable
fun CryptoCoinItem(
    coin: Coin,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = md_theme_light_primary)
            .clickable {
                //TO-DO
            }
    ) {
        Text(
            modifier = Modifier
                .padding(start = 4.dp),
            text = coin.symbol,
            color = MaterialTheme.colors.onSurface,
            fontSize = 24.sp
        )
        Text(
            modifier = Modifier
                .padding(start = 4.dp),
            text = coin.name,
            color = MaterialTheme.colors.onSurface,
            fontSize = 20.sp
        )
    }
}