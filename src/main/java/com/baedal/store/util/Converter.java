package com.baedal.store.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Converter {

	private final ObjectMapper objectMapper;

	public <T> T jsonToDto(String value, Class<T> dtoClass) {
		try {
			return objectMapper.readValue(value, dtoClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
