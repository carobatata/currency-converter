package com.github.carobatata.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
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
                    val navController = rememberNavController() //to navigate from screen to screen
                    NavHost(
                        navController = navController,
                        startDestination = ScreenCurrencyConverter,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<ScreenCurrencyConverter> {
                            CurrencyConverterScreen {
                                navController.navigate(
                                    ScreenExampleDataClass(
                                        "William",
                                        22
                                    )
                                )
                            }
                        }
                        composable<ScreenChangeCurrency> {
                            Text("Hola")
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
