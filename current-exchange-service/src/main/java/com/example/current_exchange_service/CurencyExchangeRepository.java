package com.example.current_exchange_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurencyExchangeRepository extends JpaRepository<CunrencyEchange, Long> {

    CunrencyEchange findByFromAndTo(String from, String to);
}
