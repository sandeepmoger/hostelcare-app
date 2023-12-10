package com.sandeep.hostelcare;
import androidx.lifecycle.ViewModel;

public class WardenViewModel extends ViewModel {
    private String wardenName;

    public String getWardenName() {
        return wardenName;
    }

    public void setWardenName(String wardenName) {
        this.wardenName = wardenName;
    }
}
