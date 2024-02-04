package com.example.projectemployeeinvoice.service;

import com.example.projectemployeeinvoice.model.PaymentEntry;
import com.example.projectemployeeinvoice.repository.InvoiceRepository;

import java.util.List;

public class InvoiceService {



    // Inne i service hanteras vanligen undantag och fel i den in eller ut data som skickas eller tas emot


        private InvoiceRepository invoiceRepository = new InvoiceRepository();

        public List<PaymentEntry> getAll() {
            return invoiceRepository.getAll();
        }

        public PaymentEntry get(int id) {
        return invoiceRepository.get(id);
        }

        public void create(String title, String date, String description, String category, double price, int employee_id) {
        invoiceRepository.create(title, date, description, category, price, employee_id);
        }

         public void delete(int id) {
        invoiceRepository.delete(id);
        }

         public PaymentEntry update(PaymentEntry entry) {
        invoiceRepository.update(entry);
        return invoiceRepository.get(entry.getId());
         }

}
