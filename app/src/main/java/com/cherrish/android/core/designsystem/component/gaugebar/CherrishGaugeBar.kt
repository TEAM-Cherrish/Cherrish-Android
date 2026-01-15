package com.cherrish.android.core.designsystem.component.gaugebar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cherrish.android.core.designsystem.component.type.CherrishGaugeType
import com.cherrish.android.core.designsystem.theme.CherrishTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun CherrishGaugeBar(
    currentStep: Int,
    gauges: ImmutableList<CherrishGaugeType>,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val reversedGauges = remember(gauges) { gauges.asReversed() }

        val baseSegmentWidth = 89.dp
        val baseSegmentOffset = 67.dp
        val totalBaseWidth = baseSegmentWidth + baseSegmentOffset * (gauges.size - 1)

        val screenScale = maxWidth / totalBaseWidth
        val segmentWidth = baseSegmentWidth * screenScale
        val offsetWidth = baseSegmentOffset * screenScale

        Layout(
            content = {
                reversedGauges.forEach { gauge ->
                    key(gauge) {
                        val isActive = gauge.step <= currentStep
                        val isSelected = gauge.step == currentStep
                        val cherrishColor = CherrishTheme.colors
                        val gaugeStyle = remember(gauge, isActive, isSelected) {
                            gauge.style(
                                colors = cherrishColor,
                                isActive = isActive,
                                isSelected = isSelected
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(segmentWidth)
                                    .height(10.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .background(color = gaugeStyle.gaugeLevelColor)
                                    .border(
                                        width = 1.dp,
                                        color = gaugeStyle.gaugeLevelBorderColor,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                            )

                            Text(
                                text = gaugeStyle.gaugeLabel,
                                color = gaugeStyle.gaugeLabelColor,
                                style = CherrishTheme.typography.captionR11,
                                textAlign = TextAlign.End,
                                modifier = Modifier.width(segmentWidth)
                            )
                        }
                    }
                }
            }
        ) { measurables, constraints ->

            val segmentPlaceables = measurables.map { measurable ->
                measurable.measure(constraints.copy(minWidth = 0))
            }

            val layoutHeight = segmentPlaceables.maxOfOrNull { it.height } ?: 0

            layout(width = constraints.maxWidth, height = layoutHeight) {
                reversedGauges.forEachIndexed { i, gauge ->
                    val originalIndex = gauge.step - 1
                    val xOffsetPx = (offsetWidth.toPx() * originalIndex).toInt()

                    segmentPlaceables[i].placeRelative(x = xOffsetPx, y = 0)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CherrishTheme {
        Column {
            CherrishGaugeBar(
                currentStep = 1,
                gauges = CherrishGaugeType.entries.toImmutableList()
            )

            CherrishGaugeBar(
                currentStep = 2,
                gauges = CherrishGaugeType.entries.toImmutableList()
            )

            CherrishGaugeBar(
                currentStep = 3,
                gauges = CherrishGaugeType.entries.toImmutableList()
            )
            CherrishGaugeBar(
                currentStep = 4,
                gauges = CherrishGaugeType.entries.toImmutableList()
            )
        }
    }
}
