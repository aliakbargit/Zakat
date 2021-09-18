package com.example.zakat.models.core;

public class SuccessOrFailMsg {
    private String msg;
    private boolean isSuccess;
    private boolean called = false;
    private boolean canInsert = false;
    private boolean canDelete = false;
    private boolean canUpdate = false;
    private boolean isComplete = false;

    public SuccessOrFailMsg() {
    }

    public SuccessOrFailMsg(String msg, boolean isSuccess) {
        this.msg = msg;
        this.isSuccess = isSuccess;
    }

    public SuccessOrFailMsg(String msg, boolean isSuccess, boolean called) {
        this.msg = msg;
        this.isSuccess = isSuccess;
        this.called = called;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public boolean isCalled() {
        return called;
    }

    public void setCalled(boolean called) {
        this.called = called;
    }

    public boolean isCanInsert() {
        return canInsert;
    }

    public void setCanInsert(boolean canInsert) {
        this.canInsert = canInsert;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    public boolean isCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
