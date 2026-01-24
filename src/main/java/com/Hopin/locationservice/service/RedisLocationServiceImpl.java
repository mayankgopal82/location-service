package com.Hopin.locationservice.service;

import com.Hopin.locationservice.dto.DriverLocationDto;
import com.Hopin.locationservice.dto.NearByDriversRequestDto;
import com.Hopin.locationservice.dto.SaveDriverLocationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisLocationServiceImpl implements LocationService{

    private static  final String DRIVER_GEO_OPS_KEY="driver";
    private static final Double SEARCH_RADIUS=5.0;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    //autowired vs constructor injection

    @Override
    public boolean saveDriverLocation(SaveDriverLocationRequestDto dto) {

            GeoOperations<String,String> geoOps= stringRedisTemplate.opsForGeo();
            geoOps.add(DRIVER_GEO_OPS_KEY,new RedisGeoCommands.GeoLocation<>(dto.getDriverId(),
                    new Point(dto.getLongitude(),dto.getLatitude())));

            return true;
    }

    @Override
    public List<DriverLocationDto> getNearbyDrivers(NearByDriversRequestDto dto) {

            List<DriverLocationDto> drivers= new ArrayList<>();

            GeoOperations<String,String> geoOps=stringRedisTemplate.opsForGeo();
            List<String> driverIds= new ArrayList<>();
            Distance distance =new Distance(SEARCH_RADIUS, Metrics.KILOMETERS);
            Circle circle = new Circle(new Point(dto.getLongitude(),dto.getLatitude()),distance);
            GeoResults<RedisGeoCommands.GeoLocation<String>> results =geoOps.radius(DRIVER_GEO_OPS_KEY,circle);
            for(GeoResult<RedisGeoCommands.GeoLocation<String>> result : results){
                DriverLocationDto driver= DriverLocationDto.builder().driverId(result.getContent().getName())
                        .latitude(result.getContent().getPoint().getY())
                                .longitude(result.getContent().getPoint().getX()).build();
                drivers.add(driver);
            }
            return drivers;

    }
}
