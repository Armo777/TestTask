package com.example.testtask.controller;

import com.example.testtask.entity.House;
import com.example.testtask.entity.User;
import com.example.testtask.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/houses")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @PostMapping
    public ResponseEntity<House> createHouse(@RequestBody House house) {
        House savedHouse = houseService.save(house);
        return new ResponseEntity<>(savedHouse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouse(@PathVariable Integer id) {
        Optional<House> house = houseService.findById(id);
        return house.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<House> getAllHouses() {
        return houseService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable Integer id, @RequestBody House house) {
        house.setId(id);
        House updatedHouse = houseService.save(house);
        return ResponseEntity.ok(updatedHouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable Integer id) {
        houseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{houseId}/residents")
    public ResponseEntity<Void> addResident(@PathVariable Integer houseId, @RequestBody User resident) {
        Optional<House> house = houseService.findById(houseId);
        if (house.isPresent()) {
            houseService.addResident(house.get(), resident);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
