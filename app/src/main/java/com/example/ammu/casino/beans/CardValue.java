package com.example.ammu.casino.beans;

/**
 * Created by ammu on 7/21/2017.
 */

public enum CardValue {
    A(14),K(13),Q(12),J(11),C10(10),C9(9),C8(8),C7(7),C6(6),C5(5),C4(4),C3(3),C2(2);
    int value;
    CardValue(int value){
        this.value=value;
    }
}
