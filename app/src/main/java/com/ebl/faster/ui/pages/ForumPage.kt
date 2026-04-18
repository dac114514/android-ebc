package com.ebl.faster.ui.pages

import android.content.Context
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.setPadding

class ForumPage(context: Context) : ScrollView(context) {

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        isFillViewport = true

        val contentArea = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(40, 40, 40, 40)
            }
        }

        // --- 1. 顶部标题栏 ---
        val titleArea = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
        }
        
        val pageTitle = TextView(context).apply {
            text = "论坛"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 28f
            paint.isFakeBoldText = true
            setPadding(0, 0, 0, 10)
        }
        
        val underline = android.view.View(context).apply {
            setBackgroundColor(0xFF4CB04E.toInt())
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (4 * resources.displayMetrics.density).toInt()
            )
        }
        
        titleArea.addView(pageTitle)
        titleArea.addView(underline)

        // --- 2. 占位 WebView ---
        val placeholder = TextView(context).apply {
            text = "WebView 占位区域\n\n(原 LuaWebView 区域)"
            setTextColor(0xFFAAAAAA.toInt())
            textSize = 18f
            gravity = android.view.Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                500 // 占位高度
            ).apply {
                setMargins(0, 40, 0, 0)
            }
        }

        contentArea.addView(titleArea)
        contentArea.addView(placeholder)

        addView(contentArea)
    }
}
