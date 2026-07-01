package com.android.purebilibili.feature.list

import com.android.purebilibili.core.store.HomeSettings
import com.android.purebilibili.core.theme.AndroidNativeVariant
import com.android.purebilibili.core.theme.UiPreset
import com.android.purebilibili.core.ui.resolveCompactCapsuleChromeSpec
import com.android.purebilibili.feature.home.components.BOTTOM_BAR_LIQUID_SEGMENTED_CONTROL_HEIGHT_DP
import com.android.purebilibili.feature.home.components.BOTTOM_BAR_LIQUID_SEGMENTED_CONTROL_INDICATOR_HEIGHT_DP

internal data class HistoryFilterTabChromeSpec(
    val useLiquidDock: Boolean,
    val heightDp: Int,
    val indicatorHeightDp: Int,
    val itemWidthDp: Int,
    val horizontalPaddingDp: Int,
    val labelFontSizeSp: Int,
    val dragSelectionEnabled: Boolean
)

internal fun shouldUseHistoryFilterLiquidDock(
    androidNativeLiquidGlassEnabled: Boolean
): Boolean = androidNativeLiquidGlassEnabled

internal fun resolveHistoryFilterTabItemWidthDp(filterCount: Int): Int {
    return when {
        filterCount >= 5 -> 56
        filterCount >= 4 -> 60
        else -> 66
    }
}

internal fun resolveHistoryFilterTabChromeSpec(
    homeSettings: HomeSettings,
    uiPreset: UiPreset,
    androidNativeVariant: AndroidNativeVariant = AndroidNativeVariant.MATERIAL3,
    filterCount: Int = HistoryContentFilter.entries.size
): HistoryFilterTabChromeSpec {
    val useLiquidDock = shouldUseHistoryFilterLiquidDock(
        androidNativeLiquidGlassEnabled = homeSettings.androidNativeLiquidGlassEnabled
    )
    val compactChrome = resolveCompactCapsuleChromeSpec(uiPreset, androidNativeVariant)
    return if (useLiquidDock) {
        HistoryFilterTabChromeSpec(
            useLiquidDock = true,
            heightDp = BOTTOM_BAR_LIQUID_SEGMENTED_CONTROL_HEIGHT_DP,
            indicatorHeightDp = BOTTOM_BAR_LIQUID_SEGMENTED_CONTROL_INDICATOR_HEIGHT_DP,
            itemWidthDp = resolveHistoryFilterTabItemWidthDp(filterCount),
            horizontalPaddingDp = 16,
            labelFontSizeSp = 13,
            dragSelectionEnabled = true
        )
    } else {
        HistoryFilterTabChromeSpec(
            useLiquidDock = false,
            heightDp = compactChrome.chipHeightDp,
            indicatorHeightDp = 30,
            itemWidthDp = resolveHistoryFilterTabItemWidthDp(filterCount),
            horizontalPaddingDp = 12,
            labelFontSizeSp = 14,
            dragSelectionEnabled = false
        )
    }
}