/*
 * Copyright 2019 OmniFaces
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.omnifaces.optimusfaces.test.view;

import static java.util.Collections.singletonMap;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.optimusfaces.model.PagedDataModel;
import org.omnifaces.optimusfaces.test.model.Group;
import org.omnifaces.optimusfaces.test.model.Person;
import org.omnifaces.optimusfaces.test.service.PersonService;
import org.omnifaces.utils.reflect.Getter;

@Named
@ViewScoped
public class OptimusFacesITNonLazyWithElementCollectionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PagedDataModel<Person> nonLazyGroupies;
	private Set<Group> selectedGroups;

	@Inject
	private PersonService personService;

	@PostConstruct
	public void init() {
		nonLazyGroupies = PagedDataModel.nonLazy(personService.getAllWithGroups()).criteria(this::mapSelectedCriteria).build();
	}

	private Map<Getter<Person>, Object> mapSelectedCriteria() {
		return singletonMap(Person::getGroups, selectedGroups);
	}

	public PagedDataModel<Person> getNonLazyGroupies() {
		return nonLazyGroupies;
	}

	public Set<Group> getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(Set<Group> selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

}

