package com.ebl.faster.ui.pages

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.setPadding
import dev1503.oreui.StyleSheet
import dev1503.oreui.widgets.OreCard
import dev1503.oreui.widgets.OreSwitch
import dev1503.oreui.widgets.OreSlider

class SettingsPage(context: Context) : ScrollView(context) {

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        isFillViewport = true

        val contentArea = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(40, 40, 40, 40)
            }
        }

        val pageTitle = TextView(context).apply {
            text = "游戏设置"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 22f
            paint.isFakeBoldText = true
            setPadding(10, 0, 10, 30)
        }
        contentArea.addView(pageTitle)

        // 基础设置卡片
        val basicSettingsCard = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_CARD_DARK
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
            setPadding(30)
        }

        val titleBasic = TextView(context).apply {
            text = "通用设置"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 16f
            paint.isFakeBoldText = true
            setPadding(0, 0, 0, 20)
        }
        basicSettingsCard.addView(titleBasic)

        // 全屏模式 Switch
        val fullscreenRow = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 10, 0, 20)
            }
        }
        val fullscreenText = TextView(context).apply {
            text = "全屏模式"
            setTextColor(0xFFB1B2B5.toInt())
            textSize = 15f
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }
        val fullscreenSwitch = OreSwitch(context).apply {
            isChecked = true
        }
        fullscreenRow.addView(fullscreenText)
        fullscreenRow.addView(fullscreenSwitch)
        
        // 渲染距离 Slider
        val renderDistanceText = TextView(context).apply {
            text = "渲染距离 (区块)"
            setTextColor(0xFFB1B2B5.toInt())
            textSize = 15f
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 20, 0, 10)
            }
        }
        val renderDistanceSlider = OreSlider(context).apply {
            max = 32
            progress = 12
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        
        basicSettingsCard.addView(fullscreenRow)
        basicSettingsCard.addView(renderDistanceText)
        basicSettingsCard.addView(renderDistanceSlider)

        // 内存分配 Slider
        val memorySettingsCard = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_CARD_DARK
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
            setPadding(30)
        }

        val titleMemory = TextView(context).apply {
            text = "内存分配 (MB)"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 16f
            paint.isFakeBoldText = true
            setPadding(0, 0, 0, 20)
        }
        memorySettingsCard.addView(titleMemory)

        val memorySlider = OreSlider(context).apply {
            max = 8192
            progress = 2048
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 10, 0, 10)
            }
        }
        val memoryHint = TextView(context).apply {
            text = "建议分配 2048 MB 到 4096 MB"
            setTextColor(0xFFB1B2B5.toInt())
            textSize = 12f
        }
        memorySettingsCard.addView(memorySlider)
        memorySettingsCard.addView(memoryHint)

        contentArea.addView(basicSettingsCard)
        contentArea.addView(memorySettingsCard)

        addView(contentArea)
    }
}
