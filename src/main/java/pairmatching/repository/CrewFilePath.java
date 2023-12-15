package pairmatching.repository;

public enum CrewFilePath {

    BACKEND_CREW_FILE_PATH("src/main/resources/backend-crew.md"),
    FRONTEND_CREW_FILE_PATH("src/main/resources/frontend-crew.md"),
    ;

    private final String path;

    CrewFilePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
