package be.helha.ue3103.android_project.db;

public abstract class StepDbSchema {
    public static final class StepTable {
        public static final String NAME = "steps";
        public static final class cols {
            public static final String UUID = "uuid";
            public static final String PROJECT_ID = "id_project";
            public static final String NAME = "name";
            public static final String GRADE  = "grade";
        }
    }
}
