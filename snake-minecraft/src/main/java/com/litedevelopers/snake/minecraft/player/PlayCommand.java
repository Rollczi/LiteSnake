package com.litedevelopers.snake.minecraft.player;

import com.litedevelopers.snake.engine.SnakeGameEngine;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.section.Section;
import org.bukkit.entity.Player;

@Section(route = "play")
public class PlayCommand {

    private final SnakeGameEngine engine;

    public PlayCommand(SnakeGameEngine engine) {
        this.engine = engine;
    }

    @Execute
    void play(Player player) {
        PlayerState state = new PlayerState();
        engine.registerPlayer(new MinecraftPlayer(player.getName(), player.getUniqueId(), state));
    }

}
