package be.helha.ue3103.android_project.db;

public abstract class MPMDbSchema {
    public static final class StudentTable {
        public static final String NAME = "students";
        public static final class cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
        }
    }
    public static final class StepTable {
        public static final String NAME = "steps";
        public static final class cols {
            public static final String UUID = "uuid";
            public static final String PROJECT_ID = "id_project";
            public static final String NAME = "name";
            public static final String GRADE  = "grade";
        }
    }
    public static final class ProjectTable {
        public static final String NAME = "projects";
        public static final class cols {
            public static final String UUID = "uuid";
            public static final String STUDENT_ID  = "id_student";
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String AVERAGE  = "average";
        }
    }
}
