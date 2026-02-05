package com.Hopin.locationservice.controller;

import com.Hopin.locationservice.dto.DriverLocationDto;
import com.Hopin.locationservice.dto.NearByDriversRequestDto;
import com.Hopin.locationservice.dto.SaveDriverLocationRequestDto;
import com.Hopin.locationservice.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {


    @Autowired
    LocationService locationService;

    @PostMapping("/driver")
    public ResponseEntity<Boolean> saveDriverLocaton(@RequestBody SaveDriverLocationRequestDto dto){
        try{
            boolean response= locationService.saveDriverLocation(dto);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch(Exception exception){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/nearby/drivers")
    public ResponseEntity<List<DriverLocationDto>> nearbyDrivers(@RequestBody NearByDriversRequestDto dto){

        try{
            List<DriverLocationDto> drivers = locationService.getNearbyDrivers(dto);
            return new ResponseEntity<>(drivers,HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

}
