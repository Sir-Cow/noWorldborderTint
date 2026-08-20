package sircow.noworldbordertint.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.notenoughupdates.moulconfig.processor.BuiltinMoulConfigGuis;
import io.github.notenoughupdates.moulconfig.processor.ConfigProcessorDriver;
import io.github.notenoughupdates.moulconfig.processor.MoulConfigProcessor;
import org.apache.commons.io.FileUtils;
import sircow.noworldbordertint.CommonClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    private static final File configFile = new File("config/noworldbordertint.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    public MoulConfigProcessor<NWTConfig> processor;

    public void firstLoad() {
        if (configFile.exists()) {
            try {
                String json = FileUtils.readFileToString(configFile, StandardCharsets.UTF_8);
                CommonClass.config = gson.fromJson(json, NWTConfig.class);
            }
            catch (Exception e) {
                e.printStackTrace();
                CommonClass.config = new NWTConfig();
            }
        }
        else {
            CommonClass.config = new NWTConfig();
            saveConfig();
        }
    }

    public void ensureProcessor() {
        if (processor == null) recreateProcessor();
    }

    public void saveConfig() {
        try {
            File parent = configFile.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("Failed to create directory: " + parent);
            }

            FileUtils.writeStringToFile(configFile, gson.toJson(CommonClass.config), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recreateProcessor() {
        processor = new MoulConfigProcessor<>(CommonClass.config);
        BuiltinMoulConfigGuis.addProcessors(processor);
        ConfigProcessorDriver driver = new ConfigProcessorDriver(processor);
        driver.warnForPrivateFields = false;
        driver.processConfig(CommonClass.config);
    }
}
