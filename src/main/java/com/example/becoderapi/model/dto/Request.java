package com.example.becoderapi.model.dto;

import com.example.becoderapi.model.data.Type;

public record Request(String id, Type type, double cost) {
}
