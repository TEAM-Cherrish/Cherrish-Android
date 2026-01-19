package com.cherrish.android.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.collectLatestSideEffect
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    navigateToOnboarding: () -> Unit,
    navigateToHome: () -> Unit,
    paddingValues: PaddingValues,
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.isAutoLoginCheck()
    }

    viewModel.sideEffect.collectLatestSideEffect { sideEffect ->
        delay(3000)

        when (sideEffect) {
            SplashSideEffect.NavigateToOnboarding -> {
                navigateToOnboarding()
            }
            SplashSideEffect.NavigateToHome -> {
                navigateToHome()
            }
        }
    }

    SplashScreen(
        paddingValues = paddingValues
    )
}

@Composable
private fun SplashScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val gradationColors = listOf(CherrishTheme.colors.gradation, CherrishTheme.colors.gradation2)

    Column(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                val brush = Brush.linearGradient(
                    colors = gradationColors,
                    start = Offset(size.width / 2f, size.height / 2f)
                )
                drawRect(brush)
            }
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(283f))

        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = null,
            modifier = Modifier.size(width = 114.dp, height = 100.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_app_logo_title),
            contentDescription = null
        )

        Spacer(modifier = Modifier.weight(298f))
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        SplashScreen(
            paddingValues = PaddingValues(top = 10.dp)
        )
    }
}
