package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Car {
    @NonNull
    @Id
    private String id;
    @NonNull
    private String Brand;
    @NonNull
    private int year;
    @NonNull
    private String color;

    @NonNull
    private int price;

    @NonNull
    private boolean sold;
}
