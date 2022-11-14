package com.ku.business.dao;

public interface IDetailRepository {
    String DETAIL_ID_COLUMN = "id";
    String DETAIL_OPERATION_TYPE_COLUMN = "operation_type";
    String COMPANY_ID_COLUMN = "company_id";
    String COMPANY_NAME_COLUMN = "company_name";
    String COMPANY_TAX_NUMBER_COLUMN = "company_tax_number";
    String COMPANY_USER_ID_COLUMN = "company_user_id";
    String COMPANY_IS_GOVERNMENT_AGENCY_COLUMN = "company_is_government_agency";
    String ORDER_ID_COLUMN = "order_id";
    String ORDER_STATUS_TYPE_COLUMN = "order_status";
    String ORDER_CREATE_AT_UTC_COLUMN = "order_cr_at_utc";
    String ORDER_COMPLETED_AT_UTC_COLUMN = "order_com_at_utc";
}
