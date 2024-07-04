package com.example.testtask.service;

import com.example.testtask.entity.House;
import com.example.testtask.entity.User;
import com.example.testtask.repositories.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    @Autowired
    private HouseRepository houseRepository;

    public House save(House house) {
        return houseRepository.save(house);
    }

    public Optional<House> findById(Integer id) {
        return houseRepository.findById(id);
    }

    public List<House> findAll() {
        return houseRepository.findAll();
    }

    public void deleteById(Integer id) {
        houseRepository.deleteById(id);
    }

    public void addResident(House house, User resident) {
        house.getResidents().add(resident);
        houseRepository.save(house);
    }
}
