package ra.mobileshopmanagementsystem.dao;

import ra.mobileshopmanagementsystem.model.Invoice;
import ra.mobileshopmanagementsystem.model.InvoiceDetail;

import java.util.List;

public interface IInvoice {
    public boolean addInvoice();
    public boolean addInvoiceDetail();
    public List<Invoice> getAllInvoice();
    public List<InvoiceDetail> getAllInvoiceDetail(int invoiceId);
    public void showAllInvoice();
}
