package com.example.worldskillsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.worldskillsapp.presentation.state.SignInState
import com.example.worldskillsapp.presentation.view.ConfirmEmailCode
import com.example.worldskillsapp.presentation.view.EnterPassword
import com.example.worldskillsapp.presentation.view.InputUserData
import com.example.worldskillsapp.presentation.view.NavGraphs
import com.example.worldskillsapp.presentation.view.OnBoardScreen
import com.example.worldskillsapp.presentation.view.SignIn
import com.example.worldskillsapp.presentation.view.SplashScreen
import com.example.worldskillsapp.presentation.view.destinations.ConfirmEmailCodeDestination
import com.example.worldskillsapp.presentation.view.destinations.EnterPasswordDestination
import com.example.worldskillsapp.presentation.view.destinations.InputUserDataDestination
import com.example.worldskillsapp.presentation.view.destinations.OnBoardScreenDestination
import com.example.worldskillsapp.presentation.view.destinations.SignInDestination
import com.example.worldskillsapp.presentation.view.destinations.SplashScreenDestination
import com.example.worldskillsapp.presentation.viewModels.InputUserDataViewModel
import com.example.worldskillsapp.presentation.viewModels.SignInViewModel
import com.example.worldskillsapp.presentation.viewModels.SplashScreenViewModel
import com.example.worldskillsapp.presentation.viewModels.StartScreenViewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo
import org.koin.androidx.compose.getViewModel

@Composable
fun WorldSkillsMedicAppNavigation() {
    val navController = rememberNavController()
    DestinationsNavHost(navGraph = NavGraphs.root, navController = navController) {
        composable(SplashScreenDestination) {
            val viewModel: SplashScreenViewModel = getViewModel()
            SplashScreen(navigateOnLoadSuccess = {
                viewModel.checkOnBoardInfo(isTrue = {
                    navController.navigate(SignInDestination) {
                        popUpTo(SplashScreenDestination) {
                            inclusive = true
                        }
                    }
                }, isFalse = {
                    navController.navigate(OnBoardScreenDestination) {
                        popUpTo(SplashScreenDestination) {
                            inclusive = true
                        }
                    }
                })
            })
        }
        composable(OnBoardScreenDestination) {
            OnBoardScreen(confirmOnClick = {
                navController.navigate(SignInDestination) {
                    popUpTo(OnBoardScreenDestination) {
                        inclusive = true
                    }
                }
            })
        }
        composable(SignInDestination) {
            val viewModel: SignInViewModel = getViewModel()
            val state by viewModel.viewState.collectAsStateWithLifecycle()

            SignIn(state = state, onEvent = viewModel::onEvent, onClickConfirm = {
                navController.navigate(ConfirmEmailCodeDestination)
            })
        }
        composable(ConfirmEmailCodeDestination) {
            val viewModel: SignInViewModel = getViewModel()
            val state by viewModel.viewState.collectAsStateWithLifecycle()
            ConfirmEmailCode(popBackStack = { navController.popBackStack() },
                state = state,
                onEvent = viewModel::onEvent,
                onSuccessCallback = {
                    navController.navigate(EnterPasswordDestination) {
                        popUpTo(SignInDestination) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(EnterPasswordDestination) {
            val viewModel: InputUserDataViewModel = getViewModel()
            val state by viewModel.viewState.collectAsStateWithLifecycle()
            EnterPassword(state = state, onEvent = viewModel::onEvent, skipOnClick = {

            }, onSuccessCallback = {
                navController.navigate(InputUserDataDestination) {
                    popUpTo(EnterPasswordDestination){
                        inclusive = true
                    }
                }
            })
        }
        composable(InputUserDataDestination){
            val viewModel: InputUserDataViewModel = getViewModel()
            val state by viewModel.viewState.collectAsStateWithLifecycle()
            InputUserData(state = state, onEvent = viewModel::onEvent, skipOnClick = {

            })
        }
    }
}