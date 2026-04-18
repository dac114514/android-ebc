package com.ebl.faster

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import dev1503.oreui.StyleSheet
import dev1503.oreui.widgets.OreButton
import dev1503.oreui.widgets.OrePanel
import dev1503.oreui.widgets.OreTabs
import com.ebl.faster.ui.pages.HomePage
import com.ebl.faster.ui.pages.SettingsPage
import com.ebl.faster.ui.pages.AccountPage

class MainActivity : AppCompatActivity() {
    
    private lateinit var contentFrame: FrameLayout
    
    // 缓存页面实例，避免频繁重建
    private val homePage by lazy { HomePage(this) }
    private val settingsPage by lazy { SettingsPage(this) }
    private val accountPage by lazy { AccountPage(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 根容器：深色背景，铺满屏幕，禁止自身滚动，因为子页面各自有 ScrollView
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(0xFF1E1E1F.toInt())
        }

        // --- 1. 标题栏 (灰偏白风格) ---
        val titleBar = OrePanel(this).apply {
            styleSheet = StyleSheet.STYLE_WHITE
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val titleText = TextView(this).apply {
            text = "EBLauncher"
            setTextColor(0xFF000000.toInt())
            textSize = 20f
            paint.isFakeBoldText = true
            gravity = Gravity.CENTER
            setPadding(0, 25, 0, 25)
        }
        titleBar.addView(titleText)
        rootLayout.addView(titleBar)

        // --- 2. 顶部导航栏 (OreTabs) ---
        val tabsContainer = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(20, 20, 20, 0)
            }
        }
        
        val topTabs = OreTabs(this).apply {
            // 添加页面导航按钮
            addButton(OreButton(context).apply { text = "主页" })
            addButton(OreButton(context).apply { text = "设置" })
            addButton(OreButton(context).apply { text = "账户" })
        }
        
        tabsContainer.addView(topTabs)
        rootLayout.addView(tabsContainer)

        // --- 3. 主内容渲染区 (FrameLayout) ---
        contentFrame = FrameLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f // 占据剩余空间
            )
        }
        rootLayout.addView(contentFrame)
        
        // --- 4. 绑定导航逻辑 ---
        topTabs.addOnTabChangeListener(object : OreTabs.OnTabChangeListener {
            override fun onTabChanged(index: Int, button: OreButton) {
                switchPage(index)
            }
        })

        // 默认显示主页 (因为 addButton 时会触发激活)
        setContentView(rootLayout)
    }
    
    private fun switchPage(index: Int) {
        contentFrame.removeAllViews()
        when (index) {
            0 -> contentFrame.addView(homePage)
            1 -> contentFrame.addView(settingsPage)
            2 -> contentFrame.addView(accountPage)
            // 预留位置给模组或其他页面
            else -> contentFrame.addView(homePage)
        }
    }
}
