package headfirst.design.decorator.io;

import java.io.*;

public class LowerCaseInputStream extends FilterInputStream {

    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    // 소문자 변환
    @Override
    public int read() throws IOException {
        int c = in.read();
        return (c == -1) ? c : Character.toLowerCase((char)c);
    }

    // 사용하지 않음, offset : 시작점
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = in.read(b, off, len); // 전체 byte 길이 반환
        for(int i = off; i < off + result; i++) {
            b[i] = (byte)Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
