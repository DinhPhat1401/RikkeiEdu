package ra.mobileshopmanagementsystem.business.impl;

import ra.mobileshopmanagementsystem.business.IInvoiceBusiness;
import ra.mobileshopmanagementsystem.dao.impl.CustomerDaoImpl;
import ra.mobileshopmanagementsystem.dao.impl.InvoiceDaoImpl;
import ra.mobileshopmanagementsystem.model.Invoice;

import java.time.LocalDate;
import java.util.List;

public class InvoiceBusinessImpl implements IInvoiceBusiness {
    private InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();
    private CustomerDaoImpl customerDao = new CustomerDaoImpl();

    @Override
    public List<Invoice> getAllInvoiceByCustomerName(String customerName) {
            List<Invoice> invoices = invoiceDao.getAllInvoice();
            if (invoices == null || invoices.isEmpty()) {
                System.out.println("Không có hóa đơn nào trong hệ thống.");
            } else {
                return invoices.stream()
                        .filter(invoice -> customerDao.getCustomerById(invoice.getCustomerId()).getName().toLowerCase().contains(customerName.toLowerCase()))
                        .toList();
            }

        return null;

    }

    @Override
    public List<Invoice> getAllInvoiceByDate(LocalDate date) {
        List<Invoice> invoices = invoiceDao.getAllInvoice();
            return invoices.stream()
                    .filter(invoice -> invoice.getCreatedAt().toLocalDate().equals(date))
                    .toList();

    }

    @Override
    public double getTotalAmountByDate(LocalDate date) {
            List<Invoice> invoices = invoiceDao.getAllInvoice();
                return invoices.stream()
                        .filter(invoice -> invoice.getCreatedAt().toLocalDate().equals(date))
                        .mapToDouble(Invoice::getTotalAmount)
                        .sum();
    }

    @Override
    public double getTotalAmountByMonth(int month, int year) {

        List<Invoice> invoices = invoiceDao.getAllInvoice();
            return invoices.stream()
                    .filter(invoice -> invoice.getCreatedAt().getMonthValue() == month && invoice.getCreatedAt().getYear() == year)
                    .mapToDouble(Invoice::getTotalAmount)
                    .sum();

    }

    @Override
    public double getTotalAmountByYear(int year) {
        List<Invoice> invoices = invoiceDao.getAllInvoice();
            return invoices.stream()
                    .filter(invoice -> invoice.getCreatedAt().getYear() == year)
                    .mapToDouble(Invoice::getTotalAmount)
                    .sum();

    }

}
