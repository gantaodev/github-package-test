package com.zd.core.io;

import org.springframework.core.io.InputStreamSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Spring输入流源
 *
 * @author gantao
 * @date 2022/11/10
 */
public class SpringInputStreamSource implements InputStreamResource {

    private final InputStreamSource inputStreamSource;

    public SpringInputStreamSource(InputStreamSource inputStreamSource) {
        this.inputStreamSource = inputStreamSource;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStreamSource.getInputStream();
    }
}
