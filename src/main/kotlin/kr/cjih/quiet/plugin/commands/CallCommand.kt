package kr.cjih.quiet.plugin.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class CallCommand : CommandExecutor, TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return false

        val message = args.joinToString(" ")
        val msg = Component.text(sender.name, NamedTextColor.AQUA)
            .append(Component.text(" >> $message", NamedTextColor.WHITE))
        val ops = Bukkit.getOnlinePlayers().filter { it.isOp }

        for (op in ops) { op.sendMessage(msg) }
        sender.sendMessage(Component.text("운영자에게 메시지를 보냈습니다.", NamedTextColor.AQUA))

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String> {
        return mutableListOf()
    }
}