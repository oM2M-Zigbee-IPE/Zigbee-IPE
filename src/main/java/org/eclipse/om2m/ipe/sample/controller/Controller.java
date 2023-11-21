package org.eclipse.om2m.ipe.sample.controller;

import org.eclipse.om2m.core.service.CseService;

public class Controller {

    public static CseService CSE;
    protected static String AE_ID;


    public static void setCse(CseService cse){
        CSE = cse;
    }

}
