package com.springboot.enumeration;

public enum TaskStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED

    //Kas STATUS peaks olema nii TASKil kui PROJECTil sama?
    //Kui jaa, siis kas tekitame TASKile ka arhiveerimisvõimaluse
    //või ei arhiveeri me midagi ja lisame lihtsalt DELETE funktsiooni?
}
