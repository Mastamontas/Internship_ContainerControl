package com.DEVLOP.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MovementCounter")
public class MovementCounter extends BaseEntity{
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private int id;


    @Getter @Setter
    @Column(name = "CounterKey1", nullable = false)
    private String counterKey1;

    @Getter @Setter
    @Column(name = "CounterKey2", nullable = false)
    private String counterKey2;

    @Getter @Setter
    @Column(name = "CounterKey3", nullable = false)
    private String counterKey3;

    @Getter @Setter
    @Column(name = "CounterKey4", nullable = false)
    private String counterKey4;

    @Getter @Setter
    @Column(name = "CounterKey5", nullable = false)
    private String counterKey5;

    @Getter @Setter
    @Column(name = "MovementCounterKeyRef1", nullable = false)
    private String movementCounterKeyRef1;
    @Getter @Setter
    @Column(name = "MovementCounterKeyRef2", nullable = false)
    private String movementCounterKeyRef2;
    @Getter @Setter
    @Column(name = "MovementCounterKeyRef3", nullable = false)
    private String movementCounterKeyRef3;
    @Getter @Setter
    @Column(name = "MovementCounterKeyRef4", nullable = false)
    private String movementCounterKeyRef4;
    @Getter @Setter
    @Column(name = "MovementCounterKeyRef5", nullable = false)
    private String movementCounterKeyRef5;
    @Getter @Setter
    @Column(name = "CounterAddition", nullable = false)
    private String counterAddition;
    @Getter @Setter
    @Column(name = "CounterLastNumber", nullable = false)
    private int counterLastNumber;
    @Getter @Setter
    @Column(name = "CounterPosition", nullable = false)
    private int counterPosition;
    @Getter @Setter
    @Column(name = "CounterFill", nullable = false)
    private boolean counterFill;
    @Getter @Setter
    @Column(name = "CounterFillChar", nullable = false)
    private String counterFillChar;
    @Getter @Setter
    @Column(name = "CounterReady", nullable = false)
    private boolean counterReady;
    @Getter @Setter
    @Column(name = "CounterComments")
    private String counterComments;

    public MovementCounter() {
    }
}
