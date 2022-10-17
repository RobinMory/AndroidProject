package be.helha.ue3103.android_project.db;

public abstract class StudentDbSchema {
    public static final class StudentTable {
        public static final String NAME = "students";
        public static final class cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
        }
    }
}
