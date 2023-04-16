package com.midnight.sindicato.interfaces;

public interface FormObservable {
    public void notifyTextChange();

    public void setFormObserver(FormObserver formObserver);
}
