package org.eclipse.om2m.ipe.sample.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Map;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Item {
    private String name;
    private boolean awake;
    private int defaultVal;
    private int refreshInterval;
    private String parseAt;
    private String parseCl;
    private int parseEp;
    private String parseEval;
    private String parseFn;
    private Map<String, Object> parseRead;
    private Map<String, Object> read;
}
