package com.bonobono.backend.chatting.handler;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class ByteArrayMultipartFile implements MultipartFile {
    private final byte[] bytes;
    private String contentType;
    private String fileName;

    public ByteArrayMultipartFile(byte[] bytes, String contentType, String fileName) {
        this.bytes = bytes;
        this.contentType = contentType;
        this.fileName = fileName;
    }
    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getOriginalFilename() {
        return fileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean isEmpty() {
        return bytes == null || bytes.length == 0;
    }

    @Override
    public long getSize() {
        return bytes.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return bytes;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
//        if (!file.exists() && !file.createNewFile()) {
//            throw new IOException("로컬의 ");
//        }
//        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
//            fileOutputStream.write(bytes);
//        }
    }
}