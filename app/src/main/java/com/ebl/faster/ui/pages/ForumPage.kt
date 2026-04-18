package com.ebl.faster.ui.pages

import android.content.Context
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setPadding
import dev1503.oreui.StyleSheet
import dev1503.oreui.widgets.OreCard
import dev1503.oreui.widgets.OreButton

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

        // 顶部标题栏
        val titleArea = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_PANEL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
            setPadding(30)
            val pageTitle = TextView(context).apply {
                text = "市场"
                setTextColor(0xFFFFFFFF.toInt())
                textSize = 24f
                paint.isFakeBoldText = true
            }
            addView(pageTitle)
        }

        contentArea.addView(titleArea)

        // 推荐模组区域
        val recommendedSection = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
        }

        val recommendedTitle = TextView(context).apply {
            text = "推荐内容"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 20f
            paint.isFakeBoldText = true
            setPadding(0, 0, 0, 20)
        }
        recommendedSection.addView(recommendedTitle)

        fun createModCard(title: String, desc: String, tag: String): OreCard {
            return OreCard(context).apply {
                styleSheet = StyleSheet.STYLE_PANEL
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 20)
                }
                setPadding(30)
                
                val modTitle = TextView(context).apply {
                    text = title
                    setTextColor(0xFFFFFFFF.toInt())
                    textSize = 18f
                    paint.isFakeBoldText = true
                    setPadding(0, 0, 0, 10)
                }
                
                val modTag = TextView(context).apply {
                    text = tag
                    setTextColor(0xFF4CB04E.toInt())
                    textSize = 12f
                    setPadding(0, 0, 0, 10)
                }

                val modDesc = TextView(context).apply {
                    text = desc
                    setTextColor(0xFFB1B2B5.toInt())
                    textSize = 14f
                    setPadding(0, 0, 0, 20)
                }
                
                val downloadBtn = OreButton(context).apply {
                    text = "下载"
                    styleSheet = StyleSheet.STYLE_GREEN
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    setPadding(40, 10, 40, 10)
                    setOnClickListener {
                        Toast.makeText(context, "正在下载 $title...", Toast.LENGTH_SHORT).show()
                    }
                }

                addView(modTitle)
                addView(modTag)
                addView(modDesc)
                addView(downloadBtn)
            }
        }

        recommendedSection.addView(createModCard("OptiFine 优化模组", "大幅提高游戏帧率，支持高清纹理和光影效果。", "优化"))
        recommendedSection.addView(createModCard("JEI 物品管理器", "查看所有物品的合成配方及用途，生存必备。", "工具"))
        recommendedSection.addView(createModCard("光影包 - BSL Shaders", "柔和且高度可定制的光影包，带来极致视觉体验。", "光影"))

        contentArea.addView(recommendedSection)
        addView(contentArea)
    }
}
