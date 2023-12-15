package pairmatching.view;

import java.io.InputStream;
import java.util.Objects;

public class Source {

    private final InputStream inputStream;

    private Source(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public static Source from(InputStream inputStream) {
        validateNull(inputStream);

        return new Source(inputStream);
    }

    private static void validateNull(InputStream inputStream) {
        if (Objects.nonNull(inputStream)) {
            return;
        }

        throw new IllegalArgumentException("InputStream 은 반드시 존재해야 합니다.");
    }

    public InputStream getInputStream() {
        return inputStream;
    }

}
