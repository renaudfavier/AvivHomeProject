package com.example.avivhomeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.avivhomeproject.core.ui.theme.AvivTheme
import com.example.avivhomeproject.property.presentation.detail.DetailViewModel
import com.example.avivhomeproject.property.presentation.detail.PropertyDetailScreen
import com.example.avivhomeproject.property.presentation.list.ListViewModel
import com.example.avivhomeproject.property.presentation.list.PropertyListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AvivTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = PropertyListRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<PropertyListRoute> {
                            val listViewModel = hiltViewModel<ListViewModel>()
                            val uiModel by listViewModel.uiState.collectAsStateWithLifecycle()

                            PropertyListScreen(
                                uiModel = uiModel,
                                onPropertyClick = { id ->
                                    navController.navigate(PropertyDetailRoute(id))
                                }
                            )
                        }
                        composable<PropertyDetailRoute> {
                            val detailViewModel = hiltViewModel<DetailViewModel>()
                            val uiModel by detailViewModel.uiState.collectAsStateWithLifecycle()

                            PropertyDetailScreen(
                                uiModel = uiModel,
                                onBackButtonPressed = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
