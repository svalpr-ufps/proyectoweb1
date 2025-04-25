package co.edu.ufps.kampus.entities;

public enum DayOfWeek {
    MONDAY("Lunes"),
    TUESDAY("Martes"),
    WEDNESDAY("Miércoles"),
    THURSDAY("Jueves"),
    FRIDAY("Viernes"),
    SATURDAY("Sábado"),
    SUNDAY("Domingo");
    
    private final String spanishName;
    
    DayOfWeek(String spanishName) {
        this.spanishName = spanishName;
    }
    
    public String getSpanishName() {
        return spanishName;
    }
}