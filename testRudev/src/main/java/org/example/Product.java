package org.example;

public class Product {

        private long productId;
        private int sellerId;
        private String oriMinPrice;
        private String oriMaxPrice;
        private int promotionId;
        private String productTitle;

        public long getProductId() {
                return productId;
        }

        public void setProductId(int productId) {
                this.productId = productId;
        }

        public int getSellerId() {
                return sellerId;
        }

        public void setSellerId(int sellerId) {
                this.sellerId = sellerId;
        }

        public String getOriMinPrice() {
                return oriMinPrice;
        }

        public void setOriMinPrice(String oriMinPrice) {
                this.oriMinPrice = oriMinPrice;
        }

        public String getOriMaxPrice() {
                return oriMaxPrice;
        }

        public void setOriMaxPrice(String oriMaxPrice) {
                this.oriMaxPrice = oriMaxPrice;
        }

        public int getPromotionId() {
                return promotionId;
        }

        public void setPromotionId(int promotionId) {
                this.promotionId = promotionId;
        }

        public String getProductTitle() {
                return productTitle;
        }

        public void setProductTitle(String productTitle) {
                this.productTitle = productTitle;
        }

        @Override
        public String toString() {
                return "Product{" +
                        "productId=" + productId +
                        ", sellerId=" + sellerId +
                        ", oriMinPrice='" + oriMinPrice + '\'' +
                        ", oriMaxPrice='" + oriMaxPrice + '\'' +
                        ", promotionId=" + promotionId +
                        ", productTitle='" + productTitle + '\'' +
                        '}';
        }

        public Product(long productId, int sellerId, String oriMinPrice, String oriMaxPrice, int promotionId, String productTitle) {
                this.productId = productId;
                this.sellerId = sellerId;
                this.oriMinPrice = oriMinPrice;
                this.oriMaxPrice = oriMaxPrice;
                this.promotionId = promotionId;
                this.productTitle = productTitle;
        }
}
