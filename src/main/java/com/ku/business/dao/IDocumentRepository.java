package com.ku.business.dao;

public interface IDocumentRepository {
    String DOCUMENT_ID_COLUMN = "id";
    String DOCUMENT_ORDER_ID = "order_id";
    String DOCUMENT_DOCUMENT_CONTENT = "document_content";
    String ORDER_ID_COLUMN = "order_order_id";
    String ORDER_STATUS_TYPE_COLUMN = "order_status";
    String ORDER_CREATE_AT_UTC_COLUMN = "order_created_at_utc";
    String ORDER_COMPLETED_AT_UTC_COLUMN = "order_completed_at_utc";
}