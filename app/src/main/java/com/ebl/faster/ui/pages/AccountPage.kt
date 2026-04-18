package com.ebl.faster.ui.pages

import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.setPadding
import dev1503.oreui.StyleSheet
import dev1503.oreui.widgets.OreCard

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
            text = "我的"
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

        // --- 2. 用户信息区 ---
        val userInfoArea = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
        }
        
        // 头像占位
        val avatar = ImageView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                (60 * resources.displayMetrics.density).toInt(),
                (60 * resources.displayMetrics.density).toInt()
            ).apply {
                setMargins(0, 0, 40, 0)
            }
            setImageResource(dev1503.oreuiforandroid.R.drawable.user)
            setColorFilter(0xFFFFFFFF.toInt()) // 白色图标
        }
        
        val userDetails = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            
            addView(TextView(context).apply {
                text = "默认用户"
                setTextColor(0xFFFFFFFF.toInt())
                textSize = 24f
                paint.isFakeBoldText = true
            })
            
            val statusTag = TextView(context).apply {
                text = "未登录"
                setTextColor(0xFF000000.toInt())
                textSize = 12f
                setBackgroundColor(0xFFCB8700.toInt())
                setPadding(20, 5, 20, 5)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 10, 0, 0)
                }
            }
            addView(statusTag)
        }
        
        userInfoArea.addView(avatar)
        userInfoArea.addView(userDetails)

        // --- 3. 游戏统计区 ---
        val statsSection = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
        }
        
        statsSection.addView(TextView(context).apply {
            text = "游戏统计"
            setTextColor(0xFF4CB04E.toInt())
            textSize = 20f
            paint.isFakeBoldText = true
            setPadding(0, 0, 0, 20)
        })
        
        val statsCardsLayout = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        
        // 游戏小时
        val hoursCard = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_PANEL
            layoutParams = LinearLayout.LayoutParams(0, (100 * resources.displayMetrics.density).toInt(), 1f).apply {
                setMargins(0, 0, 10, 0)
            }
            
            val innerLayout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                
                addView(TextView(context).apply {
                    text = "--"
                    setTextColor(0xFF4CB04E.toInt())
                    textSize = 36f
                    gravity = Gravity.CENTER
                })
                addView(TextView(context).apply {
                    text = "游戏小时"
                    setTextColor(0xFFAAAAAA.toInt())
                    textSize = 14f
                    gravity = Gravity.CENTER
                })
            }
            addView(innerLayout)
        }
        
        // 服务器
        val serversCard = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_PANEL
            layoutParams = LinearLayout.LayoutParams(0, (100 * resources.displayMetrics.density).toInt(), 1f).apply {
                setMargins(10, 0, 0, 0)
            }
            
            val innerLayout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                
                addView(TextView(context).apply {
                    text = "--"
                    setTextColor(0xFF4CB04E.toInt())
                    textSize = 36f
                    gravity = Gravity.CENTER
                })
                addView(TextView(context).apply {
                    text = "服务器"
                    setTextColor(0xFFAAAAAA.toInt())
                    textSize = 14f
                    gravity = Gravity.CENTER
                })
            }
            addView(innerLayout)
        }
        
        statsCardsLayout.addView(hoursCard)
        statsCardsLayout.addView(serversCard)
        statsSection.addView(statsCardsLayout)

        // --- 4. 辅助方法：创建列表项 ---
        fun createListItem(title: String, iconResId: Int): LinearLayout {
            return LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    (60 * resources.displayMetrics.density).toInt()
                )
                setPadding(30, 0, 30, 0)
                
                val iconView = ImageView(context).apply {
                    setImageResource(iconResId)
                    setColorFilter(0xFF4CB04E.toInt()) // 绿色图标
                    layoutParams = LinearLayout.LayoutParams(
                        (24 * resources.displayMetrics.density).toInt(),
                        (24 * resources.displayMetrics.density).toInt()
                    ).apply {
                        setMargins(0, 0, 20, 0)
                    }
                }
                
                val titleView = TextView(context).apply {
                    text = title
                    setTextColor(0xFFFFFFFF.toInt())
                    textSize = 16f
                    layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                }
                
                val arrowView = TextView(context).apply {
                    text = ">"
                    setTextColor(0xFFAAAAAA.toInt())
                    textSize = 16f
                    paint.isFakeBoldText = true
                }
                
                addView(iconView)
                addView(titleView)
                addView(arrowView)
            }
        }

        // --- 5. 目录功能区 ---
        val fileCard = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_PANEL
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
            
            addView(createListItem("存档目录", dev1503.oreuiforandroid.R.drawable.folder))
            addView(createListItem("下载目录", dev1503.oreuiforandroid.R.drawable.download))
            addView(createListItem("检查更新", dev1503.oreuiforandroid.R.drawable.reload))
        }

        // --- 6. 其他功能区 ---
        val aboutCard = OreCard(context).apply {
            styleSheet = StyleSheet.STYLE_PANEL
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 40)
            }
            
            addView(createListItem("隐私政策", dev1503.oreuiforandroid.R.drawable.shield))
            addView(createListItem("捐赠", dev1503.oreuiforandroid.R.drawable.gift))
            addView(createListItem("关于", dev1503.oreuiforandroid.R.drawable.mail))
        }

        contentArea.addView(titleArea)
        contentArea.addView(userInfoArea)
        contentArea.addView(statsSection)
        contentArea.addView(fileCard)
        contentArea.addView(aboutCard)

        addView(contentArea)
    }
}
