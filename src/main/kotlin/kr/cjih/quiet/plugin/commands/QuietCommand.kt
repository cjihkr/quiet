package kr.cjih.quiet.plugin.commands

import kr.cjih.quiet.plugin.QuietPlugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class QuietCommand(private val plugin: QuietPlugin) : CommandExecutor, TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size !in 1..2 || (args.size == 2 && args[1] !in listOf("true", "false"))) return false
        val arg1 = args[0]
        val arg2 = args.getOrNull(1) ?: "true"

        when (arg1) {
            "enable" -> {
                if (plugin.quietMode) {
                    sender.sendMessage(Component.text("Quiet mode is already enabled.", NamedTextColor.RED))
                    return true
                }

                plugin.quietMode = true
                sender.sendMessage(
                    Component.text("Quiet mode is ", NamedTextColor.AQUA)
                        .append(Component.text("enabled.", NamedTextColor.YELLOW))
                )

                if (arg2 == "true") {
                    for (player in Bukkit.getOnlinePlayers()) {
                        player.sendMessage(Component.text("조용한 모드가 켜졌습니다. 지금부터 채팅을 입력할 수 없습니다.", NamedTextColor.YELLOW))
                    }
                }
            }
            "disable" -> {
                if (!plugin.quietMode) {
                    sender.sendMessage(Component.text("Quiet mode is not enabled.", NamedTextColor.RED))
                    return true
                }

                plugin.quietMode = false
                sender.sendMessage(
                    Component.text("Quiet mode is ", NamedTextColor.AQUA)
                        .append(Component.text("disabled.", NamedTextColor.GREEN))
                )

                if (arg2 == "true") {
                    for (player in Bukkit.getOnlinePlayers()) {
                        player.sendMessage(Component.text("조용한 모드가 꺼졌습니다. 이제 다시 채팅을 입력할 수 있습니다.", NamedTextColor.YELLOW))
                    }
                }
            }
            else -> return false
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String> {
        if (args.size == 1) { return mutableListOf("enable", "disable") }
        if (args.size == 2) { return mutableListOf("true", "false") }
        return mutableListOf()
    }
}