package sircow.noworldbordertint.config;

import com.google.gson.annotations.Expose;
import io.github.notenoughupdates.moulconfig.annotations.*;

public class MainCategory {
    @Expose
    @ConfigOption(name = "Enabled", desc = "Toggle removing the world border tint")
    @ConfigEditorBoolean
    public boolean hideTint = true;
}
