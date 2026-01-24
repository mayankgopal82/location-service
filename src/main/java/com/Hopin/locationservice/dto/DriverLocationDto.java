package com.Hopin.locationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverLocationDto {
    private String driverId;
    private double latitude;
    private double longitude;
}
