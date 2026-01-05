package pairmatching.domain;

import static pairmatching.util.ErrorMessage.COURSE_FORMAT_ERROR;

public enum Course{
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name){
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public static Course of(String course) throws IllegalArgumentException {
        if(BACKEND.getName().equals(course)) {
            return BACKEND;
        }
        if(FRONTEND.getName().equals(course)) {
            return FRONTEND;
        }

        throw new IllegalArgumentException(COURSE_FORMAT_ERROR.getMessage());
    }
}
