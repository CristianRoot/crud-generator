/******************************************************************************
 * Copyright (c) 2019. QuantumStudio                                          *
 ******************************************************************************/

package com.quantumstudio.concep.repositories;

import com.quantumstudio.concep.entities.%ENTITY%;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface %ENTITY%Repository extends JpaRepository<%ENTITY%, Long> {

}
