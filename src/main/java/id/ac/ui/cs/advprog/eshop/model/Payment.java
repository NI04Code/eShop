package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import lombok.Getter;

import enums.payment.PaymentStatus;
import enums.payment.PaymentMethod;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.setMethod(method);
        this.setPaymentData(paymentData);
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        } else {
            this.order = order;
        }
    }

    public void setStatus(String status) {
        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            this.status = status;
        } else if (status.equals(PaymentStatus.REJECTED.getValue())) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status");
        }
    }

    public void setMethod(String method) {
        if (method.equals(PaymentMethod.VOUCHER.getValue())) {
            this.method = method;
        } else if (method.equals(PaymentMethod.COD.getValue())) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null) {
            throw new IllegalArgumentException("Payment data cannot be null");
        }

        if (this.method.equals(PaymentMethod.VOUCHER.getValue())) {
            this.setStatus(PaymentStatus.REJECTED.getValue());

            if (paymentData.get("voucherCode").length() != 16) {
                throw new IllegalArgumentException("Invalid voucher code, must 16 characters long");
            }
            if (!paymentData.get("voucherCode").startsWith("ESHOP")) {
                throw new IllegalArgumentException("Invalid voucher code, must start with ESHOP");
            }

            int numCount = 0;
            for (char c : paymentData.get("voucherCode").toCharArray()) {
                if (Character.isDigit(c)) {
                    numCount++;
                }
            }
            if (numCount < 8) {
                throw new IllegalArgumentException("Invalid voucher code, must contain 8 numerical characters");
            }

            this.paymentData = paymentData;
            this.setStatus(PaymentStatus.SUCCESS.getValue());

        } else if (this.method.equals(PaymentMethod.COD.getValue())) {
            this.setStatus(PaymentStatus.REJECTED.getValue());

            if (paymentData.get("address") == null) {
                throw new IllegalArgumentException("Address cannot be null");
            }
            if (paymentData.get("deliveryFee") == null) {
                throw new IllegalArgumentException("Delivery fee cannot be null");
            }
            if (paymentData.get("deliveryFee").length() == 0) {
                throw new IllegalArgumentException("Delivery fee cannot be empty");
            }

            this.paymentData = paymentData;
            this.setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            throw new IllegalArgumentException("Payment method is not VOUCHER");
        }
    }
}