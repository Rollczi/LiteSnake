package com.litedevelopers.snake.engine.ai;

import com.litedevelopers.snake.engine.player.Player;

public class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name, new AIInteraction());
    }

}
