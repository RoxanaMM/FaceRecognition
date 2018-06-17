package logicPackage.model;

import logicPackage.enums.AlgoName;
import logicPackage.enums.TypesOfSet;

public class Results {
    String picA;
    String picB;
    TypesOfSet typesOfSet;
    AlgoName algoName;

    public Results() {}

    public Results(String picA, String picB, TypesOfSet typesOfSet, AlgoName algoName) {
        this.picA = picA;
        this.picB = picB;
        this.typesOfSet = typesOfSet;
        this.algoName = algoName;
    }

    public String getPicA() {
        return picA;
    }

    public void setPicA(String picA) {
        this.picA = picA;
    }

    public String getPicB() {
        return picB;
    }

    public void setPicB(String picB) {
        this.picB = picB;
    }

    public TypesOfSet getTypesOfSet() {
        return typesOfSet;
    }

    public void setTypesOfSet(TypesOfSet typesOfSet) {
        this.typesOfSet = typesOfSet;
    }

    public AlgoName getAlgoName() {
        return algoName;
    }

    public void setAlgoName(AlgoName algoName) {
        this.algoName = algoName;
    }
}
