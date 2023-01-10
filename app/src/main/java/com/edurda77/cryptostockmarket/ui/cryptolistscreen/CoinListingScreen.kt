package com.edurda77.cryptostockmarket.ui.cryptolistscreen

import android.content.res.Resources
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edurda77.cryptostockmarket.R
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
        isRefreshing = viewModel.state.isLoading
    )
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = {
                    viewModel.onEvent(
                        CryptoCoinsEvent.OnSearch(it)
                    )
                },
                modifier = Modifier
                    .padding(top=16.dp, start = 4.dp)
                    .weight(5f),
                placeholder = {
                    Text(text = "Search...")
                },
                maxLines = 1,
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = md_theme_light_primary,
                    unfocusedBorderColor = md_theme_light_primary)
            )
            IconButton(
                modifier = Modifier
                    .padding(top=16.dp, start = 4.dp)
                    .weight(1f),
                onClick = {
                    viewModel.onEvent(CryptoCoinsEvent.ToggleOrderSection)
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_baseline_filter_list_24),
                    contentDescription = "Сортировка",
                )
            }
        }
        AnimatedVisibility(
            visible = state.isOrderSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            OrderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                coinOrder = state.coinOrder,
                onOrderChange = {
                    viewModel.onEvent(CryptoCoinsEvent.Order(it))
                }
            )
        }
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