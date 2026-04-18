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

class ServersPage(context: Context) : ScrollView(context) {

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
                text = "服务器列表"
                setTextColor(0xFFFFFFFF.toInt())
                textSize = 24f
                paint.isFakeBoldText = true
            }
            addView(pageTitle)
        }

        contentArea.addView(titleArea)

        val serverListSection = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        fun createServerCard(name: String, status: String, players: String, ping: String): OreCard {
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

                val nameView = TextView(context).apply {
                    text = name
                    setTextColor(0xFFFFFFFF.toInt())
                    textSize = 18f
                    paint.isFakeBoldText = true
                    setPadding(0, 0, 0, 10)
                }
                
                val infoRow = LinearLayout(context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply { setMargins(0, 0, 0, 20) }
                    
                    addView(TextView(context).apply {
                        text = status
                        setTextColor(if (status == "在线") 0xFF4CB04E.toInt() else 0xFFD55E5E.toInt())
                        textSize = 14f
                        layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    })
                    
                    addView(TextView(context).apply {
                        text = "人数: $players"
                        setTextColor(0xFFB1B2B5.toInt())
                        textSize = 14f
                        layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                    })
                    
                    addView(TextView(context).apply {
                        text = "延迟: $ping"
                        setTextColor(0xFF4CB04E.toInt())
                        textSize = 14f
                        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    })
                }

                val joinBtn = OreButton(context).apply {
                    text = "加入服务器"
                    styleSheet = StyleSheet.STYLE_WHITE
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    setOnClickListener {
                        Toast.makeText(context, "正在连接到 $name...", Toast.LENGTH_SHORT).show()
                    }
                }

                addView(nameView)
                addView(infoRow)
                addView(joinBtn)
            }
        }

        serverListSection.addView(createServerCard("Hypixel 官方服务器", "在线", "45120/100000", "120ms"))
        serverListSection.addView(createServerCard("2b2t 无政府生存", "在线", "850/1000", "240ms"))
        serverListSection.addView(createServerCard("花雨庭 国服小游戏", "维护中", "0/5000", "--ms"))

        contentArea.addView(serverListSection)
        addView(contentArea)
    }
}
