package kr.cjih.quiet.plugin

import kr.cjih.quiet.plugin.commands.CallCommand
import kr.cjih.quiet.plugin.commands.QuietCommand
import org.bukkit.plugin.java.JavaPlugin

class QuietPlugin : JavaPlugin() {
    var quietMode = false

    override fun onEnable() {
        server.pluginManager.registerEvents(EventListener(this), this)
        getCommand("quiet")?.setExecutor(QuietCommand(this))
        getCommand("call")?.setExecutor(CallCommand())
    }
}
