package kr.cjih.quiet.plugin.commands

import kr.cjih.quiet.plugin.QuietPlugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class QuietCommand(private val plugin: QuietPlugin) : CommandExecutor, TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return false

        when (args[0].lowercase()) {
            "true" -> {
                if (plugin.quietMode) {
                    sender.sendMessage(Component.text("Quiet mode is already enabled.", NamedTextColor.RED))
                    return true
                }
                plugin.quietMode = true
                sender.sendMessage(
                    Component.text("Quiet mode is ", NamedTextColor.AQUA)
                        .append(Component.text("enabled.", NamedTextColor.YELLOW))
                )
            }
            "false" -> {
                if (!plugin.quietMode) {
                    sender.sendMessage(Component.text("Quiet mode is not enabled.", NamedTextColor.RED))
                    return true
                }
                plugin.quietMode = false
                sender.sendMessage(
                    Component.text("Quiet mode is ", NamedTextColor.AQUA)
                        .append(Component.text("disabled.", NamedTextColor.GREEN))
                )
            }
            else -> return false
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String> {
        if (args.size == 1) { return mutableListOf("true", "false") }
        return mutableListOf()
    }
}