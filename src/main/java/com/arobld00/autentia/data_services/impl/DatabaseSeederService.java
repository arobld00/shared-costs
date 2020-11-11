package com.arobld00.autentia.data_services.impl;

import com.arobld00.autentia.repositories.mongodb.FriendEntity;
import com.arobld00.autentia.repositories.mongodb.FriendRepository;
import com.arobld00.autentia.repositories.mongodb.PaymentEntity;
import com.arobld00.autentia.repositories.mongodb.PaymentRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
//@Profile("dev")
public class DatabaseSeederService implements CommandLineRunner {

    private FriendRepository friendRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public DatabaseSeederService(
            FriendRepository friendRepository,
            PaymentRepository paymentRepository
    ) {
        this.friendRepository = friendRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.deleteAllAndInitializeAndSeedDataBase();
    }

    private void deleteAllAndInitializeAndSeedDataBase() {
        this.deleteAll();
        this.seedDataBaseJava();
    }

    private void deleteAll() {
        LogManager.getLogger(this.getClass()).warn("------- Delete All -----------");
        // Delete Repositories -----------------------------------------------------
        this.paymentRepository.deleteAll();
        this.friendRepository.deleteAll();

        // -------------------------------------------------------------------------
    }

    public void seedDataBaseJava() {
        LogManager.getLogger(this.getClass()).warn("------- Initial Load from JAVA -----------");
        // TODO
        FriendEntity[] friends = {
                new FriendEntity("Francisco Buyo", "666666601"),
                new FriendEntity("Alfonso Perez", "666666602"),
                new FriendEntity("Raul Gonzalez", "666666603"),
                new FriendEntity("Jose Maria Gutierrez", "666666604")
        };
        this.friendRepository.saveAll(Arrays.asList(friends));
        LogManager.getLogger(this.getClass()).warn("        ------- friends");
        PaymentEntity[] payments = {
                PaymentEntity.builder().provider(friends[0]).concept("Cena").amount(new BigDecimal("100.00")).build(),
                PaymentEntity.builder().provider(friends[1]).concept("Taxi").amount(new BigDecimal("10")).build(),
                PaymentEntity.builder().provider(friends[2]).concept("Comida").amount(new BigDecimal("53.40")).build(),
                PaymentEntity.builder().provider(friends[3]).concept("Compra").amount(new BigDecimal("7.12")).build(),
                PaymentEntity.builder().provider(friends[1]).amount(new BigDecimal("10.12")).build()
        };
        this.paymentRepository.saveAll(Arrays.asList(payments));
        LogManager.getLogger(this.getClass()).warn("        ------- payments");
    }

}
