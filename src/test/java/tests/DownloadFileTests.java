package tests;

import com.codeborne.selenide.Configuration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.MatcherAssert.assertThat;

public class DownloadFileTests {
    static final String PATH_TO_DOWNLOAD = "downloads";

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @AfterAll
    static void cleanFolders() throws IOException {
        FileUtils.cleanDirectory(new File(PATH_TO_DOWNLOAD));
    }

    @Test
    void downloadTextFileTest() throws IOException {
        Configuration.downloadsFolder = PATH_TO_DOWNLOAD;
        String expectedText = "aippolitov";

        open("https://gitlab.com/qa5_hw/qa_files/-/blob/main/src/test/resources/connects.txt");
        File downloadedFile = $("[title='Download']").download();
        String fileContent = FileUtils.readFileToString(downloadedFile, "UTF-8");

        Assertions.assertTrue(fileContent.contains(expectedText));
    }
}
