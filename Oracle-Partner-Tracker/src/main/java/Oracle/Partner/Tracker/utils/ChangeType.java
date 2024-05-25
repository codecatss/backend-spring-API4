package Oracle.Partner.Tracker.utils;

public enum ChangeType {
    CREATE, INSERT, UPDATE, DELETE;

    public static ChangeType toChangeType(String changeTypeString){
        return switch (changeTypeString.toLowerCase().trim()) {
            case "delete" -> ChangeType.DELETE;
            case "update" -> ChangeType.UPDATE;
            case "create" -> ChangeType.CREATE;
            default -> ChangeType.INSERT;
        };
    }
}
