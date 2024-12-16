package ru.vsu.cs.vvp.eproskuryakov.snake;

public final class Config {

    public static final String FRAME_TITLE_TEXT = "Игра ЗМЕЙКА";
    public static final String START_BUTTON_TEXT = "Старт";
    public static final String STOP_BUTTON_TEXT = "Стоп";
    public static final String RESTART_BUTTON_TEXT = "Перезапуск";
    public static final String INFO_BUTTON_TEXT = "Инструкция";
    public static final String INFO_WINDOW_TITLE = "Инструкция";
    public static final String INFO_WINDOW_CONTENT = """
        cтарт/стоп: enter
        управление: стрелки
        регулирование скорости: +-
        """;
    public static final String GAME_OVER_WINDOW_TITLE = "Игра окончена";
    public static final String GAME_OVER_WINDOW_CONTENT = """
        Вы проиграли.
        Хотите повторить?""";

    public static final int CELL_COUNT = 20;
    public static final int CELL_SIZE = 25;

    public static final int SLIDER_STEP = 50;
    public static final int SLIDER_MIN = 1;
    public static final int SLIDER_MAX = 1000;
    public static final int SLIDER_INIT = 750;


}
