package com.ebl.faster.ui.pages

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setPadding
import dev1503.oreuiforandroid.R
import dev1503.oreui.StyleSheet
import dev1503.oreui.widgets.OreButton
import dev1503.oreui.widgets.OreCard

class HomePage(context: Context) : ScrollView(context) {

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
        val titleArea = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_PANEL
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }

            val bannerImage = ImageView(context).apply {
                setImageResource(dev1503.oreuiforandroid.R.drawable.bg)
                scaleType = ImageView.ScaleType.CENTER_CROP
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    (120 * resources.displayMetrics.density).toInt()
                )
            }

            val titleContainer = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(30)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val pageTitle = TextView(context).apply {
                    text = "主页"
                    setTextColor(0xFFFFFFFF.toInt())
                    textSize = 24f
                    paint.isFakeBoldText = true
                }
                addView(pageTitle)
            }

            addView(bannerImage)
            addView(titleContainer)
        }

        // --- 2. 核心功能操作区 ---
        val actionArea = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
        }

        // 启动按钮 (全宽绿色)
        val launchButton = OreButton(context).apply {
            text = "启动游戏"
            styleSheet = StyleSheet.STYLE_GREEN
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (70 * resources.displayMetrics.density).toInt()
            ).apply {
                setMargins(0, 0, 0, 20)
            }
            setCompoundDrawablesWithIntrinsicBounds(dev1503.oreuiforandroid.R.drawable.play, 0, 0, 0)
            setPadding(40, 0, 40, 0)
            setOnClickListener {
                Toast.makeText(context, "正在启动游戏...", Toast.LENGTH_SHORT).show()
            }
        }

        // 存档按钮 (全宽灰色)
        val fileButton = OreButton(context).apply {
            text = "管理存档"
            styleSheet = StyleSheet.STYLE_PANEL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (70 * resources.displayMetrics.density).toInt()
            )
            setCompoundDrawablesWithIntrinsicBounds(dev1503.oreuiforandroid.R.drawable.folder, 0, 0, 0)
            setPadding(40, 0, 40, 0)
            setOnClickListener {
                Toast.makeText(context, "打开存档目录...", Toast.LENGTH_SHORT).show()
            }
        }

        actionArea.addView(launchButton)
        actionArea.addView(fileButton)

        // --- 3. 社区新闻区域 ---
        val newsSectionArea = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 20)
            }
        }

        val newsSectionTitle = TextView(context).apply {
            text = "新闻资讯"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 20f
            paint.isFakeBoldText = true
            setPadding(0, 10, 0, 20)
        }
        newsSectionArea.addView(newsSectionTitle)

        // 辅助方法：创建一条新闻卡片
        fun createNewsItem(title: String, previewText: String, date: String): OreCard {
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
                
                setOnClickListener {
                    Toast.makeText(context, "正在打开新闻：$title", Toast.LENGTH_SHORT).show()
                }

                val titleView = TextView(context).apply {
                    text = title
                    setTextColor(0xFFFFFFFF.toInt())
                    textSize = 16f
                    paint.isFakeBoldText = true
                    setPadding(0, 0, 0, 10)
                }
                
                val previewView = TextView(context).apply {
                    text = previewText
                    setTextColor(0xFFB1B2B5.toInt())
                    textSize = 14f
                    setLineSpacing(5f, 1f)
                    maxLines = 2
                }

                val dateView = TextView(context).apply {
                    text = date
                    setTextColor(0xFF8C8D90.toInt())
                    textSize = 12f
                    setPadding(0, 15, 0, 0)
                }

                addView(titleView)
                addView(previewView)
                addView(dateView)
            }
        }

        newsSectionArea.addView(createNewsItem("Minecraft 1.21 大更新，快来看看！", "准备好探索无尽的世界了吗？全新版本现已发布。", "今天 10:00"))
        newsSectionArea.addView(createNewsItem("1.21 优化更新及修复", "修复了进入地图时的闪退问题，提高了主页面的加载速度。", "昨天 18:30"))
        newsSectionArea.addView(createNewsItem("皮肤与材质包管理上线", "现在你可以在模组区域内一键更换你的角色皮肤。", "2 天前"))

        contentArea.addView(titleArea)
        contentArea.addView(actionArea)
        contentArea.addView(newsSectionArea)

        addView(contentArea)
    }
}

