package pairmatching;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileHandler {
    // String 을 반환하는 걸로 하자
    private static final String ROOT = "src/main/resources/";
    private static final String BACKEND_FILE_NAME = "backend-crew.md";
    private static final String FRONTEND_FILE_NAME = "frontend-crew.md";


    public static String readBackendCrew() {
        String backendCrewFileContent = null;

        try (BufferedReader reader = getFileInputStream(BACKEND_FILE_NAME)) {
            backendCrewFileContent = readFile(reader);
        } catch (IOException exception) {
            System.out.println(Const.ERROR + exception.getMessage());
        }

        return backendCrewFileContent;
    }

    public static String readFrontendCrew() {
        String frontendCrewFileContent = null;

        try (BufferedReader reader = getFileInputStream(FRONTEND_FILE_NAME);) {
            frontendCrewFileContent = readFile(reader);
        } catch (IOException exception) {
            System.out.println(Const.ERROR + exception.getMessage());
        }

        return frontendCrewFileContent;
    }

    private static BufferedReader getFileInputStream(String fileName) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(ROOT + fileName)));
    }

    private static String readFile(BufferedReader reader) throws IOException {
        StringBuilder fileContent = new StringBuilder();

        while (true) {
            String name = reader.readLine();

            if (name == null) {
                break;
            }

            fileContent.append(name).append(" ");
        }

        return fileContent.toString();
    }
}
