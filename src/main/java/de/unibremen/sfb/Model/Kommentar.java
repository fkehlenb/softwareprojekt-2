package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class Kommentar {
    @NonNull
    public LocalDateTime dateTime;

    @NonNull
    public String text;

    public Kommentar(LocalDateTime dateTime, String text) {
        this.dateTime = dateTime;
        this.text = text;
    }
}
