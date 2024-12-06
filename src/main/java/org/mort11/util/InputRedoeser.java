package org.mort11.util;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InputRedoeser {

    public String key;

    public String currentNumber, lastNumber;
    
    public InputRedoeser(String key, String startValue) {
        SmartDashboard.putString(key, startValue);

        this.key = key;
        currentNumber = startValue;
        lastNumber = startValue;
    }

    // public String get() {
    //     currentNumber = SmartDashboard.getString(key, key);

    //     if(!currentNumber.equals(lastNumber)) {
    //         lastNumber = currentNumber;
    //         return true;
    //     }
    //     return false;
    // }

    public String get() {
        return SmartDashboard.getString(key, key);
    }
}
