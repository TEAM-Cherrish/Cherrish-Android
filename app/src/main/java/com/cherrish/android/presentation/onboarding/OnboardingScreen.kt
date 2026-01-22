package com.cherrish.android.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.cherrish.android.R
import com.cherrish.android.core.common.extension.collectLatestSideEffect
import com.cherrish.android.core.common.extension.noRippleClickable
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.onboarding.model.OnboardingCherryType
import kotlin.math.ceil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.android.awaitFrame

@Composable
fun OnboardingRoute(
    paddingValues: PaddingValues,
    navigateToOnboardingInformation: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    viewModel.sideEffect.collectLatestSideEffect { sideEffect ->
        when (sideEffect) {
            OnboardingSideEffect.NavigateToOnboardingInformation -> {
                navigateToOnboardingInformation()
            }
        }
    }
    OnboardingScreen(
        paddingValues = paddingValues,
        onCancelClick = viewModel::onClick,
        onNextClick = viewModel::onClick
    )
}

@Composable
private fun OnboardingScreen(
    paddingValues: PaddingValues,
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val density = LocalDensity.current

    val gradientEndY = with(density) { 310.dp.toPx() }
    val gradationColors = listOf(CherrishTheme.colors.graStart, CherrishTheme.colors.graEnd)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = gradationColors,
                    startY = 0f,
                    endY = gradientEndY
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
                modifier = Modifier.padding(bottom = 40.dp)
            )

            val showButton = pagerState.currentPage == 1

            CherrishButton(
                text = "다음",
                onClick = onNextClick,
                enabled = showButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .navigationBarsPadding()
                    .graphicsLayer {
                        alpha = if (showButton) 1f else 0f
                    }
            )
        }
    }
}

@Composable
private fun OnboardingSection(
    modifier: Modifier = Modifier
) {
    val colors = CherrishTheme.colors
    val density = LocalDensity.current

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
                underlineSpacer,
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
                        painter = painterResource(id = R.drawable.img_bubble_speech),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 118.dp, height = 62.dp)
                            .offset(x = (-9).dp, y = 10.dp)
                    )
                    Text(
                        text = "*다운타임",
                        style = CherrishTheme.typography.title2M16.copy(
                            fontSize = with(density) {
                                CherrishTheme.typography.title2M16.fontSize / fontScale
                            }
                        ),
                        color = colors.gray700,
                        modifier = Modifier.offset(x = (-9).dp, y = 10.dp)
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

            UnderlineUntilWord(
                text = "시술 후 불편감이 남을 수 있는 기간을 계산해",
                underlineUntil = "기간",
                style = CherrishTheme.typography.title1SB18,
                textColor = colors.gray1000,
                underlineColor = colors.red700,
                underlineThickness = 1.4.dp,
                underlineInset = 4.dp,
                underlineYOffset = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(titleText) {
                        top.linkTo(overlayRow.bottom, margin = 12.dp)
                        start.linkTo(parent.start)
                    }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .constrainAs(underlineSpacer) {
                        top.linkTo(titleText.bottom)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = "일정 한 눈에 정리해드려요",
                style = CherrishTheme.typography.title1SB18,
                color = colors.gray1000,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(subTitleText) {
                        top.linkTo(underlineSpacer.bottom, margin = 3.dp)
                        start.linkTo(parent.start)
                    }
            )
        }
    }
}

@Composable
private fun UnderlineUntilWord(
    text: String,
    underlineUntil: String,
    style: TextStyle,
    textColor: Color,
    underlineColor: Color,
    underlineThickness: Dp,
    underlineInset: Dp,
    underlineYOffset: Dp,
    modifier: Modifier = Modifier
) {
    var layoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    val density = LocalDensity.current
    val thicknessPx = with(density) { underlineThickness.toPx() }
    val insetPx = with(density) { underlineInset.toPx() }
    val yOffsetPx = with(density) { underlineYOffset.toPx() }

    val endExclusive = remember(text, underlineUntil) {
        val idx = text.indexOf(underlineUntil)
        if (idx >= 0) idx + underlineUntil.length else -1
    }

    Text(
        text = text,
        style = style,
        color = textColor,
        onTextLayout = { layoutResult = it },
        modifier = modifier.drawBehind {
            val layout = layoutResult ?: return@drawBehind
            if (endExclusive <= 0) return@drawBehind

            val line = layout.getLineForOffset(endExclusive - 1)

            val end = layout.getHorizontalPosition(endExclusive, usePrimaryDirection = true)

            val y = layout.getLineBottom(line) + yOffsetPx
            val gap = with(density) { 3.dp.toPx() }

            drawLine(
                color = underlineColor,
                start = Offset(x = insetPx, y = y),
                end = Offset(x = end, y = y),
                strokeWidth = thicknessPx,
                cap = StrokeCap.Round
            )
            drawLine(
                color = underlineColor,
                start = Offset(x = 0f, y = y + gap),
                end = Offset(x = (end + insetPx), y = y + gap),
                strokeWidth = thicknessPx,
                cap = StrokeCap.Round
            )
        }
    )
}

@Composable
private fun Onboarding2Section(
    modifier: Modifier = Modifier
) {
    val images = remember {
        persistentListOf(
            OnboardingCherryType.LV0,
            OnboardingCherryType.LV1,
            OnboardingCherryType.LV2,
            OnboardingCherryType.LV3,
            OnboardingCherryType.LV4

        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(54.dp))

        Text(
            text = "원하는 추구미에 도달할 수 있도록\nTO-DO 루틴을 제시해줘요",
            style = CherrishTheme.typography.title1SB18,
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

        Spacer(modifier = Modifier.height(13.dp))

        MovingCherry(images = images)

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "TO-DO 미션을 채울때마다 체리가 변화해요!",
            style = CherrishTheme.typography.title2R16,
            color = CherrishTheme.colors.gray600
        )
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
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_indicator),
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

@Composable
private fun MovingCherry(
    images: ImmutableList<OnboardingCherryType>,
    modifier: Modifier = Modifier
) {
    val imageSize = 90.dp
    val speedDpPerSec = 24.dp
    val density = LocalDensity.current

    val imageSizePx = with(density) { imageSize.toPx() }
    val speedPxPerSec = with(density) { speedDpPerSec.toPx() }

    var viewWidthPx by remember { mutableFloatStateOf(0f) }
    val cherryWidthPx = imageSizePx * images.size

    val repeatCount by remember(viewWidthPx, cherryWidthPx) {
        derivedStateOf {
            if (viewWidthPx == 0f || cherryWidthPx == 0f) {
                2
            } else {
                maxOf(2, ceil((viewWidthPx + cherryWidthPx) / cherryWidthPx).toInt() + 1)
            }
        }
    }

    var offsetX by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(cherryWidthPx, speedPxPerSec) {
        if (cherryWidthPx == 0f) return@LaunchedEffect
        var lastFrameTime = 0L

        while (true) {
            val frameTime = awaitFrame()
            if (lastFrameTime != 0L) {
                val diffFrameTime = (frameTime - lastFrameTime) / 1_000_000_000f
                offsetX -= speedPxPerSec * diffFrameTime
                if (offsetX <= -cherryWidthPx) offsetX += cherryWidthPx
            }
            lastFrameTime = frameTime
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(imageSize)
            .onSizeChanged { viewWidthPx = it.width.toFloat() }
            .clipToBounds()
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth(unbounded = true)
                .graphicsLayer { translationX = offsetX }
        ) {
            repeat(repeatCount) {
                images.forEach { type ->
                    Image(
                        imageVector = ImageVector.vectorResource(id = type.image),
                        contentDescription = null,
                        modifier = Modifier.size(imageSize),
                        contentScale = ContentScale.Fit
                    )
                }
            }
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
            OnboardingScreen(
                paddingValues = PaddingValues(),
                onCancelClick = {},
                onNextClick = {}
            )
        }
    }
}
