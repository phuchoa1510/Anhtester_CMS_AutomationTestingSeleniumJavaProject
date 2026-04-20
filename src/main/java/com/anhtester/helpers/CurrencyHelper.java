package com.anhtester.helpers;

public class CurrencyHelper {

    /**
     * Hàm chuẩn hóa giá trị tiền tệ từ mọi nguồn (UI, Excel, String, Object)
     * Trả về Double để dễ dàng so sánh số học.
     *
     * @param value Đối tượng cần xử lý (có thể là String, Double từ Excel, v.v.)
     * @return Giá trị số thuần túy (Double)
     */
    public static Double getNumericPrice(Object value) {
        if (value == null) return 0.0;

        // 1. Chuyển tất cả về String và loại bỏ khoảng trắng dư thừa
        String priceStr = String.valueOf(value).trim();

        if (priceStr.isEmpty()) return 0.0;

        // 2. Xử lý định dạng Châu Âu (1.200,50) sang chuẩn quốc tế (1200.50)
        if (priceStr.contains(",") && priceStr.contains(".")) {
            // Trường hợp: 1,200.50 hoặc 1.200,50
            if (priceStr.lastIndexOf(",") > priceStr.lastIndexOf(".")) {
                // Định dạng 1.200,50 -> Bỏ dấu chấm, đổi phẩy thành chấm
                priceStr = priceStr.replace(".", "").replace(",", ".");
            } else {
                // Định dạng 1,200.50 -> Bỏ dấu phẩy
                priceStr = priceStr.replace(",", "");
            }
        } else if (priceStr.contains(",")) {
            // Trường hợp chỉ có dấu phẩy: 1200,50 -> đổi thành 1200.50
            if (priceStr.indexOf(",") == priceStr.lastIndexOf(",") && priceStr.length() - priceStr.indexOf(",") <= 3) {
                priceStr = priceStr.replace(",", ".");
            } else {
                priceStr = priceStr.replace(",", "");
            }
        }

        // 3. Dùng Regex lọc lấy các chữ số và dấu chấm thập phân duy nhất
        // Loại bỏ ký hiệu $, €, VND, "Price:", v.v.
        String cleanValue = priceStr.replaceAll("[^0-9.]", "");

        // 4. Kiểm tra nếu chuỗi sau khi lọc bị rỗng (ví dụ đầu vào chỉ là "Free")
        if (cleanValue.isEmpty() || cleanValue.equals(".")) return 0.0;

        try {
            return Double.parseDouble(cleanValue);
        } catch (NumberFormatException e) {
            System.err.println("Lỗi parse giá tiền: " + priceStr);
            return 0.0;
        }
    }
}
