package com.kossyuzokwe.fantasy.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.kossyuzokwe.fantasy.dao.LeagueRepository;

public class UniqueLeagueNameValidator implements ConstraintValidator<UniqueLeagueName, String> {
	
	@Autowired
	private LeagueRepository leagueRepository;

	@Override
	public void initialize(UniqueLeagueName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if (leagueRepository == null) {
			return true;
		}
		return leagueRepository.findByLeagueName(name) == null;
	}

}
