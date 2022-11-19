package com.ku.business.dao;

public interface Repository {
    String ID_COLUMN = "id";
    String COMPANY_ID_COLUMN = "company_id";
    String COMPANY_NAME_COLUMN = "company_name";
    String COMPANY_TAX_NUMBER_COLUMN = "tax_number";
    String COMPANY_USER_ID_COLUMN = "user_id";
    String COMPANY_IS_GOVERNMENT_AGENCY_COLUMN = "is_government_agency";
    String CONTENT_ID_COLUMN = "content_id";
    String QUANTITY_COLUMN = "quantity";
    String STORAGE_ID_COLUMN = "storage_id";
    String DETAIL_ID_COLUMN = "detail_id";
    String DETAIL_OPERATION_TYPE_COLUMN = "operation_type";
    String SERVICE_ID_COLUMN = "service_id";
    String SERVICE_NAME_COLUMN = "service_name";
    String SUM_COLUMN = "sum";
    String SERVICE_DESCRIPTION_COLUMN = "service_description";
    String ORDER_ID_COLUMN = "order_id";
    String ORDER_STATUS_TYPE_COLUMN = "order_status";
    String ORDER_CREATED_AT_UTC_COLUMN = "created_at_utc";
    String ORDER_COMPLETED_AT_UTC_COLUMN = "completed_at_utc";
    String DOCUMENT_CONTENT_COLUMN = "document_content";
}
