package com.hu.video.entity;

public class AliPay {
        private Long uid;
        private String traceNo;
        private double totalAmount;
        private String subject;
        private String alipayTraceNo;
        public AliPay() {
        }

        public AliPay(Long uid, String traceNo, double totalAmount, String subject, String alipayTraceNo) {
            this.uid = uid;
            this.traceNo = traceNo;
            this.totalAmount = totalAmount;
            this.subject = subject;
            this.alipayTraceNo = alipayTraceNo;
        }

        public AliPay(String traceNo, double totalAmount, String subject, String alipayTraceNo) {
            this.traceNo = traceNo;
            this.totalAmount = totalAmount;
            this.subject = subject;
            this.alipayTraceNo = alipayTraceNo;
        }

        public String getTraceNo() {
            return traceNo;
        }

        public void setTraceNo(String traceNo) {
            this.traceNo = traceNo;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getAlipayTraceNo() {
            return alipayTraceNo;
        }

        public void setAlipayTraceNo(String alipayTraceNo) {
            this.alipayTraceNo = alipayTraceNo;
        }

        public Long getUid() {
            return uid;
        }

        public void setUid(Long uid) {
            this.uid = uid;
        }

        @Override
        public String toString() {
            return "AliPay{" +
                    "uid=" + uid +
                    ", traceNo='" + traceNo + '\'' +
                    ", totalAmount=" + totalAmount +
                    ", subject='" + subject + '\'' +
                    ", alipayTraceNo='" + alipayTraceNo + '\'' +
                    '}';
        }
    }
