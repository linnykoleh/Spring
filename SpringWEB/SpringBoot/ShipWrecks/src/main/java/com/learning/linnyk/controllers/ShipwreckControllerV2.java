package com.learning.linnyk.controllers;

import com.learning.linnyk.model.Shipwreck;
import com.learning.linnyk.repository.ShipwreckRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * For activating spring profiles - '-Dspring.profiles.active=test'
 */
@RestController
@RequestMapping("api/v2")
public class ShipwreckControllerV2 {

    private final ShipwreckRepository repository;

    @Autowired
    public ShipwreckControllerV2(ShipwreckRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
	public List<Shipwreck> list() {
		return repository.findAll();
	}

	@RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		return repository.saveAndFlush(shipwreck);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
	public Shipwreck get(@PathVariable Long id) {
		return repository.findOne(id);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
	public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
        final Shipwreck dbShipwreck = repository.findOne(id);
        BeanUtils.copyProperties(shipwreck, dbShipwreck);
        return repository.saveAndFlush(dbShipwreck);
    }

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
	public Shipwreck delete(@PathVariable Long id) {
        final Shipwreck dbShipwreck = repository.findOne(id);
        repository.delete(dbShipwreck);
        return dbShipwreck;
	}
}
