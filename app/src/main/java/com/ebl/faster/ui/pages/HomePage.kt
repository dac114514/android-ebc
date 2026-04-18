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

        // --- Banner 海报图 ---
        val bannerCard = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_CARD_DARK
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
            setPadding(8)
        }

        val bannerImage = ImageView(context).apply {
            setImageResource(R.drawable.bg) 
            scaleType = ImageView.ScaleType.CENTER_CROP
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (180 * resources.displayMetrics.density).toInt()
            )
        }
        bannerCard.addView(bannerImage)

        // --- 启动与版本信息区域 ---
        val actionArea = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
        }

        val versionText = TextView(context).apply {
            text = "版本\n1.21.0 正式版"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 15f
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
        }

        val launchButton = OreButton(context).apply {
            text = "启动游戏"
            styleSheet = StyleSheet.STYLE_GREEN
            
            val minHeightPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 60f, resources.displayMetrics
            ).toInt()
            
            layoutParams = LinearLayout.LayoutParams(
                0,
                minHeightPx,
                2f
            )
        }
        
        actionArea.addView(versionText)
        actionArea.addView(launchButton)

        // --- 社区新闻区域 (新闻列表) ---
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
            text = "社区与新闻"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 18f
            paint.isFakeBoldText = true
            setPadding(10, 10, 10, 20)
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
                
                // 点击事件：可进入阅读
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

        // 添加三条测试新闻
        newsSectionArea.addView(createNewsItem("欢迎来到 EBLauncher 测试版！", "这是一款采用纯 Kotlin 构建的全新启动器，极速、流畅且极具可定制性。", "今天 10:00"))
        newsSectionArea.addView(createNewsItem("1.21 优化更新及修复", "修复了进入地图时的闪退问题，提高了主页面的加载速度，加入了全新的多页面 UI。", "昨天 18:30"))
        newsSectionArea.addView(createNewsItem("皮肤与材质包管理上线", "现在你可以在模组区域内一键更换你的角色皮肤，并导入外部材质包文件。", "2 天前"))

        contentArea.addView(bannerCard)
        contentArea.addView(actionArea)
        contentArea.addView(newsSectionArea)

        addView(contentArea)
    }
}

