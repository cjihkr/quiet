package kr.cjih.quiet.plugin

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class EventListener(private val plugin: QuietPlugin) : Listener {
    @EventHandler
    fun onAsyncChat(event: AsyncChatEvent) {
        if (plugin.quietMode && !event.player.isOp) {
            event.player.sendMessage(Component.text("운영자가 채팅을 제한한 상태입니다.", NamedTextColor.RED))
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        if (plugin.quietMode && !event.player.isOp && !(event.message.startsWith("/call ") || event.message.startsWith("/호출 "))) {
            event.player.sendMessage(Component.text("운영자가 명령어를 제한한 상태입니다. 호출 명령어만 사용할 수 있습니다.", NamedTextColor.RED))
            event.isCancelled = true
        }
    }
}
