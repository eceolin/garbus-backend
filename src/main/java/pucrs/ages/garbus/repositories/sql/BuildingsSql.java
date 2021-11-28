package pucrs.ages.garbus.repositories.sql;

public class BuildingsSql {
    public BuildingsSql() {
    }

    public static final String FIND_BUILDING_NAME_BY_TRASH_ID =
            "SELECT NAME FROM BUILDINGS WHERE BUILDINGS.ID = :buildingIdFromTrash";
}
