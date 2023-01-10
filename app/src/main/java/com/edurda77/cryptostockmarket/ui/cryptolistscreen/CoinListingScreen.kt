package com.edurda77.cryptostockmarket.ui.cryptolistscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edurda77.cryptostockmarket.ui.theme.md_theme_light_primary
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(start = true)
fun CoinListingScreen(
    //navigator: DestinationsNavigator,
) {
    val viewModel = hiltViewModel<CryptoListViewModel>()
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isloading
    )
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    CryptoCoinsEvent.OnSearch(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = md_theme_light_primary,
                unfocusedBorderColor = md_theme_light_primary)
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(CryptoCoinsEvent.Refresh)
            }
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.cryptoCoins.size) { i ->
                    val coin = state.cryptoCoins[i]
                    CryptoCoinItem(
                        coin = coin,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                /*navigator.navigate(
                                    CompanyInfoScreenDestination(company.symbol)
                                )*/
                            }
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}