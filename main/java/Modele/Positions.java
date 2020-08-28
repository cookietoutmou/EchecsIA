package Modele;

import java.util.HashMap;
import java.util.Map;

public class Positions {
    private static Map<Integer, Map<Integer, Integer[]>> positions = new HashMap<Integer, Map<Integer, Integer[]>>();
    private static Positions instance = new Positions();
    private Positions(){
        for (int i = 0; i < 8; i++) {
            positions.put(i, new HashMap<Integer, Integer[]>());
            for (int j = 0; j < 8; j++) {
                positions.get(i).put(j, new Integer[] {i, j});
            }
        }
    }

    public static Positions getInstance(){
        return instance;
    }

    public Integer[] getLaPosition(int i, int j){
        return positions.get(i).get(j);
    }
}