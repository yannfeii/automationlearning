package com.psa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AleLog {

    @JsonProperty("EVENT_DT")
    private String eventDt;
    @JsonProperty("EVENT_ID")
    private String eventId;
    @JsonProperty("TRANS_ID")
    private String transId;
    @JsonProperty("MSG_ID")
    private String msgId;
    @JsonProperty("CORRELATION_ID")
    private String correlationId;
    @JsonProperty("SOURCE_SYSTEM_M")
    private String sourceSystemM;
    @JsonProperty("SOURCE_SYSTEM_ID")
    private String sourceSystemId;
    @JsonProperty("MODULE_M")
    private String moduleM;
    @JsonProperty("DEST_SYSTEM_M")
    private String destSystemM;
    @JsonProperty("DEST_SYSTEM_ID")
    private String destSystemId;
    @JsonProperty("DURATION_N")
    private String durationN;
    @JsonProperty("IC_ID")
    private String icId;
    @JsonProperty("LOG_TYPE_M")
    private String logTypeM;
    @JsonProperty("LOG_LEVEL_M")
    private String logLevelM;
    @JsonProperty("APPLICATION_M")
    private String applicationM;
    @JsonProperty("ENGINE_M")
    private String engineM;
    @JsonProperty("HOST_M")
    private String hostM;
    @JsonProperty("NODE_M")
    private String nodeM;
    @JsonProperty("PROCESS_STACK_X")
    private String processStackX;
    @JsonProperty("MSG_C")
    private String msgC;
    @JsonProperty("STACK_TRACE_X")
    private String stackTraceX;
    @JsonProperty("SRC_QUEUE_TOPIC_M")
    private String srcQueueTopicM;
    @JsonProperty("DEST_QUEUE_TOPIC_M")
    private String destQueueTopicM;
    @JsonProperty("MSG_X")
    private String msgX;
    @JsonProperty("PAYLOAD_X")
    private String payloadX;

    public String getEventDt() {
        return eventDt;
    }

    public void setEventDt(String eventDt) {
        this.eventDt = eventDt;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getSourceSystemM() {
        return sourceSystemM;
    }

    public void setSourceSystemM(String sourceSystemM) {
        this.sourceSystemM = sourceSystemM;
    }

    public String getSourceSystemId() {
        return sourceSystemId;
    }

    public void setSourceSystemId(String sourceSystemId) {
        this.sourceSystemId = sourceSystemId;
    }

    public String getModuleM() {
        return moduleM;
    }

    public void setModuleM(String moduleM) {
        this.moduleM = moduleM;
    }

    public String getDestSystemM() {
        return destSystemM;
    }

    public void setDestSystemM(String destSystemM) {
        this.destSystemM = destSystemM;
    }

    public String getDestSystemId() {
        return destSystemId;
    }

    public void setDestSystemId(String destSystemId) {
        this.destSystemId = destSystemId;
    }

    public String getDurationN() {
        return durationN;
    }

    public void setDurationN(String durationN) {
        this.durationN = durationN;
    }

    public String getIcId() {
        return icId;
    }

    public void setIcId(String icId) {
        this.icId = icId;
    }

    public String getLogTypeM() {
        return logTypeM;
    }

    public void setLogTypeM(String logTypeM) {
        this.logTypeM = logTypeM;
    }

    public String getLogLevelM() {
        return logLevelM;
    }

    public void setLogLevelM(String logLevelM) {
        this.logLevelM = logLevelM;
    }

    public String getApplicationM() {
        return applicationM;
    }

    public void setApplicationM(String applicationM) {
        this.applicationM = applicationM;
    }

    public String getEngineM() {
        return engineM;
    }

    public void setEngineM(String engineM) {
        this.engineM = engineM;
    }

    public String getHostM() {
        return hostM;
    }

    public void setHostM(String hostM) {
        this.hostM = hostM;
    }

    public String getNodeM() {
        return nodeM;
    }

    public void setNodeM(String nodeM) {
        this.nodeM = nodeM;
    }

    public String getProcessStackX() {
        return processStackX;
    }

    public void setProcessStackX(String processStackX) {
        this.processStackX = processStackX;
    }

    public String getMsgC() {
        return msgC;
    }

    public void setMsgC(String msgC) {
        this.msgC = msgC;
    }

    public String getStackTraceX() {
        return stackTraceX;
    }

    public void setStackTraceX(String stackTraceX) {
        this.stackTraceX = stackTraceX;
    }

    public String getSrcQueueTopicM() {
        return srcQueueTopicM;
    }

    public void setSrcQueueTopicM(String srcQueueTopicM) {
        this.srcQueueTopicM = srcQueueTopicM;
    }

    public String getDestQueueTopicM() {
        return destQueueTopicM;
    }

    public void setDestQueueTopicM(String destQueueTopicM) {
        this.destQueueTopicM = destQueueTopicM;
    }

    public String getMsgX() {
        return msgX;
    }

    public void setMsgX(String msgX) {
        this.msgX = msgX;
    }

    public String getPayloadX() {
        return payloadX;
    }

    public void setPayloadX(String payloadX) {
        this.payloadX = payloadX;
    }



}
