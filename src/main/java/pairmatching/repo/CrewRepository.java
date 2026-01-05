package pairmatching.repo;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CrewRepository {

    private static final String FRONTEND_FILE_PATH = "./src/main/resources/frontend-crew.md";
    private static final String BACKEND_FILE_PATH = "./src/main/resources/backend-crew.md";

    public List<Crew> findByCourse(Course course) {
        return loadAll(course);
    }

    private List<Crew> loadAll(Course course) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(getPath(course)), StandardCharsets.UTF_8)) {

            List<Crew> crews = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) continue;
                crews.add(new Crew(course, line));
            }
            return crews;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getPath(Course course) {
        if (Course.FRONTEND.equals(course)) {
            return FRONTEND_FILE_PATH;
        }
        return BACKEND_FILE_PATH;
    }

    private List<Crew> loadBackendAll() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(BACKEND_FILE_PATH), StandardCharsets.UTF_8)) {
            List<Crew> crews = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) continue;
                crews.add(new Crew(Course.BACKEND, line));
            }
            return crews;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
