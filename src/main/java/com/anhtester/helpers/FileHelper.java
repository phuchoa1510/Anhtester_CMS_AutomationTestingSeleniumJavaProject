package com.anhtester.helpers;

import com.anhtester.utils.LogUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileHelper {

    /**
     * Upload file bằng Robot Class (Dùng cho các cửa sổ Window Pop-up)
     *
     * @param filePath Đường dẫn tuyệt đối đến file (Ví dụ: C:\data\image.png)
     */
    public static void uploadFileWithRobot(String filePath) {
        try {
            // Copy đường dẫn file vào Clipboard (Bộ nhớ tạm)
            StringSelection str = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

            Robot robot = new Robot();
            LogUtils.info("Uploading file: " + filePath);

            // Nhấn Ctrl + V để dán đường dẫn file
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            // Tạm dừng một chút để đảm bảo đường dẫn đã được dán xong
            robot.delay(1000);

            // Nhấn Enter để xác nhận upload
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            LogUtils.info("Upload file successfully!");
        } catch (AWTException e) {
            LogUtils.error("Error with Robot class: " + e.getMessage());
        }
    }
}
