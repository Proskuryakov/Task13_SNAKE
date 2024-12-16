package ru.vsu.cs.vvp.eproskuryakov.snake;

import ru.vsu.cs.vvp.eproskuryakov.snake.game.Game;
import ru.vsu.cs.vvp.eproskuryakov.snake.game.GameController;
import ru.vsu.cs.vvp.eproskuryakov.snake.ui.GameFieldGenerator;
import ru.vsu.cs.vvp.eproskuryakov.snake.ui.MainPanel;

public class Application {

    public static void main(String[] args) {
        Game game = new Game();
        GameController gameController = new GameController(game);
        GameFieldGenerator gameFieldGenerator = new GameFieldGenerator(gameController);
        MainPanel mainPanel = new MainPanel(gameController, gameFieldGenerator);
    }

}
