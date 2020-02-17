package de.unibremen.sfb.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Car {
    @NonNull
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
