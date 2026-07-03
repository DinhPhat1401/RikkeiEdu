package ra.mobileshopmanagementsystem.dao;

import ra.mobileshopmanagementsystem.model.Invoice;
import ra.mobileshopmanagementsystem.model.InvoiceDetail;

import java.util.List;

public interface IInvoice {
    public boolean addInvoice();
    public boolean addInvoiceDetail();
    public boolean deleteInvoice();
    public boolean updateInvoice();
    public List<Invoice> getAllInvoice();
    public List<InvoiceDetail> getAllInvoiceDetail(int invoiceId);
}
