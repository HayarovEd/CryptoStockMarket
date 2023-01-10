package com.edurda77.cryptostockmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.edurda77.cryptostockmarket.ui.cryptolistscreen.CoinListingScreen
import com.edurda77.cryptostockmarket.ui.theme.CryptoStockMarketTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoStockMarketTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoinListingScreen()
                    //DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}