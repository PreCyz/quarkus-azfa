package io.quarkus.services;

import io.quarkus.db.dao.CarDao;
import io.quarkus.db.entities.CarEntity;
import io.quarkus.dto.CarDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class ResourceService {

    private final CarDao carDao;

    @Inject
    public ResourceService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<CarDto> getCars() {
        return carDao.findAll()
                .stream()
                .map(CarDto::new)
                .collect(toList());
    }

    public CarDto createResource(CarDto carDto) {
        CarEntity saved = carDao.save(CarDto.createEntity(carDto));
        return new CarDto(saved);
    }
}
