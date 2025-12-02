package com.github.carobatata.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.github.carobatata.currencyconverter.ui.ChangeCurrencyScreen
import com.github.carobatata.currencyconverter.ui.CurrencyConverterScreen
import com.github.carobatata.currencyconverter.ui.ScreenChangeCurrency
import com.github.carobatata.currencyconverter.ui.ScreenCurrencyConverter
import com.github.carobatata.currencyconverter.ui.ScreenExampleDataClass
import com.github.carobatata.currencyconverter.ui.theme.CurrencyConverterTheme
import com.github.carobatata.currencyconverter.ui.theme.app_blue
import com.github.carobatata.currencyconverter.ui.theme.app_light_blue

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConverterTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            navigationIcon = {
                                val showBackIcon =
                                    currentDestination?.route != ScreenCurrencyConverter::class.qualifiedName

                                if (showBackIcon) {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = null,
                                            tint = app_light_blue,
                                        )
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = app_blue,
                                titleContentColor = app_light_blue,
                            ),
                            title = {
                                Text(
                                    stringResource(R.string.app_name),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        )
                    },
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ScreenCurrencyConverter,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<ScreenCurrencyConverter> {
                            CurrencyConverterScreen {
                                navController.navigate(ScreenChangeCurrency)
                            }
                        }
                        composable<ScreenChangeCurrency> {
                            ChangeCurrencyScreen()
                        }
                        composable<ScreenExampleDataClass> {
                            val args = it.toRoute<ScreenExampleDataClass>()
                            Text(
                                "${args.name} and ${args.age} years old"
                            )
                        }
                    }
                }
            }
        }
    }
}
