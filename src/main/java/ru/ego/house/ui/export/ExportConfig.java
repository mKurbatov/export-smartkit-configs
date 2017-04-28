package ru.ego.house.ui.export;

import static com.codeborne.selenide.Selenide.*;
import static ru.ego.house.ui.export.ConfigurationProvider.*;

/**
 * @author mkurbatov
 */
public class ExportConfig {

    public static void main(String[] args) {
        open(get().applicationUrl());
    }
}
