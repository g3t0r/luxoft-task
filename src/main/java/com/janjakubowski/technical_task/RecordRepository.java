package com.janjakubowski.technical_task;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecordRepository extends CrudRepository<Record, String> {
}
