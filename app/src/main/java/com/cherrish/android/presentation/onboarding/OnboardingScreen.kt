package com.cherrish.android.presentation.onboarding

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = viewModel()
) {
    Onboarding(
        onCancelClick = viewModel::onCancelClick,
        onNextClick = viewModel::onNextClick
    )
}

@Composable
private fun Onboarding(
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val colors = CherrishTheme.colors
    val gradientEndY = with(LocalDensity.current) { 310.dp.toPx() }

    var buttonHeightPx by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    val buttonSlotMinHeight = with(density) { buttonHeightPx.toDp() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = persistentListOf(colors.graStart, colors.graEnd),
                    startY = 0f,
                    endY = gradientEndY
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(54.dp))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = CherrishTheme.colors.gray600,
                modifier = Modifier
                    .align(Alignment.End)
                    .noRippleClickable(onClick = onCancelClick)
                    .padding(end = 24.dp)
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 23.dp + buttonSlotMinHeight + 22.dp)
            ) { page ->
                when (page) {
                    0 -> OnboardingSection()
                    1 -> Onboarding2Section()
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PagerIndicator(
                pagerState = pagerState,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .heightIn(min = buttonSlotMinHeight),
                contentAlignment = Alignment.Center
            ) {
                if (pagerState.currentPage == 1) {
                    CherrishButton(
                        text = "다음",
                        onClick = onNextClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onSizeChanged { buttonHeightPx = maxOf(buttonHeightPx, it.height) }
                    )
                }
            }
        }
    }
}

@Composable
private fun OnboardingSection(
    modifier: Modifier = Modifier
) {
    val colors = CherrishTheme.colors

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp)
        ) {
            val (
                calendarImage,
                overlayRow,
                titleText,
                line1,
                line2,
                subTitleText
            ) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.img_illustration_onboarding_cal),
                contentDescription = null,
                modifier = Modifier.constrainAs(calendarImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(overlayRow) {
                        top.linkTo(calendarImage.bottom, margin = (-45).dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.align(Alignment.Bottom),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_bubble_speech),
                        contentDescription = null
                    )
                    Text(
                        text = "*다운타임",
                        style = CherrishTheme.typography.title2M16,
                        color = colors.gray700
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.img_onboarding_cherry),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 152.dp, height = 204.dp)
                        .padding(bottom = 5.dp)
                )
            }

            Text(
                text = "시술 후 불편감이 남을 수 있는 기간을 계산해",
                style = CherrishTheme.typography.title1SB18,
                color = colors.gray1000,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(titleText) {
                        top.linkTo(overlayRow.bottom, margin = 12.dp)
                        start.linkTo(parent.start)
                    }
            )

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 72.dp)
                    .constrainAs(line1) {
                        top.linkTo(titleText.bottom, margin = 0.dp)
                    }
            ) {
                val strokePx = 1.4.dp.toPx()
                drawLine(
                    color = colors.red700,
                    start = Offset(0f, strokePx / 2),
                    end = Offset(size.width, strokePx / 2),
                    strokeWidth = strokePx,
                    cap = StrokeCap.Round
                )
            }

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 68.dp)
                    .constrainAs(line2) {
                        top.linkTo(line1.bottom, margin = 3.dp)
                    }
            ) {
                val strokePx = 1.4.dp.toPx()
                drawLine(
                    color = colors.red700,
                    start = Offset(0f, strokePx / 2),
                    end = Offset(size.width, strokePx / 2),
                    strokeWidth = strokePx,
                    cap = StrokeCap.Round
                )
            }

            Text(
                text = "일정 한 눈에 정리해드려요",
                style = CherrishTheme.typography.title1SB18,
                color = colors.gray1000,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(subTitleText) {
                        top.linkTo(line2.bottom, margin = 3.dp)
                        start.linkTo(parent.start)
                    }
            )
        }
    }
}

@Composable
private fun Onboarding2Section(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(54.dp))

        Text(
            text = "원하는 추구미에 도달할 수 있도록\nTO-DO 루틴을 제시해줘요",
            style = CherrishTheme.typography.title1M18,
            color = CherrishTheme.colors.gray1000,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 34.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.img_onboarding_calendar),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 34.dp)
        )

        Spacer(modifier = Modifier.weight(13f))

        // TODO: 애니 이상항
        val imageCount = 5
        val imageSize = 90.dp
        val imageSpacing = 16.dp

        val density = LocalDensity.current
        val singleSetWidthPx = with(density) {
            (imageSize + imageSpacing).toPx() * imageCount
        }

        val infinite = rememberInfiniteTransition(label = "cherry_marquee")
        val translateX by infinite.animateFloat(
            initialValue = 0f,
            targetValue = -singleSetWidthPx,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 6000,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "translateX"
        )

        val cherries = OnboardingCherryType.entries

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds()
        ) {
            Row(
                modifier = Modifier.graphicsLayer {
                    translationX = translateX
                }
            ) {
                repeat(3) {
                    cherries.forEach { type ->
                        Image(
                            imageVector = ImageVector.vectorResource(id = type.image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(90.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(18f))

        Text(
            text = "TO-DO 미션을 채울때마다 체리가 변화해요!",
            style = CherrishTheme.typography.title2R16,
            color = CherrishTheme.colors.gray600
        )

        Spacer(modifier = Modifier.weight(62f))
    }
}

@Composable
private fun PagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pagerState.pageCount) { index ->
            val isSelected = pagerState.currentPage == index

            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (isSelected) {
                        R.drawable.ic_indicator
                    } else {
                        R.drawable.ic_unindicator
                    }
                ),
                contentDescription = null,
                tint = if (isSelected) {
                    CherrishTheme.colors.gray800
                } else {
                    CherrishTheme.colors.gray500
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Onboarding(
                onCancelClick = {},
                onNextClick = {}
            )
        }
    }
}
