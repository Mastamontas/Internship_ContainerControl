package com.DEVLOP.Entities;
import jakarta.persistence.*;
@Entity
@Table(name = "MovementCounter")
public class MovementCounter extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Long id;

    //counterKey1
    //primary key desta entidade, tem de ser unique
    @Column(name = "CounterKey1", nullable = false)
    private String counterKey1;

    //counterKey2
    //primary key desta entidade, tem de ser unique
    @Column(name = "CounterKey2", nullable = false)
    private String counterKey2;
    //counterKey3
    //primary key desta entidade, tem de ser unique
    @Column(name = "CounterKey3", nullable = false)
    private String counterKey3;

    //counterKey4
    //primary key desta entidade, tem de ser unique
    @Column(name = "CounterKey4", nullable = false)
    private String counterKey4;

    //counterKey5
    //primary key desta entidade, tem de ser unique
    @Column(name = "CounterKey5", nullable = false)
    private String counterKey5;
    //movementCounterKeyRef1
    @Column(name = "MovementCounterKeyRef1", nullable = false)
    private String movementCounterKeyRef1;
    //movementCounterKeyRef2
    @Column(name = "MovementCounterKeyRef2", nullable = false)
    private String movementCounterKeyRef2;
    //movementCounterKeyRef3
    @Column(name = "MovementCounterKeyRef3", nullable = false)
    private String movementCounterKeyRef3;
    //movementCounterKeyRef4
    @Column(name = "MovementCounterKeyRef4", nullable = false)
    private String movementCounterKeyRef4;
    //movementCounterKeyRef5
    @Column(name = "MovementCounterKeyRef5", nullable = false)
    private String movementCounterKeyRef5;
    //counterAddition
    @Column(name = "CounterAddition", nullable = false)
    private String counterAddition;
    //counterLastNumber
    @Column(name = "CounterLastNumber", nullable = false)
    private int counterLastNumber;
    //counterPosition
    @Column(name = "CounterPosition", nullable = false)
    private int counterPosition;
    //counterFill
    @Column(name = "CounterFill", nullable = false)
    private boolean counterFill;
    //counterFillChar
    @Column(name = "CounterFillChar", nullable = false)
    private String counterFillChar;
    //counterReady
    @Column(name = "CounterReady", nullable = false)
    private boolean counterReady;
    //counterComments
    @Column(name = "CounterComments")
    private String counterComments;

    public MovementCounter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounterKey1() {
        return counterKey1;
    }

    public void setCounterKey1(String counterKey1) {
        this.counterKey1 = counterKey1;
    }

    public String getCounterKey2() {
        return counterKey2;
    }

    public void setCounterKey2(String counterKey2) {
        this.counterKey2 = counterKey2;
    }

    public String getCounterKey3() {
        return counterKey3;
    }

    public void setCounterKey3(String counterKey3) {
        this.counterKey3 = counterKey3;
    }

    public String getCounterKey4() {
        return counterKey4;
    }

    public void setCounterKey4(String counterKey4) {
        this.counterKey4 = counterKey4;
    }

    public String getCounterKey5() {
        return counterKey5;
    }

    public void setCounterKey5(String counterKey5) {
        this.counterKey5 = counterKey5;
    }

    public String getMovementCounterKeyRef1() {
        return movementCounterKeyRef1;
    }

    public void setMovementCounterKeyRef1(String movementCounterKeyRef1) {
        this.movementCounterKeyRef1 = movementCounterKeyRef1;
    }

    public String getMovementCounterKeyRef2() {
        return movementCounterKeyRef2;
    }

    public void setMovementCounterKeyRef2(String movementCounterKeyRef2) {
        this.movementCounterKeyRef2 = movementCounterKeyRef2;
    }

    public String getMovementCounterKeyRef3() {
        return movementCounterKeyRef3;
    }

    public void setMovementCounterKeyRef3(String movementCounterKeyRef3) {
        this.movementCounterKeyRef3 = movementCounterKeyRef3;
    }

    public String getMovementCounterKeyRef4() {
        return movementCounterKeyRef4;
    }

    public void setMovementCounterKeyRef4(String movementCounterKeyRef4) {
        this.movementCounterKeyRef4 = movementCounterKeyRef4;
    }

    public String getMovementCounterKeyRef5() {
        return movementCounterKeyRef5;
    }

    public void setMovementCounterKeyRef5(String movementCounterKeyRef5) {
        this.movementCounterKeyRef5 = movementCounterKeyRef5;
    }

    public String getCounterAddition() {
        return counterAddition;
    }

    public void setCounterAddition(String counterAddition) {
        this.counterAddition = counterAddition;
    }

    public int getCounterLastNumber() {
        return counterLastNumber;
    }

    public void setCounterLastNumber(int counterLastNumber) {
        this.counterLastNumber = counterLastNumber;
    }

    public int getCounterPosition() {
        return counterPosition;
    }

    public void setCounterPosition(int counterPosition) {
        this.counterPosition = counterPosition;
    }

    public boolean isCounterFill() {
        return counterFill;
    }

    public void setCounterFill(boolean counterFill) {
        this.counterFill = counterFill;
    }

    public String getCounterFillChar() {
        return counterFillChar;
    }

    public void setCounterFillChar(String counterFillChar) {
        this.counterFillChar = counterFillChar;
    }

    public boolean isCounterReady() {
        return counterReady;
    }

    public void setCounterReady(boolean counterReady) {
        this.counterReady = counterReady;
    }

    public String getCounterComments() {
        return counterComments;
    }

    public void setCounterComments(String counterComments) {
        this.counterComments = counterComments;
    }
}
