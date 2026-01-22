package com.cherrish.android.presentation.onboarding.information

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState

import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cherrish.android.core.common.extension.collectLatestSideEffect
import com.cherrish.android.core.designsystem.component.button.CherrishButton
import com.cherrish.android.core.designsystem.component.textfield.CherrishTextField
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import com.cherrish.android.presentation.onboarding.information.extension.AgeSuffixTransformation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnboardingInformationRoute(
    paddingValues: PaddingValues,
    navigateToHome: () -> Unit,
    viewModel: OnboardingInformationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectLatestSideEffect { sideEffect ->
        when (sideEffect) {
            InformationSideEffect.NavigateToHome -> navigateToHome()
        }
    }

    OnboardingInformationScreen(
        paddingValues = paddingValues,
        username = uiState.username,
        onNameChange = viewModel::onNameChanged,
        age = uiState.age,
        onAgeChange = viewModel::onAgeChanged,
        onNextClick = viewModel::onNextClicked,
        enabled = uiState.buttonEnabled,
        nameErrorCase = viewModel.onNameErrorCase(uiState.username),
        ageErrorCase = viewModel.onAgeErrorCase(uiState.age)
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardingInformationScreen(
    paddingValues: PaddingValues,
    username: String,
    onNameChange: (String) -> Unit,
    age: String,
    onAgeChange: (String) -> Unit,
    onNextClick: () -> Unit,
    enabled: Boolean,
    nameErrorCase: Boolean,
    ageErrorCase: Boolean,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current

    val nameFocusRequester = remember { FocusRequester() }
    val ageFocusRequester = remember { FocusRequester() }
    var isNameFocused by remember { mutableStateOf(false) }
    var isAgeFocused by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    LaunchedEffect(isNameFocused, isAgeFocused) {
        if (isNameFocused || isAgeFocused) {
            delay(300)
            bringIntoViewRequester.bringIntoView()
        }
    }

    val listState = rememberLazyListState()
    val imeBottom = WindowInsets.ime.getBottom(density)
    val imeBottomDp = with(density) { imeBottom.toDp() }
    val bottomInset = if (imeBottomDp > 0.dp) 0.dp else paddingValues.calculateBottomPadding()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = CherrishTheme.colors.gray0)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(157.dp))
            }

            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CherrishTheme.colors.gray0)
                ) {
                    UserInfoHeader()
                }
            }

            item {
                Spacer(modifier = Modifier.height(70.dp))
            }

            item {
                Column(
                    modifier = Modifier
                        .bringIntoViewRequester(bringIntoViewRequester)
                        .windowInsetsPadding(WindowInsets.ime.only(WindowInsetsSides.Bottom))
                ) {
                    UserInfoTextField(
                        textFieldName = "이름",
                        value = username,
                        onValueChange = onNameChange,
                        placeholder = "김체리",
                        keyboardImeAction = ImeAction.Next,
                        onNextAction = { ageFocusRequester.requestFocus() },
                        keyboardType = KeyboardType.Text,
                        errorText = "이름은 최대 7자까지 입력 가능합니다.",
                        errorCase = nameErrorCase,
                        textFieldModifier = Modifier
                            .focusRequester(nameFocusRequester)
                            .pointerInput(Unit) {
                                awaitEachGesture {
                                    awaitFirstDown(pass = PointerEventPass.Initial)
                                    nameFocusRequester.requestFocus()
                                    waitForUpOrCancellation()
                                }
                            }
                            .onFocusChanged { state ->
                                isNameFocused = state.isFocused
                            }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    UserInfoTextField(
                        textFieldName = "나이",
                        value = age,
                        onValueChange = onAgeChange,
                        placeholder = "20",
                        keyboardImeAction = ImeAction.Done,
                        onDoneAction = {
                            keyboardController?.hide()
                            coroutineScope.launch {
                                delay(100)
                                focusManager.clearFocus()
                            }
                        },
                        keyboardType = KeyboardType.Number,
                        visualTransformation = if (isAgeFocused) VisualTransformation.None else AgeSuffixTransformation(
                            " 세"
                        ),
                        errorText = "입력 가능한 최대 나이 100세를 초과했습니다.",
                        errorCase = ageErrorCase,
                        textFieldModifier = Modifier
                            .focusRequester(ageFocusRequester)
                            .pointerInput(Unit) {
                                awaitEachGesture {
                                    awaitFirstDown(pass = PointerEventPass.Initial)
                                    ageFocusRequester.requestFocus()
                                    waitForUpOrCancellation()
                                }
                            }
                            .onFocusChanged { state ->
                                isAgeFocused = state.isFocused
                            }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        CherrishButton(
            text = "다음",
            onClick = onNextClick,
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .background(CherrishTheme.colors.gray0)
                .padding(horizontal = 24.dp)
                .padding(top = 30.dp, bottom = 30.dp + bottomInset)
        )
    }
}

@Composable
private fun UserInfoHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "이름과 나이를 입력해주세요.",
            style = CherrishTheme.typography.title1SB18,
            color = CherrishTheme.colors.gray1000
        )

        Text(
            text = "회복가이드를 위해 기본 정보가 필요해요!",
            style = CherrishTheme.typography.title2M16,
            color = CherrishTheme.colors.gray700
        )
    }
}

@Composable
private fun UserInfoTextField(
    textFieldName: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardImeAction: ImeAction,
    keyboardType: KeyboardType,
    errorText: String,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    onNextAction: () -> Unit = {},
    onDoneAction: () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorCase: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        Text(
            text = textFieldName,
            style = CherrishTheme.typography.body1SB14,
            color = CherrishTheme.colors.gray1000
        )

        Spacer(modifier = Modifier.height(8.dp))

        CherrishTextField(
            value = value,
            onValueChange = onValueChange,
            roundedCornerShape = RoundedCornerShape(10.dp),
            placeholder = placeholder,
            placeholderTextStyle = CherrishTheme.typography.body1R14,
            inputTextStyle = CherrishTheme.typography.body1M14,
            inputTextColor = CherrishTheme.colors.gray1000,
            paddingValues = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            keyboardImeAction = keyboardImeAction,
            onNextAction = onNextAction,
            onDoneAction = onDoneAction,
            keyboardType = keyboardType,
            visualTransformation = visualTransformation,
            modifier = textFieldModifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = errorText,
            style = CherrishTheme.typography.body1R14,
            color = if (errorCase) CherrishTheme.colors.red700 else Color.Transparent
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        OnboardingInformationScreen(
            paddingValues = PaddingValues(),
            username = "",
            onNameChange = {},
            age = "",
            onAgeChange = {},
            onNextClick = {},
            enabled = true,
            nameErrorCase = false,
            ageErrorCase = false
        )
    }
}
