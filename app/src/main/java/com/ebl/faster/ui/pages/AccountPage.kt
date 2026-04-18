package com.ebl.faster.ui.pages

import android.content.Context
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.setPadding
import dev1503.oreui.StyleSheet
import dev1503.oreui.widgets.OreButton
import dev1503.oreui.widgets.OreCard
import dev1503.oreui.widgets.OreEditText

class AccountPage(context: Context) : ScrollView(context) {

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
            text = "账户管理"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 22f
            paint.isFakeBoldText = true
            setPadding(10, 0, 10, 30)
        }
        contentArea.addView(pageTitle)

        // 添加新账户卡片
        val addAccountCard = OreCard(context).apply {
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

        val addAccountTitle = TextView(context).apply {
            text = "添加本地账户"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 16f
            paint.isFakeBoldText = true
            setPadding(0, 0, 0, 20)
        }
        addAccountCard.addView(addAccountTitle)

        val usernameInput = OreEditText(context).apply {
            hint = "输入游戏角色名..."
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (45 * resources.displayMetrics.density).toInt()
            ).apply {
                setMargins(0, 0, 0, 20)
            }
        }
        addAccountCard.addView(usernameInput)

        val loginBtn = OreButton(context).apply {
            text = "登录并保存"
            styleSheet = StyleSheet.STYLE_GREEN
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (50 * resources.displayMetrics.density).toInt()
            )
        }
        addAccountCard.addView(loginBtn)

        contentArea.addView(addAccountCard)

        // 现有账户列表
        val accountListTitle = TextView(context).apply {
            text = "已保存的账户"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 16f
            paint.isFakeBoldText = true
            setPadding(10, 0, 10, 20)
        }
        contentArea.addView(accountListTitle)

        // 示例账户
        val accountItemCard = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_PANEL
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 20)
            }
            setPadding(20)
        }

        val accountName = TextView(context).apply {
            text = "Steve"
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 18f
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }
        
        val useBtn = OreButton(context).apply {
            text = "使用中"
            styleSheet = StyleSheet.STYLE_DISABLED
            layoutParams = LinearLayout.LayoutParams(
                (100 * resources.displayMetrics.density).toInt(),
                (40 * resources.displayMetrics.density).toInt()
            )
        }

        accountItemCard.addView(accountName)
        accountItemCard.addView(useBtn)
        
        contentArea.addView(accountItemCard)

        addView(contentArea)
    }
}
