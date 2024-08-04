package mff.business;

import java.util.ArrayList;
import mff.application.Main;
import mff.application.Services;
import mff.objects.Diary;
import mff.persistence.Database;

public class AccessDiary {
    private Database dataAccess;
    private ArrayList<Diary> reflections;


    public AccessDiary() {
        dataAccess = Services.getDataAccess(Main.dbName);
        reflections = null;
    }

    public void addReflection(Diary newDiary){
        dataAccess.addReflection(newDiary);
    }

    public ArrayList<Diary> getReflections() {
        return dataAccess.getAllReflections();
    }
}
