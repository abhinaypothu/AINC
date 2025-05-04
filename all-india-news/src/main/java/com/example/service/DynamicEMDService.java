package com.example.service;

import com.example.model.DynamicEMD;
import com.example.repository.DynamicEMDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicEMDService {

    @Autowired
    private DynamicEMDRepository dynamicEMDRepository;

    public List<DynamicEMD> getAllDynamicEMDs() {
        return dynamicEMDRepository.findAll();
    }

    public DynamicEMD getDynamicEMDById(Long id) {
        return dynamicEMDRepository.findById(id).orElse(null);
    }

    public DynamicEMD createDynamicEMD(DynamicEMD dynamicEMD) {
        return dynamicEMDRepository.save(dynamicEMD);
    }

    public DynamicEMD updateDynamicEMD(Long id, DynamicEMD dynamicEMD) {
        DynamicEMD existingDynamicEMD = dynamicEMDRepository.findById(id).orElse(null);
        if (existingDynamicEMD != null) {
            dynamicEMD.setId(id);
            return dynamicEMDRepository.save(dynamicEMD);
        }
        return null;
    }

    public void deleteDynamicEMD(Long id) {
        dynamicEMDRepository.deleteById(id);
    }
}
