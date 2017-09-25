package com.verysu.mydata.spring;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-19 18:45
 */
public class MyData {

    private Integer batchSize;
    private SourceTable sourceTable;
    private DestTable destTable;

    public SourceTable getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(SourceTable sourceTable) {
        this.sourceTable = sourceTable;
    }

    public DestTable getDestTable() {
        return destTable;
    }

    public void setDestTable(DestTable destTable) {
        this.destTable = destTable;
    }

    public Integer getBatchSize() {
        return batchSize;
    }
    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "batchSize=" + batchSize +
                ", sourceTable=" + sourceTable +
                ", destTable=" + destTable +
                '}';
    }
}
