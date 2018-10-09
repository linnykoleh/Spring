package com.learning.linnyk.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learning.linnyk.model.Shipwreck;
import com.learning.linnyk.repository.ShipwreckStub;

/**
 * For activating spring profiles - '-Dspring.profiles.active=test'
 */
@RestController
@RequestMapping("api/v1")
public class ShipwreckControllerV1 {

	@RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
	public List<Shipwreck> list() {
		return ShipwreckStub.list();
	}

	@RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		return ShipwreckStub.create(shipwreck);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
	public Shipwreck create(@PathVariable Long id) {
		return ShipwreckStub.get(id);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
	public Shipwreck create(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
		return ShipwreckStub.update(id, shipwreck);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
	public Shipwreck delete(@PathVariable Long id) {
		return ShipwreckStub.delete(id);
	}
}
