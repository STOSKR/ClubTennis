
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.Court;
import model.Member;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author shiyi
 */
public class Reserva {
    
    private LocalDate madeForDay;
    private LocalTime fromTime;
    private LocalTime toTime;
    private Boolean paid;
    private Court court;

    public Reserva(LocalDate madeForDay, LocalTime fromTime, LocalTime toTime, Boolean paid, Court court) {
        this.madeForDay = madeForDay;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.paid = paid;
        this.court = court;
    }

    public LocalDate getMadeForDay() {
        return madeForDay;
    }

    public void setMadeForDay(LocalDate madeForDay) {
        this.madeForDay = madeForDay;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }
    
    
}
