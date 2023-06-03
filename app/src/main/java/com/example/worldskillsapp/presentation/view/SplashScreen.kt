package com.example.worldskillsapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.ImagePainter
import com.example.worldskillsapp.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo
import kotlinx.coroutines.delay

@RootNavGraph(true)
@Destination
@Composable
fun SplashScreen(
    navigateOnLoadSuccess: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(2500)
        navigateOnLoadSuccess()
    }
    SplashScreenContent()
}


@Composable
fun SplashScreenContent() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.splash_screen_image),
        contentDescription = "splash screen image",
        contentScale = ContentScale.Crop
    )
}


@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navigateOnLoadSuccess = {

    })
}