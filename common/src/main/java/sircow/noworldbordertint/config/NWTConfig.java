package sircow.noworldbordertint.config;

import com.google.gson.annotations.Expose;
import io.github.notenoughupdates.moulconfig.Config;
import io.github.notenoughupdates.moulconfig.Social;
import io.github.notenoughupdates.moulconfig.annotations.Category;
import io.github.notenoughupdates.moulconfig.common.MyResourceLocation;
import io.github.notenoughupdates.moulconfig.common.text.StructuredText;
import sircow.noworldbordertint.Constants;

import java.util.List;

public class NWTConfig extends Config {
    private final MyResourceLocation github = new MyResourceLocation(Constants.MOD_ID, "social/github.png");

    @Override
    public StructuredText getTitle() {
        return StructuredText.of("No Worldborder Tint");
    }

    @Override
    public List<Social> getSocials() {
        return List.of(
                Social.forLink(StructuredText.of("GitHub"), github, "https://github.com/Sir-Cow/noWorldborderTint")
        );
    }

    @Expose
    @Category(name = "Settings", desc = "")
    public MainCategory mainCategory = new MainCategory();
}
