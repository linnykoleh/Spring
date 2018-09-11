package com.guitar.db.repository.spring_data.custom;

public class ModelDataJPARepositoryImpl implements ModelDataCustomJPARepository {

    @Override
    public void aCustomMethod() {
        System.out.println("Custom method");
    }
}
