package com.Hopin.locationservice.service;

import com.Hopin.locationservice.dto.DriverLocationDto;
import com.Hopin.locationservice.dto.NearByDriversRequestDto;
import com.Hopin.locationservice.dto.SaveDriverLocationRequestDto;

import java.util.List;

public interface LocationService {

    boolean saveDriverLocation(SaveDriverLocationRequestDto dto);
    List<DriverLocationDto> getNearbyDrivers(NearByDriversRequestDto dto);
}
