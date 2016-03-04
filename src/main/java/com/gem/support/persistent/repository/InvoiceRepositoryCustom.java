package com.gem.support.persistent.repository;

/**
 * Custom functions for {@link InvoiceRepository}, require manually implementing
 */
public interface InvoiceRepositoryCustom {
    int getUserIncrement(String invoiceId);
}
