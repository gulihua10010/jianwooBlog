package cn.jianwoo.blog.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class FileUtil {
    private static final String SYMBOL_LEFT = "[";
    private static final String SYMBOL_RIGHT = "]";
    private static final String TO = "] to [";
    private static final String TO_FOLDER = "] to folder [";
    private static final Lock lock = new ReentrantLock();

    public static void createDir(File file) throws IOException {

        log.info(">>> Start to create director [{}].", file.getAbsolutePath());
        if (file.exists()) {
            log.info("Thread " + Thread.currentThread().getId() + ": file already exist ");
        } else {
            // 创建目录原子化
            lock.lock();
            try {
                if (file.exists()) {
                    log.info("Thread " + Thread.currentThread().getId() + " in lock : file already exist ");
                } else {
                    if (!file.mkdirs()) {
                        if (file.exists()) {
                            log.info(
                                    "Thread " + Thread.currentThread().getId() + ": mkdir failed, file already exist ");
                        } else {
                            log.error("Thread " + Thread.currentThread().getId() + ": mkdir failed, unknown reason ");
                            throw new IOException("Create director failed.");
                        }
                    } else {
                        log.info("Thread " + Thread.currentThread().getId() + ": mkdir successfully");
                    }
                }
            } finally {
                lock.unlock();
            }
            log.info(">>> End to create director [{}].", file.getAbsolutePath());
        }
    }


    public static void deleteAllFile(File file) {
        if (file.exists() && file.isFile() && !file.delete()) {
            throw new RuntimeException("failed delete file " + file.getPath());
        } else if (file.exists() && file.isDirectory()) {
            File[] fx = file.listFiles();

            for (File aFx : fx) {
                deleteAllFile(aFx);
            }

            if (!file.delete()) {
                throw new RuntimeException(file.getAbsolutePath() + " delete failed");
            }
        }

        if (file.exists() && file.isFile() && !file.delete()) {
            throw new RuntimeException(file.getAbsolutePath() + " delete failed");
        }
    }


    public static void deleteAllFilesIn(File file) {
        if (file.exists() && file.isFile() && !file.delete()) {
            throw new RuntimeException("failed delete file " + file.getPath());
        } else if (file.exists() && file.isDirectory()) {
            File[] fx = file.listFiles();

            for (File aFx : fx) {
                deleteAllFile(aFx);
            }
        }

    }


    public static void writeByteToDisk(byte[] data, String targetFile) throws IOException {
        log.info(">> Start to generate file [{}].", targetFile);
        File directory = new File(targetFile).getParentFile();
        if (!directory.exists()) {
            createDir(directory);
        }
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(targetFile);
            out.write(data);
            out.flush();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
        }

        log.info(">> End to generate file [{}].", targetFile);
    }


    public static byte[] readByteFromDisk(String targetFile) throws IOException {
        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        byte[] rlt = null;

        byte[] buf = new byte[1024];
        int readByte;
        try {
            if (targetFile != null) {
                in = new FileInputStream(targetFile);

                out = new ByteArrayOutputStream();

                while ((readByte = in.read(buf)) != -1) {
                    out.write(buf, 0, readByte);
                }

                rlt = out.toByteArray();
            }
        } finally {
            buf = null;

            if (in != null) {
                in.close();
                in = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
        }

        return rlt;
    }


    /**
     * 复制文件到文件
     *
     * @param sourceFile(要复制的文件)
     * @param targetFile(目的地文件)
     * @param outputLog(是否输出文件移到日志)
     */
    public static void copyFile(File sourceFile, File targetFile, boolean outputLog) throws IOException {
        if (outputLog) {
            log.info(">> Start to copy file " + SYMBOL_LEFT + sourceFile.getPath() + TO + targetFile.getPath()
                    + SYMBOL_RIGHT + ". <<");
        }

        FileUtils.copyFile(sourceFile, targetFile, false);

        if (outputLog) {
            log.info(">> Copy file " + SYMBOL_LEFT + sourceFile.getPath() + TO + targetFile.getPath() + SYMBOL_RIGHT
                    + " successfully. <<");
        }
    }


    /**
     * 复制文件到目录
     *
     * @param sourceFile(要复制的文件)
     * @param targetFolder(目的地目录)
     * @param outputLog(是否输出文件移到日志)
     */
    public static void copyFileToFolder(File sourceFile, File targetFolder, boolean outputLog) throws IOException {
        if (outputLog) {
            log.info(">> Start to copy file " + SYMBOL_LEFT + sourceFile.getPath() + TO_FOLDER + targetFolder.getPath()
                    + SYMBOL_RIGHT + ". <<");
        }

        FileUtils.copyFileToDirectory(sourceFile, targetFolder, false);

        if (outputLog) {
            log.info(">> Copy file " + SYMBOL_LEFT + sourceFile.getPath() + TO_FOLDER + targetFolder.getPath()
                    + SYMBOL_RIGHT + " successfully. <<");
        }
    }


    public static boolean moveFile(File source, String toPath) throws IOException {
        String filename = source.getName();

        File path = new File(toPath);
        if (!path.isDirectory()) {
            createDir(path);
        }

        File newFile = new File(path, filename);

        if (newFile.exists()) {
            newFile.delete();
        }

        try {
            log.info("Start to move file " + SYMBOL_LEFT + source.getPath() + TO + toPath + SYMBOL_RIGHT + ".");

            copyFile(source, newFile, false);

            if (!source.delete()) {
                throw new IOException();
            }

            log.info("Move file " + SYMBOL_LEFT + source.getPath() + TO + toPath + SYMBOL_RIGHT + " successfully.");

            return true;
        } catch (IOException e) {
            log.error(">>>FileUtil.moveFile exec failed, exception : \n", e);
            throw new IOException(
                    "Fail to move file " + SYMBOL_LEFT + source.getPath() + TO + toPath + SYMBOL_RIGHT + ".", e);
        }
    }


    /**
     * 检验文件是否存在
     *
     * @param fileFullName 全路径
     */
    public static boolean isFileExist(String fileFullName) {
        File file = null;
        try {
            file = new File(fileFullName);
            return file.exists();
        } catch (Exception e) {
            log.error(">>> FileUtil.isFileExist exec failed, exception : \n", e);
            return false;
        } finally {
            file = null;
        }
    }

}
