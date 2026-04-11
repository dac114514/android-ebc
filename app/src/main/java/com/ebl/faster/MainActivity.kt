package com.ebl.faster

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import dev1503.oreuiforandroid.R
import dev1503.oreui.StyleSheet
import dev1503.oreui.widgets.OreButton
import dev1503.oreui.widgets.OreCard
import dev1503.oreui.widgets.OrePanel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. 根容器：纯黑背景，可滚动
        val scrollView = ScrollView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(0xFF000000.toInt())
        }

        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // --- 2. 标题栏 ---
        val titleBar = OrePanel(this).apply {
            styleSheet = StyleSheet.STYLE_PANEL
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(20, 20, 20, 20)
            }
        }

        val titleText = TextView(this).apply {
            text = "EBC"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 20f
            gravity = Gravity.CENTER
            setPadding(0, 10, 0, 10)
        }
        titleBar.addView(titleText)

        // --- 3. Banner 海报图 ---
        val bannerImage = ImageView(this).apply {
            // 使用项目自带的 bg.jpg 作为示例海报
            setImageResource(R.drawable.bg) 
            scaleType = ImageView.ScaleType.CENTER_CROP
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (200 * resources.displayMetrics.density).toInt() // 约 200dp 高度
            ).apply {
                setMargins(20, 0, 20, 20)
            }
        }

        // --- 4. 大尺寸绿色启动按钮 ---
        val launchButton = OreButton(this).apply {
            text = "启动"
            styleSheet = StyleSheet.STYLE_GREEN
            
            // 确保高度至少 67dp
            val minHeightPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 67f, resources.displayMetrics
            ).toInt()
            
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                minHeightPx
            ).apply {
                setMargins(40, 20, 40, 40)
            }
        }

        // --- 5. 设置与资讯区域 ---
        val contentArea = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(20, 0, 20, 50)
            }
        }

        // 设置区域
        val settingsCard = OreCard(this).apply {
            styleSheet = StyleSheet.STYLE_CARD_DARK
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 10, 0, 10)
            }
        }
        
        val settingsText = TextView(this).apply {
            text = "设置"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 16f
            setPadding(20, 20, 20, 20)
        }
        settingsCard.addView(settingsText)

        // 社区活动区域
        val communityCard = OreCard(this).apply {
            styleSheet = StyleSheet.STYLE_CARD_DARK
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 10, 0, 10)
            }
        }
        
        val communityText = TextView(this).apply {
            text = "社区活动\n- New Map Contest\n- Weekly Build Challenge"
            setTextColor(0xFFB1B2B5.toInt())
            textSize = 14f
            setPadding(20, 20, 20, 20)
        }
        communityCard.addView(communityText)

        // 新闻资讯区域
        val newsCard = OreCard(this).apply {
            styleSheet = StyleSheet.STYLE_CARD_DARK
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 10, 0, 10)
            }
        }
        
        val newsText = TextView(this).apply {
            text = "新闻资讯\n- Version 1.21.0 is now available!"
            setTextColor(0xFFB1B2B5.toInt())
            textSize = 14f
            setPadding(20, 20, 20, 20)
        }
        newsCard.addView(newsText)

        contentArea.addView(settingsCard)
        contentArea.addView(communityCard)
        contentArea.addView(newsCard)

        // 组装所有组件
        rootLayout.addView(titleBar)
        rootLayout.addView(bannerImage)
        rootLayout.addView(launchButton)
        rootLayout.addView(contentArea)

        scrollView.addView(rootLayout)
        setContentView(scrollView)
    }
}
