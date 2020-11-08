package main;

public class IDGenerator {
    private static long lastSessionTimestamp=0;
    private String crtTimetamp;
    private long idCounter;

    public IDGenerator(){
        idCounter = 0;
        crtTimetamp = Long.toString(getLastSessionTimestamp());
    }

    public String getNewId(){
        return crtTimetamp + idCounter++;
    }

    private static long getLastSessionTimestamp(){
        long crtTimeSeconds = java.time.Instant.now().getEpochSecond(); // ii atribuie timpul unix curent
        if( crtTimeSeconds > lastSessionTimestamp) {
            lastSessionTimestamp = crtTimeSeconds;
        }else{
            lastSessionTimestamp++;
        }
        return lastSessionTimestamp;
    }

}
