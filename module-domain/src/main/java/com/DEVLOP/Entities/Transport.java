package com.DEVLOP.Entities;

import java.sql.Date;
import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="Transport")
public class Transport extends BaseEntity {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key
    @Column(name ="Id", unique = true, nullable = false)
    private int id;

    @Getter @Setter
    @Column(name = "TradeLine", nullable = false, unique = true)
    private int tradeLine; //foreign key

    @Getter @Setter
    @Column(name = "TradeID", nullable = false, unique = true)
    private int tradeID; //foreign key

    @Getter @Setter
    @Column(name = "VoyageID", nullable = false, unique = true)
    private int voyageID; //foreign key

    @Getter @Setter
    @Column(name = "BusinessUnitID")
    private int businessUnitID;

    @Getter @Setter
    @Column(name = "BusinessStatusID")
    private int businessStatusID;

    @Getter @Setter
    @Column(name = "AccessUserID", unique = true)
    private int accessUserID;

    @Getter @Setter
    @Column(name = "TransportDate")
    private Date transportDate;

    @Getter @Setter
    @Column(name = "TransportExchange")
    private Date transportExchange;

    @Getter @Setter
    @Column(name = "TransportAccounting")
    private String transportAccounting;

    @Getter @Setter
    @Column(name = "TransportState")
    private String transportState;

    @Getter @Setter
    @Column(name = "TransportComments")
    private String transportComments;

    public Transport() {
    }
}
