package com.learning.linnyk.jb._2_scope_lifecycle.beans.autowired;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class Shop {

	private Person person;
	private Item item;
	private Food food;
	private Building building;
	private NotExistsBean notExistsBean;

	@Autowired
	private Shop(Person person) {
		this.person = person;
	}

	@Autowired
	private void setItem(Item item) {
		this.item = item;
	}

	@Autowired
	private void injectFood(Food food) {
		this.food = food;
	}

	@Autowired
	private void injectBuildingOptional(Optional<Building> building) {
		this.building = building.orElseThrow(RuntimeException::new);
	}

	@Autowired
	private void injectNotExistsBeanOptional(Optional<NotExistsBean> notExistsBean) {
		// Will be injected Optional.empty due to not exists the NotExistsBean Bean
		this.notExistsBean = notExistsBean.orElseGet(() -> {
			System.out.println("injectNotExistsBeanOptional: " + notExistsBean); //injectNotExistsBeanOptional: Optional.empty
			return new NotExistsBean();
		});
	}

	@Override
	public String toString() {
		return "Shop{" +
				"person=" + person +
				"item=" + item +
				"food=" + food +
				"building=" + building +
				"notExistsBean=" + notExistsBean +
				'}';
	}
}
