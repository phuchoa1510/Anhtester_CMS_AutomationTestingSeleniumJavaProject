# Dự án cá nhân Automation Test - Anh Tester CMS

Đây là dự án tự động hóa kiểm thử cho hệ thống CMS, sử dụng Java và Selenium.

## 🛠 Công nghệ sử dụng
- **Ngôn ngữ:** Java
- **Thư viện:** Selenium WebDriver
- **Framework:** TestNG
- **Quản lý dự án:** Maven
- **Báo cáo:** Allure Report

## 📂 Cấu trúc dự án
- `src/main/java`: Chứa các mã nguồn của dự án.
- `src/test/java`: Chứa các kịch bản kiểm thử (Test Cases).
- `exports/`: Nơi lưu trữ kết quả chạy test:
    - `logs/`: Các tệp ghi nhật ký.
    - `screenshots/`: Ảnh chụp màn hình khi lỗi.
    - `videos/`: Video quay lại quá trình chạy test.
- `allure-report/`: Thư mục chứa báo cáo sau khi chạy.
- `pom.xml`: File cấu hình các thư viện liên quan.

## 🔄 Quy trình kiểm thử (Workflow)
Các luồng nghiệp vụ chính được thực hiện tự động trong dự án:

1. **Login Test:** Kiểm thử đăng nhập hệ thống.
2. **Get Product Info:** Kiểm tra và lấy thông tin chi tiết sản phẩm.
3. **Update Profile Test:** Cập nhật thông tin cá nhân trên Dashboard.
4. **Order Test (Quy trình đặt hàng):**
   - Add Product (Thêm vào giỏ) → My Cart (Kiểm tra giỏ hàng) → Shipping Info (Thông tin giao hàng) → Delivery Info (Phương thức vận chuyển) → Payment (Thanh toán) → Confirmation (Xác nhận đơn hàng).

## 📊 Xem báo cáo Allure
Sau khi chạy test xong, bạn sử dụng lệnh sau để mở báo cáo:
```bash
allure serve target/allure-results
```

## 📝 Lưu ý
- Các tài liệu nằm trong thư mục `docs/`.
- Kết quả video và ảnh chụp màn hình sẽ tự động sinh ra trong thư mục `exports/` sau mỗi lần chạy.
