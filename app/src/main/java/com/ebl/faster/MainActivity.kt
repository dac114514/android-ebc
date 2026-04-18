package com.ebl.faster

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import dev1503.oreui.StyleSheet
import dev1503.oreui.widgets.OreButton
import dev1503.oreui.widgets.OrePanel
import dev1503.oreui.widgets.OreTabs
import com.ebl.faster.ui.pages.HomePage
import com.ebl.faster.ui.pages.ServersPage
import com.ebl.faster.ui.pages.ForumPage
import com.ebl.faster.ui.pages.AccountPage

class MainActivity : AppCompatActivity() {
    
    private lateinit var contentFrame: FrameLayout
    
    // 缓存页面实例，避免频繁重建
    private val homePage by lazy { HomePage(this) }
    private val serversPage by lazy { ServersPage(this) }
    private val forumPage by lazy { ForumPage(this) }
    private val accountPage by lazy { AccountPage(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 根容器：深色背景，铺满屏幕，禁止自身滚动，因为子页面各自有 ScrollView
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(0xFF1E1E1F.toInt())
        }

        ViewCompat.setOnApplyWindowInsetsListener(rootLayout) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- 1. 标题栏 (可选保留，如果不想要可以删掉) ---
        // 按照 ebl 框架，标题是在各个页面的内容里，而不是全局顶部
        // 因此这里不添加全局标题栏了

        // --- 2. 主内容渲染区 (FrameLayout) ---
        contentFrame = FrameLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f // 占据上方所有可用空间
            )
        }
        rootLayout.addView(contentFrame)

        // --- 3. 底部导航栏 (OreTabs 作为 Bottom Navigation) ---
        val bottomNavContainer = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 0)
            }
        }
        
        val bottomTabs = OreTabs(this).apply {
            // 使用 OreButton 充当底部导航按钮，并添加 xml 图标
            addButton(OreButton(context).apply { 
                text = "主页"
                styleSheet = StyleSheet.STYLE_PANEL 
                setCompoundDrawablesWithIntrinsicBounds(0, dev1503.oreuiforandroid.R.drawable.home, 0, 0)
                setPadding(0, 20, 0, 20)
            })
            addButton(OreButton(context).apply { 
                text = "服务器"
                styleSheet = StyleSheet.STYLE_PANEL 
                setCompoundDrawablesWithIntrinsicBounds(0, dev1503.oreuiforandroid.R.drawable.server, 0, 0)
                setPadding(0, 20, 0, 20)
            })
            addButton(OreButton(context).apply { 
                text = "市场"
                styleSheet = StyleSheet.STYLE_PANEL 
                setCompoundDrawablesWithIntrinsicBounds(0, dev1503.oreuiforandroid.R.drawable.message, 0, 0)
                setPadding(0, 20, 0, 20)
            })
            addButton(OreButton(context).apply { 
                text = "我的"
                styleSheet = StyleSheet.STYLE_PANEL 
                setCompoundDrawablesWithIntrinsicBounds(0, dev1503.oreuiforandroid.R.drawable.user, 0, 0)
                setPadding(0, 20, 0, 20)
            })
            
            // 为了让底部看起来更像导航栏，可以设置一些边距
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(20, 20, 20, 20)
            }
        }
        
        bottomNavContainer.addView(bottomTabs)
        rootLayout.addView(bottomNavContainer)
        
        // --- 4. 绑定导航逻辑 ---
        bottomTabs.addOnTabChangeListener(object : OreTabs.OnTabChangeListener {
            override fun onTabChanged(index: Int, button: OreButton) {
                switchPage(index)
            }
        })

        // 默认显示主页
        setContentView(rootLayout)
    }
    
    private fun switchPage(index: Int) {
        contentFrame.removeAllViews()
        when (index) {
            0 -> contentFrame.addView(homePage)
            1 -> contentFrame.addView(serversPage)
            2 -> contentFrame.addView(forumPage)
            3 -> contentFrame.addView(accountPage)
            else -> contentFrame.addView(homePage)
        }
    }
}
