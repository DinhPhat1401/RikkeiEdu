package ra.mobileshopmanagementsystem.business;

import ra.mobileshopmanagementsystem.model.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface IInvoiceBusiness {
    public List<Invoice> getAllInvoiceByCustomerName(String customerName);
    public List<Invoice> getAllInvoiceByDate(LocalDate date);
    public double getTotalAmountByDate(LocalDate date);
    public double getTotalAmountByMonth(int month, int year);
    public double getTotalAmountByYear(int year);

}
